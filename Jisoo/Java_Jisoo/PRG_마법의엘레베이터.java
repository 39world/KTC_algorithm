
class Scratch {

    public static void main(String[] args) {
        int storey = 154;
        int answer = 10;
        assert Solution.solution(storey) == answer;
        Solution.solution(storey);
    }

    public static class Solution {
        public static int solution(int storey) {
            int answer = 0;

            while (storey > 0) {
                int mod = storey % 10;
                //마지막 자리수가 5보다 큰지
                if(mod>5){
                    storey += (10-mod);
                    storey = storey / 10;
                    answer += (10-mod);
                }
              // 5인지
              else if(mod ==5 ){
                    //5라면 그 앞자리수가 5보다 크거나 같으면 더해주기
                    if(((storey/10)%10) > 5){
                        storey += (10-mod);
                        storey = storey /10;
                        answer += (10-mod);
                    }else{
                        storey -= mod;
                        storey = storey /10;
                        answer += mod;
                    }
                }
              // 5보다 작은지
              else{
                    storey -= mod;
                    storey = storey /10;
                    answer += mod;
                }
            }
            System.out.println(answer);
            return  answer;
          
          //얘넨 array로 쪼개서 풀려다가 edge 가 괴롭..
//            int[] digits = Stream.of(String.valueOf(storey).split(""))
//                    .mapToInt(Integer::parseInt)
//                    .toArray();
//
//            System.out.println(Arrays.toString(digits));
//
//            int n = digits.length;
//
//            for(int i= n-1; i>=0; i--){
//                System.out.println(i);
//                if(digits[i]>5 && i!=0) {
//                    digits[i] = 10 - digits[i];
//                    digits[i - 1] = digits[i - 1] + 1;
//                }else if (i == 0){
//                    if(digits[i]==10){
//                        digits[i] = 1;
//                    }else if(digits[i]>5){
//                        if(digits[i-1]==5){
//                            digits[i] +=1;
//                        }else if(digits[i-1]<5){
//
//                        }
//                        digits[i] = 10 - digits[i];
//                        digits[i]+=1;
//                    }
//
//                }
//            }
//            answer = Arrays.stream(digits).sum();
//            System.out.println(Arrays.toString(digits));
//            System.out.println(answer);
//            return answer;
        }
    }
}
