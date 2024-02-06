import java.util.HashMap;
import java.util.function.BiPredicate;

public class HomeWork1 {

    private final static int N = 25;

    public static void main(String[] args) {

        BiPredicate<Integer, Integer> predicate1 = (x, y) -> x > y;
        BiPredicate<Integer, Integer> predicate2 = Integer::equals;
        BiPredicate<Integer, Integer> predicate3 = (x, y) -> y == (N - 1) - x;
        BiPredicate<Integer, Integer> predicate4 = (x, y) -> {
            final var c = 5;
            return y < N + c - x;
        };
        BiPredicate<Integer, Integer> predicate5 = (x, y) -> y == x / 2;
        BiPredicate<Integer, Integer> predicate6 = (x, y) -> {
            final var c = 10;
            return y < c || x < c;
        };
        BiPredicate<Integer, Integer> predicate7 = (x, y) -> {
            final var c = 15;
            return y > c && x > c;
        };
        BiPredicate<Integer, Integer> predicate8 = (x, y) -> y == 0 || x == 0;
        BiPredicate<Integer, Integer> predicate8_1 = (x, y) -> x * y == 0;
        BiPredicate<Integer, Integer> predicate9 = (x, y) -> {
            final var c = 10;
            return Math.abs(y - x) >= c;
        };
        BiPredicate<Integer, Integer> predicate9_1 = (x, y) -> {
            final var c = 10;
            return y >= x + c || y <= x - c;
        };
        BiPredicate<Integer, Integer> predicate10 = (x, y) -> y >= x / 2 && y < x;
        BiPredicate<Integer, Integer> predicate11 = (x, y) -> y == 1 || x == 1 || y == N - 2 || x == N - 2;
        BiPredicate<Integer, Integer> predicate11_1 = (x, y) -> Math.abs(x - N / 2) == (N / 2 - 1) || Math.abs(y - N / 2) == (N / 2 - 1);
        BiPredicate<Integer, Integer> predicate12 = (x, y) -> Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(20, 2);
        BiPredicate<Integer, Integer> predicate13 = (x, y) -> {
            final var c = 4;
            return y < Math.abs((N - 1) - x) + c && y > Math.abs((N - 1) - x) - c;
        };
        BiPredicate<Integer, Integer> predicate14 = (x, y) -> {
            final var c = 27;
            return Math.pow(x - c, 2) + Math.pow(y - c, 2) > Math.pow(23, 2);
        };
        BiPredicate<Integer, Integer> predicate15 = (x, y) -> {
            final var c = 10;
            return Math.abs(y - x) >= c && Math.abs(y - x) <= 2 * c;
        };
        BiPredicate<Integer, Integer> predicate16 = (x, y) -> {
            final var c = 10;
            return Math.abs(x - c - 2) + Math.abs(y - c - 2) < c;
        };
        BiPredicate<Integer, Integer> predicate17 = (x, y) -> y > 9 * Math.sin(x / 4.0) + 15;
        BiPredicate<Integer, Integer> predicate21 = (x, y) -> y.equals(x) || y == 2 * x + 1;


        final var conditions = new HashMap<String, BiPredicate<Integer, Integer>>();
        conditions.put("Pic_1", predicate1);
        conditions.put("Pic_2", predicate2);
        conditions.put("Pic_3", predicate3);
        conditions.put("Pic_4", predicate4);
        conditions.put("Pic_5", predicate5);
        conditions.put("Pic_6", predicate6);
        conditions.put("Pic_7", predicate7);
        conditions.put("Pic_8", predicate8);
        conditions.put("Pic_9", predicate9);
        conditions.put("Pic_10", predicate10);
        conditions.put("Pic_11", predicate11);
        conditions.put("Pic_12", predicate12);
        conditions.put("Pic_13", predicate13);
        conditions.put("Pic_14", predicate14);
        conditions.put("Pic_15", predicate15);
        conditions.put("Pic_16", predicate16);
        conditions.put("Pic_17", predicate17);
        conditions.put("Pic_21", predicate21);

        conditions.forEach(HomeWork1::printGraphicByPredicate);
    }

    private static void printGraphicByPredicate(String header, BiPredicate<Integer, Integer> condition) {
        final var sb = new StringBuffer(header).append(":").append("\n");

        int x = 0;
        int y = 0;

        while (y < N) {
            if (x == N) {
                sb.append("\n");
                x = 0;
                y++;
                continue;
            } else if (condition.test(x, y)) {
                sb.append("X ");
            } else {
                sb.append(". ");
            }
            x++;
        }

        System.out.println(sb);
    }
}
