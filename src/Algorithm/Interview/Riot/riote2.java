package Algorithm.Interview.Riot;

import java.util.*;

/**
 * Created by program on 10/24/2017.
 */
public class riote2 {
    static class Node {
    String name;
    Node parent;
    long birth;
    long death;
    List<Node> child;
    public Node(String name, String birth, String death) {
        this.name = name;
        this.parent = null;
        this.birth = getDays(birth);
        this.death = getDays(death);
        this.child = new ArrayList<>();
    }

    public long getDays(String str) {
        String [] s = str.split("-");
        return (long)(Long.parseLong(s[0]) * 365.25 + Long.parseLong(s[1]) * 30 + Long.parseLong(s[2]));
    }
}
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        //read the input
        Scanner read = new Scanner(System.in);
        List<String> input = new ArrayList<>();
//        if(read.hasNext()) {
//            read.nextLine();
//        }
//        while(read.hasNextLine()) {
//            input.add(read.nextLine());
//        }
//
        input.add("GRAYBEARD,,1872-12-10,1907-11-15");
        input.add("THORIN,GRAYBEARD,1903-09-29,1922-05-13");
        input.add("ATHENA,GRAYBEARD,1906-10-13,1926-12-20");
        input.add("TRITON,ATHENA,1913-10-01,1952-01-12");
        input.add("ALEXTHANIA,,1072-01-05,1207-07-14");
        input.add("GLORAX,ALEXTHANIA,1105-02-17,1317-03-21");
        input.add("SNEERLAX,ALEXTHANIA,1110-10-18,1298-04-16");
        input.add("ORGANAX,GLORAX,1229-12-01,1415-11-30");









        //create a hashmap
        //key : the name of the person
        //value: the node of the person
        HashMap<String, Node> map = new HashMap<>();

        //create the nodes
        for(int i = 0; i < input.size(); i ++) {
            String [] s = input.get(i).split(",");
            map.put(s[0], new Node(s[0], s[2], s[3]));
        }


        //connect the nodes, and record the roots
        List<Node> roots = new ArrayList<>();
        for(int i = 0; i < input.size(); i ++) {
            String [] s = input.get(i).split(",");
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
        long[] globalLong = new long[1];
        Node[] globalNode = new Node[1];
        for(int i = 0; i < roots.size(); i ++) {
            globalLong[0] = Long.MIN_VALUE;
            globalNode[0] = null;

            Node root = roots.get(i);
            System.out.print(root.name);
            System.out.print(",");
            longestArrow(root, globalLong, globalNode);
            System.out.print(globalNode[0].name);
            System.out.print(",");
            System.out.println((int)((globalNode[0].death - root.birth) / 365.25));

        }


    }
    //return path

    public static void longestArrow(Node root, long [] globalLong, Node[] globalNode) {
        if(root.child.size() == 0) {
            if(root.death > globalLong[0]) {
                globalLong[0] = root.death;
                globalNode[0] = root;
            }
            return;
        }


        if(root.death > globalLong[0]) {
            globalLong[0] = root.death;
            globalNode[0] = root;
        }

        for(Node n : root.child) {
            longestArrow(n, globalLong, globalNode);
        }
    }
//    public static int differentDaysByMillisecond(Calendar date1, Calendar date2){
//        int days = (int) ((date2.getTime().getTime() - date1.getTime().getTime()) / (1000*3600*24) / 85.1 * 79);
//        return days;
//    }



    }

