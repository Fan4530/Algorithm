package Interview.PocketGem;

import java.util.*;
public class caculator {
    public int calculate_stack(String s) {
        if(s == null || s.length() == 0)
            return 0;
        char sign = '+';
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            if(Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == s.length()-1) {
                if(sign == '+') {
                    stack.push(num);
                }
                if(sign == '-') {
                    stack.push(-num);
                }
                if(sign == '*') {
                    stack.push(stack.pop() * num);
                }
                if(sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
        int res = 0;
        for(int i : stack) {
            res += i;
        }
        return res;
    }
    public int calculate_withoutStack(String s) {
        if(s == null || s.length() == 0)
            return 0;
        char sign = '+';
        int num = 0;
        int pre = 0;
        int res = 0;
        for(int i = 0; i < s.length(); i++) {
            if(Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == s.length()-1) {
                if(sign == '+') {
                    res += num;
                    pre = num;
                }
                if(sign == '-') {
                    res += -num;
                    pre = -num;
                }
                if(sign == '*') {
                    res = res - pre + pre * num;
                    pre = pre * num;
                }
                if(sign == '/') {
                    res = res - pre + pre / num;
                    pre = pre / num;
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
        return res;
    }

    // only have + and -
    // 1 + 3 - 5
    // meet a new sign, then let previous sign * number = number, and res += number
    // if meet (, then push the previous sum and sign into stack, nothing to do with number
    // if meet ), res += sign * number, 1 + (1 + 3), you need to complete the operation before ).
    //             then poll the sign and previous sum. --> make up the (
    // [summary]: meet the non digit number, then update number. excpet (, but use ) to make up
    public int calculate(String s) {
        // 1 + (2 - 1)
        // step 1: +, res += sign * number  sign = 1
        // step 2: (, push res, res = 0,
        // step 3: ), poll res,
        s = s.trim();
        int number = 0;
        int res = 0;
        int sign = 1;
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                number = number * 10 + c - '0';
            } else if (c == '+') {
                res += number * sign;
                sign = 1;
                number = 0;
            } else if (c == '-') {
                res += number * sign;
                sign = -1;
                number = 0;
            } else if(c == '(') {
                stack.offerFirst(res);
                stack.offerFirst(sign);
                res = 0;
                sign = 1;// since it must be 1 + (1 - 1), cannot be 1 + (-1 - 1)
            } else if(c == ')') {
                res += number * sign;//be careful, +1) --> you need to plus the last one
                // however, 1 + (2 - , you need to push the 1 + into the stack
                number = 0;//
                res *= stack.pollFirst();
                res += stack.pollFirst();
            }
        }
        if(number != 0) {
            res += sign * number;
        }
        return res;
    }
}
