import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Scratch {
    public static void main(String[] args) {

        int n1 = 11;
        int[] stations1 = {4,11};
        int w1 = 1;
        int result1 = 3;

        Solution.solution(n1,stations1,w1);

        int n2 = 16;
        int[] stations2 = {9};
        int w2 = 2;
        int result2 = 3;

        Solution.solution(n2,stations2,w2);
//        assert Solution.solution(n1, stations1, w1) == result1;

        int n3 = 10;
        int[] stations3 = {2,6};
        int w3 = 1;
        int result3 = 1;
        Solution.solution(n3,stations3,w3);
    }
//    public static class Calculate{
//        public static double calculate(int k, int w){
//            double temp = k;
//            if(temp==0){
//                return 0;
//            }else{
//                double divisor = 2*w+1;
//                return Math.ceil(temp/divisor);
//            }
//        }
//    }
    public static class Solution {
        public static double calculate(int k, int w){
            double temp = k;
            if(temp==0){
                return 0;
            }else{
                double divisor = 2*w+1;
                return Math.ceil(temp/divisor);
            }
        }
        public static int solution(int n, int[] stations, int w) {
            int answer = 0;

            List<Integer> size = new ArrayList<>();
            for(int i=0; i<stations.length; i++){
                if(stations.length==1){
                    answer += calculate(Math.max(0,(stations[0]-w-1)), w);
                    answer += calculate(Math.max(0,n-stations[i]-w), w);
                    break;
                }
                if(i==0){
                    answer += calculate(Math.max(0,(stations[0]-w-1)), w);
                }else if (i==(stations.length-1)) {
                    if(n>(stations[i]+w)){
                        answer += calculate(Math.max(0, stations[i] - stations[i - 1] - 2 * w - 1),w);
                        answer += calculate(Math.max(0,n-stations[i]-w),w);
                    }else{
                        answer += calculate(Math.max(0, stations[i] - stations[i - 1] - 2 * w - 1),w);
                    }
                }else{
                    answer += calculate(Math.max(0,(stations[i]-stations[i-1]-2*w-1)),w);
                }
            }
            return answer;
        }

    }
}
