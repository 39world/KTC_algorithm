/*
https://school.programmers.co.kr/learn/courses/30/lessons/77486?language=java
 */
import java.util.*;

class Scratch {
    public static void main(String[] args) {
        assert Arrays.equals(Solution.solution(new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                        new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                        new String[]{"young", "john", "tod", "emily", "mary"},
                        new int[]{12, 4, 2, 5, 10}),
                new int[]{360, 958, 108, 0, 450, 18, 180, 1080});

        assert Arrays.equals(Solution.solution(new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                        new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                        new String[]{"sam", "emily", "jaimie", "edward"},
                        new int[]{2, 3, 5, 4}),
                new int[]{0, 110, 378, 180, 270, 450, 0, 0});
    }

    static class Solution {

        public static List<Person> personList;
        public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
            //init
            personList = new ArrayList<>();
            //name to id
            HashMap<String, Integer> nameMap = new HashMap<>();
            for (int i = 0; i < enroll.length; i++) {
                nameMap.put(enroll[i], i);
            }

            //create Object & set List
            for (int i = 0; i < enroll.length; i++) {
                int masterId = (referral[i].equals("-")) ? -1 : nameMap.get(referral[i]);
                Person p = new Person(i, masterId);
                personList.add(p);
            }

            for (int i = 0; i < seller.length; i++) {
                int id = nameMap.get(seller[i]);
                int earned = amount[i] * 100;
                exploitation(id, earned);
            }

            return personList.stream().mapToInt(Person::getMoney).toArray();
        }

        public static void exploitation(int slaveId, int earned) {
            Person person = personList.get(slaveId);
            int tax = earned / 10;
            person.setMoney(person.getMoney() + earned - tax);
            if (person.getMasterId() == -1) {
                return;
            }
            if (tax == 0) {
                return;
            }
            Person master = personList.get(person.getMasterId());
            exploitation(master.getId(), tax);
        }

    }

    static class Person {
        int id;
        int masterId;
        int money;

        public Person(int id, int masterId) {
            this.id = id;
            this.masterId = masterId;
        }

        public int getId() {
            return id;
        }

        public int getMasterId() {
            return masterId;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
    }
}
