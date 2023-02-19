import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

class Scratch {
    public static void main(String[] args) {
        //https://school.programmers.co.kr/questions/20888
        String[][] relation1 = {{"a", "1", "aaa", "c", "ng"},
                {"a", "1", "bbb", "e", "g"},
                {"c", "1", "aaa", "d", "ng"},
                {"d", "2", "bbb", "d", "ng"}};
        int result1 = 5;

        assert Solution.solution(relation1) == result1;
    }

    static List<String> combList = new ArrayList<>();

    static class Solution {
        static public int solution(String[][] relation) {
            int columnLength = relation[0].length; //세로줄 개수
            int rowLength = relation.length; //가로줄 개수

            //모든 combination 획득
            for (int i = 1; i <= columnLength; i++) {
                setAllCombinations(0, i, columnLength, new boolean[columnLength]);
            }

            List<String> resultStrList = new ArrayList<>();
            //조합 별
            for (String comb : combList) {
                List<Integer> combIndexList = new ArrayList<>();
                for (int i = 0; i < comb.length(); i++) {
                    combIndexList.add(Integer.parseInt(String.valueOf(comb.charAt(i))));
                }
                HashSet<String> groupSet = new HashSet<>();
                //유일성 확인
                for (int j = 0; j < rowLength; j++) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < combIndexList.size(); i++) {
                        Integer columnIndex = combIndexList.get(i);
                        sb.append(relation[j][columnIndex] + "+");
                    }
                    String str = sb.toString();
                    str = str.substring(0, str.length() - 1);
                    groupSet.add(str);
                }
                if (groupSet.size() == rowLength) {
                    StringBuilder indexSb = new StringBuilder();
                    combIndexList.forEach(index -> indexSb.append(index).append("+"));
                    resultStrList.add(indexSb.substring(0, indexSb.length() - 1));
                }
            }
            for (int i = 0; i < resultStrList.size(); i++) {
                for (int j = 0; j < resultStrList.size(); j++) {
                    String[] jSplit = resultStrList.get(j).split("\\+");
                    String[] iSplit = resultStrList.get(i).split("\\+");
                    AtomicBoolean isContains = new AtomicBoolean(true);

                    for (int i1 = 0; i1 < iSplit.length; i1++) {
                        int finalI = i1;
                        boolean b = Arrays.stream(jSplit).anyMatch(jc -> jc.equals(iSplit[finalI]));
                        isContains.set(isContains.get() && b);
                    }
                    if(isContains.get()){
                        resultStrList.set(j, resultStrList.get(i));
                    }
                }
            }
            resultStrList = resultStrList.stream().distinct().collect(Collectors.toList());

            return resultStrList.size();
        }

        static public void setAllCombinations(int start, int cnt, int columnLength, boolean[] visited) {
            if (cnt == 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < columnLength; i++) {
                    if (visited[i]) {
                        sb.append(i);
                    }
                }
                combList.add(sb.toString());
            }
            for (int i = start; i < columnLength; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    setAllCombinations(start + 1, cnt - 1, columnLength, visited);
                    visited[i] = false;
                }
            }
        }
    }
}
