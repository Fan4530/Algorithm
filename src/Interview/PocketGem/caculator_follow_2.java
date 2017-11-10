package Interview.PocketGem;

import java.util.*;
public class caculator_follow_2 {
	public int Calculate(String input)
	{
	     if(input == null)
	    	 return 0;

	     int num = 0;
	     int negativeSign = 1;

	     Stack<Integer> numStack = new Stack<>();
	     Stack<Character> opStack = new Stack<>();

	     //Hash table to define the priority of operators
	     HashMap<Character, Integer> opDict = new HashMap<>();
	     opDict.put('+', 1);     opDict.put('-', 1);
	     opDict.put('*', 2);     opDict.put('/', 2);
	     opDict.put('^', 3);

	     for(int i = 0; i < input.length(); i++)
	     {
	          char curr = input.charAt(i);
	          if(Character.isDigit(curr))
	          {
	               num = (num * 10 + curr - '0');  //Could check if (num*10+curr-'0') > int.MaxValue
	          } else if(curr == ' ')
	        	  continue;
	          else
	          {
	               //Bracket cases
	               if(curr == '(')
	               {
	                    int count = 1;
	                    int j = i + 1;
	                    int len = 0;
	                    while(j < input.length())
	                    {
	                         char tempChar = input.charAt(j);
	                         if(tempChar == '(')
	                              count++;
	                         else if(tempChar == ')')
	                              count--;
	                         
	                         if(count == 0)  break;   //Find all pairs, out of current loop
	                         j++; len++;
	                    }
	                    int temp = Calculate(input.substring(i + 1, j)); //Exclude brackets
	                    num = temp;  i = j;
	                    continue;
	               }

	               //Negative Sign case
	               if(curr == '-')
	               {    //We just case about the '-' after a operator and before a number
	                    if(i == 0 || !Character.isDigit(input.charAt(i-1)) && input.charAt(i-1) != ')') 
	                    {
	                         negativeSign = -1; continue;
	                    }
	               }

	               //Push the num into numStack and Reset the num
	               numStack.push(negativeSign * num);
	               num = 0; negativeSign = 1;

	               //Operators Cases:
	               if(opStack.isEmpty() || opDict.get(curr) > opDict.get(opStack.peek()))
	                    opStack.push(curr);
	               else
	               {
	                    while(!opStack.isEmpty() && opDict.get(curr) <= opDict.get(opStack.peek()))
	                         helper(numStack, opStack);
	                    opStack.add(curr);
	               }
	          }
	     }
	     //Push last number, if opStack is not empty, do calculation
	     numStack.add(num);
	     while(!opStack.isEmpty())
	          helper(numStack, opStack);
	     return numStack.pop();
	}

	//Helper for Calculate
	private void helper(Stack<Integer> numStack, Stack<Character> opStack)
	{
	     int b = numStack.pop();
	     int a = numStack.pop();
	     char op = opStack.pop();

	     //Calculate based on the operator
	     if(op == '+')
	          numStack.push(a + b);
	     else if(op == '-')
	          numStack.push(a - b);
	     else if(op == '*')
	          numStack.push(a * b);
	     else if(op == '/')
	          numStack.push(a / b);
	     else if(op == '^')          //For a^b
	          numStack.push((int)Math.pow(a, b));
	     return;
	}
	
	public static void main(String[] args) {
		caculator_follow_2 c = new caculator_follow_2();
        String s = "1 + (3 + 2) * 4";
        System.out.println(c.Calculate(s));
	}
}
