package PracticeInterview.Google.medium;
// not leetcode : 第二题要求写一个简化版的计算器，能够处理输入数字和加减的情况。用户每次能够输入`0-9,+,-,=`这几种可能的字符。
// 要求用户每输入一个字符就做相应处理并输出结果。输出结果的方式类似于手机上的计算器的输出方式。
// 2018/5/10, 目标时间： 25分钟， 实际时间： 1h+ 【原因，分类没有分好】
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

// 1 + 2   next: +   ==> return 3,   stack : 3 +
//         next: *   ==> return 2,   stack : 1 + 2 *
// if no op yet, do nothing and just push op and value
// if + -. calculate All, and return the value calculated just now
// if * /,
//      if last op is + or -, do nothing and just push op and value
//      else  calculateAll until there is no op
public class OnlineCalculator {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            char c = scanner.next().charAt(0);
            System.out.println(onlineCalculator(c));

        }
    }
    static Deque<Character> op = new LinkedList<>();
    static Deque<Integer> number = new LinkedList<>();
    public static int onlineCalculator(char x) {
        if(x - '0' >= 0 && x - '0' < 10) {
            if(number.size() != op.size()) {
                int val = number.pollFirst();
                val = val * 10 + x - '0';
                number.offerFirst(val);

            } else {
                number.offerFirst(x - '0');

            }

            return number.peekFirst();
        } else if(op.isEmpty() ||
                ((op.peekFirst() == '+' || op.peekFirst() == '-') && (x == '*' || x == '/'))) {
            op.offerFirst(x);
            return number.peekFirst();
        } {
            // do nothing
            int val = calculateAll();
            op.offerFirst(x);
            return val;
        }
    }

    private static int calculateAll() {
        while(!op.isEmpty()) {
            int n2 = number.pollFirst();
            int n1 = number.pollFirst();
            char c = op.pollFirst();
            if(c == '+') {
                number.offerFirst(n2 + n1);
            } else if(c == '-') {
                number.offerFirst(n1 - n2);
            } else if(c == '*') {
                number.offerFirst(n2 * n1);
            } else {
                number.offerFirst(n1 / n2);
            }
        }


        return number.peekFirst();
    }
}
