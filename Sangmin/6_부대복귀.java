import java.util.*;

class Scratch {
    public static void main(String[] args) {

        int n1 = 3;
        int[][] r1 = {{1, 2}, {2, 3}};
        int[] s1 = {2, 3};
        int d1 = 1;
        int[] result1 = {1, 2};
        int[] solution1 = Solution.solution(n1, r1, s1, d1);
        assert result1.length == solution1.length;
        for (int i = 0; i < solution1.length; i++) {
            assert solution1[i] == result1[i];
        }
        for (int i = 0; i < result1.length; i++) {
            assert result1[i] == solution1[i];
        }

        int n2 = 5;
        int[][] r2 = {{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}};
        int[] s2 = {1, 3, 5};
        int d2 = 5;
        int[] result2 = {2, -1, 0};
        int[] solution2 = Solution.solution(n2, r2, s2, d2);
        assert result2.length == solution2.length;
        for (int i = 0; i < solution2.length; i++) {
            assert solution2[i] == result2[i];
        }
        for (int i = 0; i < result2.length; i++) {
            assert result2[i] == solution2[i];
        }
    }

    static class Solution {
        static Map<Integer, List<Integer>> map; //인접 노드 정보
        static List<Integer> distList; //거리 정보 저장

        static public int[] solution(int n, int[][] roads, int[] sources, int destination) {
            //init
            map = new HashMap<>();
            distList = new ArrayList<>(Collections.nCopies(n + 1, Integer.MAX_VALUE));//최대 거리로 초기화

            //map 정보 저장
            Arrays.stream(roads).forEach(road -> {
                map.computeIfAbsent(road[0], k -> new ArrayList<>())
                        .add(road[1]);
                map.computeIfAbsent(road[1], k -> new ArrayList<>())
                        .add(road[0]);
            });

            //destination 노드 기준으로 거리 정보 저장
            bfsByPoint(destination);

            //source 기준 distance 값 return
            List<Integer> returnList = new ArrayList<>();
            for (int source : sources) {
                returnList.add(distList.get(source));
            }
            return returnList.stream()
                    .mapToInt(i -> (i != Integer.MAX_VALUE) ? i : -1).toArray();

        }

        private static void bfsByPoint(int destination) {
            //init
            Queue<Point> jobQueueByNum = new LinkedList<>();
            jobQueueByNum.add(new Point(destination, 0));

            while (!jobQueueByNum.isEmpty()) {
                Point now = jobQueueByNum.poll();

                //거리(방문여부) 확인 -> 스킵 or 저장
                if (distList.get(now.getNumber()) != Integer.MAX_VALUE &&
                        distList.get(now.getNumber()) < now.getDistance()) {
                    continue;
                }
                distList.set(now.getNumber(), now.getDistance());

                //큐에 인접노드 저장
                map.get(now.getNumber())
                        .forEach(next -> jobQueueByNum.add(new Point(next, now.distance + 1)));
            }
        }

        static class Point {
            int number;
            int distance;

            public Point(int number, int distance) {
                this.number = number;
                this.distance = distance;
            }

            public int getNumber() {
                return number;
            }

            public int getDistance() {
                return distance;
            }
        }
    }
}
