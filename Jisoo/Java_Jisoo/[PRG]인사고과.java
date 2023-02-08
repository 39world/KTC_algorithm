
import java.util.*;
import java.util.stream.Collectors;

//시간초과
class Scratch {

    public static void main(String[] args) {
        int[][] scores = {{2, 1}, {1, 4}, {3, 2}, {3, 2}, {2, 1}, {3, 1}};
        int answer = -1;
        assert Solution.solution(scores) == answer;
        Solution.solution(scores);
    }

    public static class Solution {
        public static int solution(int[][] scores) {
            int answer = 0;

            int[][] scoresum = new int[scores.length][4];

            //완호 만들기
            scoresum[0][0] = scores[0][0];
            scoresum[0][1] = scores[0][1];
            scoresum[0][2] = scores[0][0] + scores[0][1];
            scoresum[0][3] = 1;
            int[] wan = scoresum[0];

            // 평가1,평가2,합
            for (int i = 1; i < scores.length; i++) {
                scoresum[i][0] = scores[i][0];
                scoresum[i][1] = scores[i][1];
                scoresum[i][2] = scores[i][0] + scores[i][1];
                scoresum[i][3] = 0;
            }
            
            //첫번째 평가는 내림차순, 두번째 평가는 오름차순
            Comparator<int[]> c1 = new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o2[0] > o1[0]) {
                        return 1;
                    } else if (o1[0] == o2[0]) {
                        if (o1[1] >= o2[1]) {
                            return 1;
                        } else {
                            return -1;
                        }
//                        return o1[1].compareTo(o2[1]);
                    } else {
                        return -1;
                    }
                }
            };

            //합계는 내림차순
            Comparator<int[]> c3 = new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o2[2] - o1[2];
                }
            };

            //합계가 같으면 완호가 제일 앞으로 오게
            Comparator<int[]> c4 = new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o2[3] - o1[3];
                }
            };


            List<int[]> scorelist = Arrays.stream(scoresum)
                    .collect(Collectors.toList());

            //합계 정렬 후 완호 인덱스 구하기
            //왜냐면 완호 후에 애들은 비교 안할것임
            Collections.sort(scorelist, c4);
            Collections.sort(scorelist, c3);
            int whichwan = scorelist.indexOf(wan);


            List<int[]> biggerThanWan = scorelist.subList(0, whichwan + 1);

            Collections.sort(biggerThanWan, c1);
            
            //두번째 값 max보다 작으면 앞에잇는거보다 작은거니깐 인센티브 못바덩
            Integer max = 0;
            Integer maxfirst = 0;
            List<int[]> result = new ArrayList<>();
            for (int[] items : biggerThanWan) {
                if (items[1] >= max) {
                    max = items[1];
                    maxfirst = items[0];
                    result.add(items);
                } else {
                    continue;
                }
            }
            if (!result.contains(wan)) {
                System.out.println("-1");
                return -1;
            } else {
                System.out.println(result.size());
                return result.size();
            }
        }}
}
//            List<Boolean> booleans = new ArrayList<>();
//
//            for (int i = 0; i < biggerThanWan.size(); i++) {
//                booleans.clear();
//                for (int j = 0; j < scores.length; j++) {
//                    boolean a = IsBigger(biggerThanWan.get(i), scores[j]);
//                    booleans.add(a);
//                    if (a == false) {
//                        break;
//                    }
//                }
//                if(booleans.contains(false)){
//                    continue;
//                }else {
//                    result.add(biggerThanWan.get(i));
//                }
//            }
//
//            if(!result.contains(wan)){
//                System.out.println("-1");
//                return -1;
//            }else{
//                System.out.println(result.size());
//                return result.size();
//            }
//        }

//        static boolean IsBigger(int[] a, int[] b) {
//            if (a[0] >= b[0] || a[1] >= b[1]) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//            //첫번째요소 내림차순
//            Comparator<List<Integer>> c1 = new Comparator<List<Integer>>() {
//                public int compare(List<Integer> s1, List<Integer> s2) {
//                    return Integer.compare(s2.get(0), s1.get(0));
//                    //내림차순일 경우 return Integer.compare(s2.size(), s1.size());
//                }
//            };
//            //두번째요소는 오름차순
//            Comparator<List<Integer>> c2 = new Comparator<List<Integer>>() {
//                public int compare(List<Integer> s1, List<Integer> s2) {
//                    return Integer.compare(s1.get(1), s2.get(1));
//                    //내림차순일 경우 return Integer.compare(s2.size(), s1.size());
//                }
//            };
//            //합계는 내림차순
//            Comparator<List<Integer>> c5 = new Comparator<List<Integer>>() {
//                public int compare(List<Integer> s1, List<Integer> s2) {
//                    return Integer.compare(s1.get(2), s2.get(2));
//                    //내림차순일 경우 return Integer.compare(s2.size(), s1.size());
//                }
//            };


//            for(List<Integer> i : scorelist){
//                i.add(i.get(0)+i.get(1));
//            }
//
//            Collections.sort(scorelist, c5);
//
//            List<int[]> entries = map.entrySet().stream()
//                    .sorted(Map.Entry.comparingByValue())
//                    .filter(s->s.getValue()>sumWan)
//                    .map(s->s.getKey())
//                    .collect(Collectors.toList());
//
//
//            entries.add(wan);
//
//            List<Integer> result = new ArrayList<>();
//
//            for(int i=0; i<entries.size();i++){
//                for(int j=0; j< scores.length; j++){
//                    boolean a = IsBigger(entries.get(i), scores[j]);
//                    if(a == false){
//                        break;
//                    }
//                }
//                result.add(i);
//            }
//            answer = result.size();
//            System.out.println(result);
//            System.out.println(entries.get(0));
//            return answer;



//            Integer firstMax = Arrays.stream(scores)
//                    .map(s -> s[0]).collect(Collectors.toList()).stream().max(Integer::compare).orElse(0);
//
//
//            Integer secondMax = Arrays.stream(scores)
//                    .map(s -> s[1]).collect(Collectors.toList()).stream().max(Integer::compare).orElse(0);
//
//            List<List<Integer>> compareScore = Arrays.stream(scores)
//                    .filter(s-> s[0]<firstMax).filter(s->s[1]<secondMax)
//                    .map(tmp -> IntStream.of(tmp)
//                            .boxed()
//                            .collect(Collectors.toList())).collect(Collectors.toList());
