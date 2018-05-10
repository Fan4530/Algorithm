package Practical.liyi;

import java.util.Deque;
import java.util.LinkedList;

public class PolishNotation {
//	//  stack
//
//	//  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
//		 stack: 2 1 '+' --> 3 3 * -->
//  	//  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
//
	// assumption: 
	// the input is a string array
    public int polishNotation(String [] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        
        for(int i = 0; i < tokens.length; i ++) {
            String s = tokens[i];
            if(s.equals("+")) {
                stack.offerFirst(stack.pollFirst() + stack.pollFirst());
            } else if(s.equals("*")) {
                stack.offerFirst(stack.pollFirst() * stack.pollFirst());
            } else if(s.equals("-")) {// because we need to let the later number num2 minus the former number num1, so 
                int num1 = stack.pollFirst();
                int num2 = stack.pollFirst();// later number as the first 
                stack.offerFirst(num2 - num1);
            } else if(s.equals("/")) {
                int num1 = stack.pollFirst();
                int num2 = stack.pollFirst();
                stack.offerFirst(num2 / num1);
            } else {
                stack.offerFirst(Integer.parseInt(s));
            }
        }
        return stack.pollFirst();
    }
}