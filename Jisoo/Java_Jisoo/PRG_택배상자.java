import java.util.Stack;

class Scratch {
    public static void main(String[] args) {
        int[] order1 = {4, 3, 1, 2, 5};
        int result1 = 2;
        assert Solution.solution(order1) == result1;

        int[] order2 = {5, 4, 3, 2, 1};
        int result2 = 5;
        assert Solution.solution(order2) == result2;
        Solution.solution(order2);

    }
    //재귀로 풀려다가,, java recursion limit 이 10000이라며?
//    public static int calculate(Stack<Integer> mainConv, Stack<Integer> secondConv, int item){
//        if(mainConv.size()!=0 && mainConv.peek()==item){
//            mainConv.pop();
//            return 1;
//        }else if(secondConv.size()!=0 && secondConv.peek()==item){
//            secondConv.pop();
//            return 1;
//        }else {
//            if (mainConv.size() != 0) {
//                secondConv.push(mainConv.pop());
//                return calculate(mainConv, secondConv, item);
//            } else {
//                return -1;
//            }
//        }
//    }

    public static class Solution {
        public static int solution(int[] order) {
            int answer = 0;

            // 전체 택배 갯수 구하기
            int size = order.length;

            // 메인 컨베이어, 보조 컨베이어 만들기
            Stack<Integer> mainConv = new Stack<>();
            Stack<Integer> secondConv = new Stack<>();

            // 메인 컨베이어에 순서
            for(int i =size; i>0; i--){
                mainConv.push(i);
            }

            int i = 0;
            while(true){
                if(mainConv.size()!=0 &&mainConv.peek()==order[i]){
                    answer +=1;
                    mainConv.pop();
                    i+=1;
                }else if(secondConv.size()!=0 && secondConv.peek()==order[i]){
                    answer +=1;
                    secondConv.pop();
                    i+=1;
                }else {
                    if (mainConv.size() != 0) {
                        secondConv.push(mainConv.pop());
                    } else {
                        break;
                    }
            }}
            return answer;
        }

    }

}
