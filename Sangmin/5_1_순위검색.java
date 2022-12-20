/*
https://school.programmers.co.kr/learn/courses/30/lessons/72412
 */
import java.util.*;

class Scratch {
    public static void main(String[] args) {
        int[] result = {1, 1, 1, 1, 2, 4};
        String[] info = {
                "java backend junior pizza 150",
                "python frontend senior chicken 210",
                "python frontend senior chicken 150",
                "cpp backend senior pizza 260",
                "java backend junior chicken 80",
                "python backend senior chicken 50"};
        String[] query = {
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150"};

        int[] solution = Solution.solution(info, query);
        for (int i = 0; i < query.length; i++) {
            assert solution[i] == result[i];
        }
    }

    static class Solution {
        static HashMap<String, List<Integer>> map = new HashMap<>();

        static public int[] solution(String[] info, String[] query) {
            //info
            for (String i : info) {
                setMap(0, "", i.split(" "));
            }

            //sort
            //효율 탈락 3트 -> 성공
            map.values().forEach(v ->{
                v.sort(Integer::compare);
            });

            //query
            int[] result = new int[query.length];
            for (int i = 0; i < query.length; i++) {
                String q = query[i];
                int lastIndexOf = q.lastIndexOf(" ");
                int score = Integer.parseInt(q.substring(lastIndexOf + 1));
                q = q.substring(0, lastIndexOf)
                        .replaceAll(" and ", "")
                        .replaceAll(" ", "");
                List<Integer> scores = map.getOrDefault(q, new ArrayList<>());
                /*
                효율 탈락 1트 -> 1234 실패
                long count = scores.stream()
                        .sorted()
                        .dropWhile(cutLine -> cutLine < score)
                        .count();*/

                //binary search
                int count = findWithBinarySearch(scores, score);
                result[i] = count;
            }
            return result;
        }

        private static int findWithBinarySearch(List<Integer> scores, int score) {
            //효율 탈락 2트 -> 12 실패
            //scores.sort(Integer::compare);
            int start = 0;
            int end = scores.size();
            int now = 0;
            while (start < end) {
                now = (start + end) / 2;
                if (scores.get(now) < score) start = now + 1;
                else end = now;
            }
            return scores.size() - start;
        }

        static void setMap(int index, String str, String[] oneInfo) {
            if (index == 4) {
                map.computeIfAbsent(str, k -> new ArrayList<>())
                        .add(Integer.parseInt(oneInfo[4]));
                return;
            }
            setMap(index + 1, str + oneInfo[index], oneInfo);
            setMap(index + 1, str + "-", oneInfo);
        }
    }
}