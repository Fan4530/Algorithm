package Practical.Riot;

import java.util.*;

/**
 * Created by program on 10/24/2017.
 */

public class RiotGame {

    static class Node {
        String name;
        Node parent;
        Calendar birth;
        Calendar death;
        List<Node> child;
        public Node(String name, String birth, String death) {
            this.name = name;
            this.parent = null;
            this.birth = getData(birth);
            this.death = getData(death);
            this.child = new ArrayList<>();
        }
        public Calendar getData(String str) {
            String [] date = str.split("-");
            //date[0] month,  date[1] day,  date[2]   year
            return new GregorianCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[2]));
        }
    }
    public static void main(String [] args) {
        //read the input
        Scanner read = new Scanner(System.in);
        String [] input = read.nextLine().split(" ");

        //create a hashmap
        //key : the name of the person
        //value: the node of the person
        HashMap<String, Node> map = new HashMap<>();
        //create the nodes
        for(int i = 0; i < input.length; i ++) {
            String [] s = input[i].split(",");
            map.put(s[0], new Node(s[0], s[2], s[3]));
        }
        //connect the nodes, and record the roots
        List<Node> roots = new ArrayList<>();
        for(int i = 0; i < input.length; i ++) {
            String [] s = input[i].split(",");
            //map.get(s[1]): parent Node
            //map.get(s[0]): cur Node
            Node p = map.get(s[1]);
            if(p != null) {
                map.get(s[0]).parent = p;
                map.get(s[1]).child.add(map.get(s[0]));
            } else {
                roots.add(map.get(s[0]));
            }
        }

        //Get the largestPath
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < roots.size(); i ++) {
            longestArrow(roots.get(i), sb);
        }
        System.out.println("Longest Path");
        System.out.println(longestPath);

        //Get the root of the longest Path
        String [] longestPathArray = longestPath.split("->");
        Node longestPathRoot = map.get(longestPathArray[0]);

        //calculate the one who is the last to die, the diff between its death time and the root's birth time is the longestTime
        Node largestDeath = longestTime(longestPathRoot);
        long largestDiffDays = differentDaysByMillisecond(longestPathRoot.birth, largestDeath.death);
        //TODO: CONVERT to year
        System.out.println("LargestDiffDays");
        System.out.println(largestDiffDays);

        //Get the amount of the members, amount of ages, amount of generation in the family that has the longest path
        System.out.println(amountOfMember(longestPathRoot));
        System.out.println(amountOfAge(longestPathRoot));
        System.out.println(amountOfGeneration());

//        Calendar cal = Calendar.getInstance();
//        Node n1 = new Node("zhangfan", "02/01/2017", "02/01/2018");
//        System.out.println(differentDaysByMillisecond(n1.birth,n1.death));
        //System.out.println(date.getTime());
    }

    //return path
    private static long globalLong = 0;
    private static String longestPath = "";
    public static void longestArrow(Node root, StringBuilder sb) {
        if(root.child.size() == 0) {
            sb.append(root.name);
            if(root.death.getTimeInMillis() > globalLong) {
                longestPath = sb.toString();
                globalLong = root.death.getTimeInMillis();
            }
        }
        int len = sb.length();
        sb.append(root.name);
        sb.append("->");
        for(Node n : root.child) {
            longestArrow(n, sb);
        }
        sb.setLength(len);
    }
    //A,B,02/01/1900,02/01/1905
    //B,C,02/01/1901,02/01/1903
    //C,NULL,02/01/1902,02/01/1904
    //D,C,02/01/1900,02/01/1909

    //E,F,02/01/1900,02/01/1910
    //unit: days
    public static Node longestTime(Node root) {
        if(root == null) {
            return root;
        }
        Node node = root;
        long longest = root.death.getTimeInMillis();
        for(Node n : root.child) {
            if(n.death.getTimeInMillis() > longest) {
                longest = n.death.getTimeInMillis();
                node = n;
            }
        }
        return node;
    }

    /**
     *
     * @return day difference between date1 and date2
     */
    public static int differentDaysByMillisecond(Calendar date1, Calendar date2)
    {
        int days = (int) ((date2.getTime().getTime() - date1.getTime().getTime()) / (1000*3600*24));
        return days;
    }

    /**
     * @return amount of member number
     */
    public static int amountOfMember(Node root) {
        if(root == null) {
            return 0;
        }
        int count = 0;
        for(Node n : root.child) {
            count += amountOfMember(n);
        }
        return count + 1;
    }

    /**
     * according to the root has longest path, the first one is the root
     * @return
     */
    public static int amountOfGeneration() {
        return longestPath.split("->").length;
    }

    /**
     * @return the amount of ages
     */
        public static long amountOfAge(Node root) {
            if(root == null) {
                return 0;
            }
            long age = 0;
            for(Node n : root.child) {
                age += amountOfAge(n);
            }
            return age + differentDaysByMillisecond(root.birth, root.death) / 365;
        }
//    1,2,02/01/1900,02/01/1905 1,3,02/01/1901,02/01/1903 1,4,02/01/1902,02/01/1904 2,5,02/01/1900,02/01/1909 2,6,02/01/1900,02/01/1910 3,7,02/01/1900,02/01/1905 4,8,02/01/1901,02/01/1903 4,9,02/01/1902,02/01/1904 4,10,02/01/1900,02/01/1909 4,11,02/01/1900,02/01/1910 7,12,02/01/1900,02/01/1905 9,13,02/01/1901,02/01/1903 9,14,02/01/1902,02/01/1904 11,15,02/01/1900,02/01/1909 12,16,02/01/1900,02/01/1910 14,17,02/01/1900,02/01/1905 14,18,02/01/1901,02/01/1903
}
