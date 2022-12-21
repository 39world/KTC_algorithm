import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
// webhook test
class Scratch {
    public static void main(String[] args) {
        int k1 = 6;
        int[] t1 = {1, 3, 2, 5, 4, 5, 2, 3};
        int result1 = 3;
        assert Solution.solution(k1, t1) == result1;

        int k2 = 4;
        int[] t2 = {1, 3, 2, 5, 4, 5, 2, 3};
        int result2 = 2;
        assert Solution.solution(k2, t2) == result2;

        int k3 = 2;
        int[] t3 = {1, 1, 1, 1, 2, 2, 2, 3};
        int result3 = 1;
        assert Solution.solution(k3, t3) == result3;

    }

    static class Solution {
        static public int solution(int k, int[] tangerine) {
            ArrayList<Long> cntList = new ArrayList<>(
                    Arrays.stream(tangerine)
                            .boxed()
                            .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                            .values()
            );
            cntList.sort(Comparator.reverseOrder());

            AtomicInteger atomicK = new AtomicInteger(k);
            AtomicInteger cnt = new AtomicInteger(0);
            cntList.forEach(c -> {
                if (atomicK.get() > 0) {
                    atomicK.set((int) (atomicK.get() - c));
                    cnt.addAndGet(1);
                }
            });

            return cnt.get();
        }
    }
}
