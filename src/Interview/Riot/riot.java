package Interview.Riot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by program on 10/24/2017.
 */
public class riot {
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
        if(read.hasNext()) {
            read.nextLine();
        }
        while(read.hasNext()) {
            input.add(read.nextLine());
        }


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
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < roots.size(); i ++) {
            longestArrow(roots.get(i), sb);
        }
        //System.out.println("Longest Path");
        System.out.println(longestPath);

    }
    //return path
    private static long globalLong = 0;
    private static String longestPath = "";
    public static void longestArrow(Node root, StringBuilder sb) {
        if(root.child.size() == 0) {
            int len = sb.length();
            sb.append(root.name);
            if(root.death > globalLong) {
                longestPath = sb.toString();
                globalLong = root.death;
            }
            sb.setLength(len);
            return;
        }

        int len = sb.length();
        sb.append(root.name);

        if(root.death > globalLong) {
            longestPath = sb.toString();
            globalLong = root.death;
        }

        sb.append(" -> ");
        for(Node n : root.child) {
            longestArrow(n, sb);
        }
        sb.setLength(len);
    }
}
