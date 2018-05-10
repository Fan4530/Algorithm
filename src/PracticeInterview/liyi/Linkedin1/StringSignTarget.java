package Practical.liyi.Linkedin1;

import java.util.*;

/**
 * Created by program on 1/12/2018.
 */
public class StringSignTarget {
    //优化： 可以用一个hashmap<String, List<Double>> 给存下来
    //比如说1 2 3 ,   3 --> 3, -3
    //               2 3 --> 2 + 3， 2 / 3, 2 - 3, 2 * 3 ...
    // 如果下一次，遇到sign == + 或者 - ， 就直接取后面的map.get(), 直接拿过来用
    public static void main(String [] agrs) {
        StringSignTarget sol = new StringSignTarget();
        System.out.println(sol.StringSignTarget("123", 24));
    }
    public boolean StringSignTarget(String s, int target) {
        HashMap<String, Set<Integer>> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        return dfs(1, target, s, sb.append(s.charAt(0)));
    }

    public Double getValue(String s) {
        if(s == null || s.length() == 0) {
            return 0.0;
        }
        Double curNum = 0.0;
        Deque<Character> op = new LinkedList<>();
        Deque<Double> number = new LinkedList<>();
        for(int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                curNum = curNum * 10 + c - '0';
            } else if (c == ' ') {
                continue;
            } else {
                //push the number and clear it up
                number.offerFirst(curNum);
                curNum = 0.0;
                if(c == '+' || c == '-') {
                    while(!op.isEmpty()) {
                        calculate(op, number);
                    }
                }
                // push the sign
                op.offerFirst(c);
            }
        }
        number.offerFirst(curNum);
        while(!op.isEmpty()) {
            calculate(op, number);
        }
        return number.pollFirst();
    }
    private double calculate(Deque<Character> op, Deque<Double> number) {
        char sign = op.pollFirst();
        double second = number.pollFirst();
        double first = number.pollFirst();
        if(sign == '+') {
            number.offerFirst(first + second);
        } else if(sign == '-'){
            number.offerFirst(first - second);
        } else if(sign == '*') {
            number.offerFirst(first * second);
        } else if(sign == '/') {
            number.offerFirst(first / second);
        } else if(sign == '^') {
            number.offerFirst(Math.pow(first, second));
        }
        return number.peekFirst();
    }
    private String [] sign = new String[]{"+", "-", "*", "/", ""};
    private boolean dfs(int idx, int target, String s, StringBuilder sb) {
        if(idx == s.length()) {
            if(getValue(sb.toString()) == target) {
                return true;
            }
            return false;
        }
        for(int i = 0; i < sign.length; i ++) {
            if(s.charAt(idx) == '0' && sign[i].equals("/")) {
                continue;
            }
            int len = sb.length();
            sb.append(sign[i]).append(s.charAt(idx));
            if(dfs(idx + 1, target, s, sb)) {
                // it should be setLength(), but because we directly return true and no other operation,
                // so we don't have to add remove operation
                return true;
            }
            sb.setLength(len);
        }
        return false;
    }
}
