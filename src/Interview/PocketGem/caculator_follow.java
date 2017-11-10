package Interview.PocketGem;

import java.util.*;
public class caculator_follow {
    public static void main(String[] args) {
        String s = "3 + 2 * 4 ^ 2 ^ 2 - 4 / 2";
        System.out.println(caclulator(s));
    }
    // s = "3 + 2 * 4 ^ 2 ^ 2 / 4 - 4 /2"
    public static int caclulator(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        Map<Character, Integer> map = new HashMap<>();
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
        map.put('^', 3);
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } 
            else if(c == ' ')
            	continue;
            else {
                numStack.push(num);
                num = 0;
                if (opStack.size() == 0 || map.get(c) > map.get(opStack.peek())) {
                    opStack.push(c);
                } else {
                    while (!opStack.isEmpty() && map.get(c) <= map.get(opStack.peek())) {
                        helper(numStack, opStack);
                    }
                    opStack.push(c);
                }
            }
        }
        numStack.push(num);
        while (!opStack.isEmpty()) helper(numStack, opStack);
        return numStack.pop();
    }
    public static void helper(Stack<Integer> numStack, Stack<Character> opStack) {
        int numL = numStack.pop();
        int numF = numStack.pop();
        char op = opStack.pop();
        if (op == '+') numStack.push(numF + numL);
        if (op == '-') numStack.push(numF - numL);
        if (op == '/') numStack.push(numF / numL);
        if (op == '*') numStack.push(numF * numL);
        if (op == '^') numStack.push((int) Math.pow(numF, numL));
    }
}
