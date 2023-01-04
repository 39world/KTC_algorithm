class Scratch {

    public static void main(String[] args) {

        int n1 = 11;
        int[] s1 = {4, 11};
        int w1 = 1;
        int answer1 = 3;
        assert Solution.solution(n1, s1, w1) == answer1;


        int n2 = 16;
        int[] s2 = {9};
        int w2 = 2;
        int answer2 = 3;
        assert Solution.solution(n2, s2, w2) == answer2;

    }

    static class Solution {
        static public int solution(int n, int[] stations, int w) {
            //solution : station이 없는 sublist들에 최소로 설치
            //List<Boolean> 만들고있었는데 쓰다보니 그냥 수학문제인것같음
            int start = 1;
            int answer = 0;
            for (int station : stations) {
                if (start < station - w) {
                    answer += counting(start, station - w - 1, w);
                }
                start = station + w + 1;
            }
            if (stations[stations.length - 1] + w < n) { //마지막 sublist 처리가 필요하면
                answer += counting(stations[stations.length - 1] + w + 1, n, w);
            }
            return answer;
        }

        private static int counting(int start, int end, int w) {
            int cnt = (end - start + 1) / (w * 2 + 1);
            return ((end - start + 1) % (w * 2 + 1) != 0) ? cnt + 1 : cnt;
        }
    }
}
