package Practical.pg;

import java.util.*;
public class caculator {
    public int calculate2(String s) {
        int len;
        if(s==null || (len = s.length())==0) return 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        int num = 0;
        char sign = '+';
        for(int i = 0 ; i < len; i++) {
            if(Character.isDigit(s.charAt(i))){
                num = num * 10 + s.charAt(i) - '0';
            }
            if((!Character.isDigit(s.charAt(i)) &&' '!=s.charAt(i)) || i==len-1){
                if(sign=='-') {// sign store the last sign
                    stack.offerFirst(-num);
                }
                if(sign=='+') {
                    stack.offerFirst(num);
                }
                if(sign=='*') {
                    stack.push(stack.pollFirst()*num);
                }
                if(sign=='/') {
                    stack.push(stack.pollFirst()/num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }

        int re = 0;
        for(int i : stack){
            re += i;
        }
        return re;
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




//---------------------------------------------------


    // calculate 1: only has + and -
    public static int calculate1(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int curNum = 0;
        Deque<Character> op = new LinkedList<>();
        Deque<Integer> number = new LinkedList<>();
        for(int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                curNum = curNum * 10 + c - '0';
            } else if (c == ' ') {
                continue;
            } else {
                //push the number and clear it up
                number.offerFirst(curNum);
                curNum = 0;
                if(!op.isEmpty()) {
                    calculate(op, number);
                }
                // push the sign
                op.offerFirst(c);
            }
        }
        number.offerFirst(curNum);
        return calculate(op, number);
    }
    private static int calculate(Deque<Character> op, Deque<Integer> number) {
        char sign = op.pollFirst();
        int second = number.pollFirst();
        int first = number.pollFirst();
        if(sign == '+') {
            number.offerFirst(first + second);
        } else if(sign == '-'){
            number.offerFirst(first - second);
        } else if(sign == '*') {
            number.offerFirst(first * second);
        } else if(sign == '/') {
            number.offerFirst(first / second);
        } else if(sign == '^') {
            number.offerFirst((int)Math.pow(first, second));
        }
        return number.peekFirst();
    }

    public static void main(String [] args) {
        System.out.println(calculate1("2-200+2"));
        System.out.println(calculateMulti("-2*200+2"));
        System.out.println(calculateMultiAndPa("-2*((200+2 * 6) + 1)"));
        System.out.println(calculateHard("-2*((200^2 * 6) + 1)"));
        System.out.println(calculateHard("-2-((200+2 ^ 6) * 2)"));
    }

    //calculate 2:
    //+ - * /
    //if meet lower priority op, calculate the former first
    public static int calculateMulti(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int curNum = 0;
        Deque<Character> op = new LinkedList<>();
        Deque<Integer> number = new LinkedList<>();
        for(int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                curNum = curNum * 10 + c - '0';
            } else if (c == ' ') {
                continue;
            } else {
                //push the number and clear it up
                number.offerFirst(curNum);
                curNum = 0;
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

    //calculate 3:
    //+ - * / ()
    //if meet lower priority op, calculate the former first
    public static int calculateMultiAndPa(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int curNum = 0;
        Deque<Character> op = new LinkedList<>();
        Deque<Integer> number = new LinkedList<>();
        for(int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                curNum = curNum * 10 + c - '0';
            } else if (c == ' ') {
                continue;
            } else if(c == '(') {
                op.offerFirst(c);
            } else if(c == ')') {
                //when meet ), calculate all until meet the last (
                // 2 * (1 + 3 - 2)  --> 2 * 2
                number.offerFirst(curNum);
                while(!op.isEmpty() && op.peekFirst() != '(') {
                    calculate(op, number);
                }
                if(!op.isEmpty() && op.peekFirst() == '(') {
                    op.pollFirst();
                }
                curNum = number.pollFirst();
            } else {
                //push the number and clear it up
                number.offerFirst(curNum);
                curNum = 0;
                if(c == '+' || c == '-' || c == '(') {
                    while(!op.isEmpty() && op.peekFirst() != '(') {
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

    //calculate 4:
    //+ - * / () ^
    //if meet lower priority op, calculate the former first
    public static int calculateHard(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        // key: sign,  Integer: priority
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
        map.put('^', 3);
        map.put('(', 0);
        int curNum = 0;
        Deque<Character> op = new LinkedList<>();
        Deque<Integer> number = new LinkedList<>();
        for(int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                curNum = curNum * 10 + c - '0';
            } else if (c == ' ') {
                continue;
            } else if(c == '(') {
                op.offerFirst(c);
            } else if(c == ')') {
                //when meet ), calculate all until meet the last (
                // 2 * (1 + 3 - 2)  --> 2 * 2
                number.offerFirst(curNum);
                while(!op.isEmpty() && op.peekFirst() != '(') {
                    calculate(op, number);
                }
                if(!op.isEmpty() && op.peekFirst() == '(') {
                    op.pollFirst();
                }
                curNum = number.pollFirst();
            } else {
                //push the number and clear it up
                number.offerFirst(curNum);
                curNum = 0;
                //1. Not the first sign, 2. lower or equal priority with before
                if(!op.isEmpty() && map.get(c) <= map.get(op.peekFirst())) {
                    while(!op.isEmpty() && op.peekFirst() != '(') {
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
}



//part1: " "
//part2: number
//part3: ( )  --> recursion
//part4: operation,
//		push number
//		if cur operation has higher priority, push op 低优先级 while 算
//		else  if cur operation has lower or equal priority,  calculate the former
//			push op
//end loop: push cur number
//    call calculate

// 2 - (-6 + 8 / 2)
// init
// curNumber: 0
// curOp:    +
// op:
// number:

// 2
// curNumber: 2
// curOp:    +
// op:
// number:


// -
// curNumber: 2
// curOp:    -
// op:      -
// number:  2

// ( recursion
// curNumber: 0
// curOp:     +
// op:
// number:

// -
// curNumber: 0
// curOp:     -
// op:        -
// number:    0


// 6
// curNumber: 6
// curOp:     -
// op:        -
// number:    0

// +
// curNumber: 6
// curOp:     +
// op:        -
// number:    0 6
// ----calculate
// op:
// number:    -6
//after calculate
// op:		   +
// number:    -6

// 8
// curNumber: 8
// curOp:     -
// op:		  +
// number:    -6

// /. its ok, since / has higher priority
// curNumber: 8
// curOp:     /
// op:        +  /
// number:    -6 8

// 2
// curNumber: 2
// curOp:     /
// op:        +  /
// number:    -6 8
////end loop, add curnumber
// op:        +  /
// number:    -6 8 2
//calculate
// op:        +
// number:    -6 4
// op:
// number:    -2
//---end this call

// -2
// curNumber: -2
// curOp:    -
// op:      -
// number:  2
//end loop, add -2
// op:      -
// number:  2 -2

// op:
// number:  4
// res = 4






// + - ()                     can be optimized, not use recursion
// + - * /                    标准写法

// + - * /  ^                 map
// + - * /  ^ ()              () --> recursion       ^ map
