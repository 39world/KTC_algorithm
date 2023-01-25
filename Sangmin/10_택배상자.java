import java.util.*;
import java.util.stream.IntStream;

class Scratch {
    public static void main(String[] args) {
        int[] order1 = {4, 3, 1, 2, 5};
        int result1 = 2;
        assert Solution.solution(order1) == result1;

        int[] order2 = {5, 4, 3, 2, 1};
        int result2 = 5;
        assert Solution.solution(order2) == result2;
    }

    static class Solution {
        static public int solution(int[] order) {
            Stack<Integer> defaults = new Stack<>();
            Stack<Integer> tmp = new Stack<>();
            IntStream.range(1, order.length + 1)
                    .boxed()
                    .sorted(Comparator.reverseOrder())
                    .forEach(defaults::push);
            int cnt = 0;
            try {
                while (true) {
                    if (!defaults.isEmpty() && defaults.peek() == order[cnt]) {
                        cnt++;
                        defaults.pop();
                    } else if (!tmp.isEmpty() && tmp.peek() == order[cnt]) {
                        cnt++;
                        tmp.pop();
                    } else {
                        tmp.push(defaults.pop());
                    }
                }
            } catch (Exception ignored) {}

            return cnt;
            /*
            AtomicInteger cnt = new AtomicInteger(0);
            IntStream.range(1, order.length + 1)
                    .forEach(i -> {
                        tmp.push(i);
                        while (!tmp.isEmpty() &&
                                tmp.peek() == order[cnt.get()]) {
                            tmp.pop();
                            cnt.addAndGet(1);
                        }
                    });
            return cnt.get();
            */
        }
    }
}
