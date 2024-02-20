package hw_3;

public class SimplePow {

    public static void main(String[] args) {
        for (int i = 25; i <= 250; i += 25) {
            System.out.println("-------------------------");
            final var sp = new SimplePow();
            var start = System.nanoTime();
            long l = sp.simplePoe(5, i);
            var end = System.nanoTime();
            System.out.println(l);
            var time1 = end - start;
            System.out.println(time1);

            start = System.nanoTime();
            long l2 = sp.simplePoe1(5, i);
            end = System.nanoTime();
            System.out.println(l2);
            var time2 = end - start;
            System.out.println(time2);

            start = System.nanoTime();
            long l3 = sp.recursivePow(5, i);
            end = System.nanoTime();
            System.out.println(l3);
            var time3 = end - start;
            System.out.println(time3);
        }
    }

    //O(n)
    private long simplePoe(long base, long rate) {
        long result = 1;
        for (int i = 1; i <= rate; i++) {
            result = result * base;
        }
        return result;
    }

    //    O(N/2)
    private long simplePoe1(long base, long rate) {
        boolean isEven = rate % 2 == 0;

        if (isEven) {
            var halfResult = simplePoe(base, rate / 2);
            return halfResult * halfResult;
        } else {
            var halfResult = simplePoe(base, (rate - 1) / 2);
            return halfResult * halfResult * base;
        }
    }

    //    O(logN)
    private long recursivePow(long base, long rate) {
        if (rate == 0) {
            return 1;
        }

        if (rate % 2 == 0) {
            long result = recursivePow(base, rate / 2);
            return result * result;
        } else {
            long result = recursivePow(base, rate - 1);
            return result * base;
        }
    }
}
