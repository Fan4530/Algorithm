package Algorithm.Interview.Linkedin;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by program on 12/8/2017.
 */
public class Polish {
    public static void main(String [] args) {
        evalRPN(new String[]{"3", "-4", "+"});
        String one = "there";
        String two = "the";
        System.out.println(one.indexOf(two));
    }
    public static int evalRPN(String[] tokens) {
        // int one = stack.pollFirst();
        // int two = stack.pollFirst();
        // if(c =='/' ) {
        //     one / two   push back
        // }
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < tokens.length; i ++) {
            String s = tokens[i];
            if(s.equals("+")) {
                stack.offerFirst(stack.pollFirst() + stack.pollFirst());
            } else if(s.equals("-")) {
                int one = stack.pollFirst();
                int two = stack.pollFirst();
                stack.offerFirst(two - one);
            } else if(s.equals("*")) {
                stack.offerFirst(stack.pollFirst() * stack.pollFirst());
            } else if(s.equals("/")) {
                int one = stack.pollFirst();
                int two = stack.pollFirst();
                stack.offerFirst(two / one);
            } else {
                stack.offerFirst(getInt(s));
            }
        }
        return stack.pollFirst();
    }
    private static int getInt(String s) {
        int sum = 0;
        int start = 0;
        if(s.charAt(0) == '-') {
            start = 1;
        }
        for(int i = start; i < s.length(); i ++) {
            sum = sum * 10 + s.charAt(i) - '0';
        }
        return start == 1 ? - sum: sum;
    }
}
