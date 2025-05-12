package hw_22;

public class BloomFilter {
    private final int hashCount;
    private final long byteSize;
    private final byte[] bloom;

    private static final byte[] BIT_MASK = {
            0x01, 0x02, 0x04, 0x08,
            0x10, 0x20, 0x40, (byte) 0x80
    };

    public BloomFilter(int maxKeys, double errorRate, int foldFactor) {
        final var bitSize = computeBitSize(maxKeys, errorRate);
        this.hashCount = computeHashFunctionCount(maxKeys, bitSize);
        this.byteSize = computeBloomFilterSize(bitSize, foldFactor);
        this.bloom = new byte[(int) byteSize];
    }

    public static long computeBitSize(long maxKeys, double errorRate) {
        return (long) Math.ceil(maxKeys * (-Math.log(errorRate) / (Math.log(2) * Math.log(2))));
    }

    public static int computeHashFunctionCount(int maxKeys, long bitSize) {
        return (int) Math.ceil(Math.log(2) * bitSize / maxKeys);
    }

    public static int computeBloomFilterSize(long bitSize, int foldFactor) {
        var byteSizeLong = (bitSize + 7) / 8;
        var mask = (1 << foldFactor) - 1;
        if ((mask & byteSizeLong) != 0) {
            byteSizeLong >>= foldFactor;
            ++byteSizeLong;
            byteSizeLong <<= foldFactor;
        }
        return (int) byteSizeLong;
    }

    public void add(byte[] buf) {
        add(buf, 0, buf.length);
    }

    public void add(byte[] buf, int offset, int len) {
        final var hash1 = murmurHash3(buf, offset, len, 0);
        final var hash2 = murmurHash3(buf, offset, len, hash1);

        for (int i = 0; i < hashCount; i++) {
            int pos = Math.abs((int) ((hash1 + i * (long) hash2) % (byteSize * 8)));
            set(pos);
        }
    }

    public boolean contains(byte[] buf) {
        return contains(buf, 0, buf.length);
    }

    public boolean contains(byte[] buf, int offset, int len) {
        final var hash1 = murmurHash3(buf, offset, len, 0);
        final var hash2 = murmurHash3(buf, offset, len, hash1);
        var compositeHash = hash1;

        for (int i = 0; i < hashCount; i++) {
            int pos = Math.abs((int) (compositeHash % (byteSize * 8)));
            if (!get(pos)) {
                return false;
            }
            compositeHash = compositeHash + hash2;
        }
        return true;
    }

    private void set(int pos) {
        final var byteNum = pos >> 3;
        final var bitNum = pos & 7;
        bloom[byteNum] |= BIT_MASK[bitNum];
    }

    private boolean get(int pos) {
        final var byteNum = pos >> 3;
        final var bitNum = pos & 7;
        return (bloom[byteNum] & BIT_MASK[bitNum]) != 0;
    }

    private static int murmurHash3(byte[] data, int offset, int length, int seed) {
        final var c1 = 0xcc9e2d51;
        final var c2 = 0x1b873593;
        final var roundedEnd = offset + (length & 0xfffffffc);
        var h1 = seed;

        for (int i = offset; i < roundedEnd; i += 4) {
            int k1 = ((data[i] & 0xff)) |
                    ((data[i + 1] & 0xff) << 8) |
                    ((data[i + 2] & 0xff) << 16) |
                    ((data[i + 3] & 0xff) << 24);
            k1 *= c1;
            k1 = Integer.rotateLeft(k1, 15);
            k1 *= c2;

            h1 ^= k1;
            h1 = Integer.rotateLeft(h1, 13);
            h1 = h1 * 5 + 0xe6546b64;
        }

        var k1 = 0;
        final var tail = length & 3;

        if (tail == 3) {
            k1 ^= (data[offset + length - 3] & 0xff) << 16;
        }

        if (tail >= 2) {
            k1 ^= (data[offset + length - 2] & 0xff) << 8;
        }

        if (tail >= 1) {
            k1 ^= (data[offset + length - 1] & 0xff);
            k1 *= c1;
            k1 = Integer.rotateLeft(k1, 15);
            k1 *= c2;
            h1 ^= k1;
        }

        h1 ^= length;
        h1 ^= (h1 >>> 16);
        h1 *= 0x85ebca6b;
        h1 ^= (h1 >>> 13);
        h1 *= 0xc2b2ae35;
        h1 ^= (h1 >>> 16);

        return h1;
    }
}
