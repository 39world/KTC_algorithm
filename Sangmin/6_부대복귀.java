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
        static List<Boolean> visitedList; //방문 여부 저장

        static public int[] solution(int n, int[][] roads, int[] sources, int destination) {
            //init
            map = new HashMap<>();
            distList = new ArrayList<>(n);
            visitedList = new ArrayList<>(n);
            for (int i = 0; i < n + 1; i++) {
                distList.add(Integer.MAX_VALUE); //최대 거리로 초기화
                visitedList.add(false);
            }

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
                    .mapToInt(i -> {
                        if (i != Integer.MAX_VALUE) {
                            return i;
                        } else {
                            return -1;
                        }
                    }).toArray();
        }

        private static void bfsByPoint(int destination) {
            Queue<Point> jobQueueByNum = new LinkedList<>();

            //init
            distList.set(destination, 0);
            jobQueueByNum.add(new Point(destination, 0));
            while (!jobQueueByNum.isEmpty()) {
                Point now = jobQueueByNum.poll();

                //방문 확인 -> 스킵 or 방문
                if (visitedList.get(now.getNumber())) {
                    continue;
                }
                visitedList.set(now.getNumber(), true);

                //거리 확인 -> 스킵 or 저장
                if (distList.get(now.getNumber()) < now.getDistance()) {
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
    //<editor-fold desc="실패함ㅠ">
    /*
    static class Solution {
        static List<Point> points;

        static public int[] solution(int n, int[][] roads, int[] sources, int destination) {
            //init
            points = new ArrayList<>();

            //set points
            for (int i = 0; i < n; i++) {
                points.add(new Point(i));
            }
            Arrays.stream(roads).forEach(road -> {
                points.get(road[0] - 1).addNearPoint(road[1] - 1);
                points.get(road[1] - 1).addNearPoint(road[0] - 1);
            });

            //get results
            List<Integer> results = new ArrayList<>();
            for (int source : sources) {
                results.add(comeback(new boolean[n], source - 1, destination - 1, 0));
            }
            return results.stream().mapToInt(i -> i).toArray();
        }

        private static int comeback(boolean[] visited, int start, int end, int cnt) {
            //도착
            if (start == end) return cnt;
            //고립
            Point point = points.get(start);
            List<Integer> notVisitedPoints = point.getNotVisitedPoint(visited);
            if (notVisitedPoints.size() == 0) return -1;
            //ㄱㄱ
            List<Integer> allWay = new ArrayList<>();
            visited[start] = true;
            for (Integer notVisitedPoint : notVisitedPoints) {
                int comeback = comeback(visited, notVisitedPoint, end, cnt + 1);
                allWay.add(comeback);
            }
            AtomicInteger result = new AtomicInteger(-1);
            allWay.stream().filter(w -> w != -1)
                    .min(Integer::compareTo)
                    .ifPresent(result::set);
            return result.get();
        }

        static class Point {
            int num;
            List<Integer> nearPoint;

            public Point(int num) {
                this.num = num;
                this.nearPoint = new ArrayList<>();
            }

            public void addNearPoint(int nearNum) {
                this.nearPoint.add(nearNum);
            }

            public List<Integer> getNotVisitedPoint(boolean[] visited) {
                List<Integer> notVisited = new ArrayList<>();
                for (Integer near : nearPoint) {
                    if (!visited[near]) {
                        notVisited.add(near);
                    }
                }
                return notVisited;
            }

            @Override
            public String toString() {
                return "Point{" +
                        "num=" + num +
                        ", nearPoint=" + nearPoint +
                        '}';
            }
        }
        */
    //</editor-fold>
    //<editor-fold desc="또실패함ㅠㅠ">
    /*class Solution {

        static List<Point> points;

        static public int[] solution(int n, int[][] roads, int[] sources, int destination) {
            //set points
            points = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                points.add(new Point(i));
            }
            Arrays.stream(roads).forEach(road -> {
                System.out.println("road = " + Arrays.toString(road));
                points.get(road[0] - 1).addNearPoint(road[1] - 1);
                points.get(road[1] - 1).addNearPoint(road[0] - 1);
            });
            points.forEach(System.out::println);
            bfs(destination);

            System.out.println("results------------");
            points.forEach(System.out::println);

            List<Integer> resultList = new ArrayList<>();
            for (int source : sources) {
                resultList.add(points.get(source - 1).getWeight());
            }
            return resultList.stream()
                    .mapToInt(i -> {
                        if (i != Integer.MAX_VALUE) return i;
                        else return -1;
                    }).toArray();
        }

        private static void bfs(int start) {
            Queue<Point> bfsQ = new LinkedList<>();

            //init
            Point now = points.get(start - 1);
            bfsQ.add(now);
            now.setWeight(0);
            int dist = now.getWeight();

            while (!bfsQ.isEmpty()) {
                System.out.println("start Q : " + bfsQ);

                now = bfsQ.poll();
                if (now.getNum() == -1) {
                    dist++;
                    System.out.println("\nQUEUE ENDED! DIST UP to " + dist + "\n");
                }else{
                    bfsQ.add(new Point(-1, true));
                    System.out.println("now " + now + " / dist " + dist);
                    System.out.println("added Q : " + bfsQ);
                }

                if (now.isVisited()) {
                    continue;
                }
                now.setVisited(true);

                if (now.getWeight() > dist) {
                    now.setWeight(dist);
                }

                now.getNearPointIndexList().forEach(nextIndex -> {
                    bfsQ.add(points.get(nextIndex));
                });
            }
        }

        static class Point {
            int num;
            int weight;
            List<Integer> nearPointIndexList;
            boolean visited;

            public Point(int num) {
                this.num = num;
                this.weight = Integer.MAX_VALUE;
                this.nearPointIndexList = new ArrayList<>();
                this.visited = false;
            }

            public Point(int num, boolean visited) {
                this.num = num;
                this.weight = Integer.MAX_VALUE;
                this.nearPointIndexList = new ArrayList<>();
                this.visited = visited;
            }

            public int getNum() {
                return num;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public int getWeight() {
                return weight;
            }

            public boolean isVisited() {
                return visited;
            }

            public void setVisited(boolean visited) {
                this.visited = visited;
            }

            public void addNearPoint(int nearNum) {
                this.nearPointIndexList.add(nearNum);
            }

            public List<Integer> getNearPointIndexList() {
                return nearPointIndexList;
            }

            @Override
            public String toString() {
                return "Point{" +
                        "num=" + num +
                        ", weight=" + weight +
                        ", nearPointIndexList=" + nearPointIndexList +
                        ", visited=" + visited +
                        '}';
            }
        }
    }*/
    //</editor-fold>
}
