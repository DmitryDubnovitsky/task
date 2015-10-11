package com.java;
import java.util.Scanner;
import java.util.Stack;
public class Calculation {
	/*
	 * example
	 * binary: 1010 + 0001 * ( 0100 - 0010 ) + 0100 / 0001
	 * decimal:( 5 + 8 ) * 4 - 4 / 2
	 * hexadecimal: f - b / ( 9 + 2 ) * 2
	 */
 public static int evaluate(String expression,int type)
  {
			
    char[] tokens = expression.toCharArray();

	// Stack for numbers: 'values'
	Stack<Integer> values = new Stack<Integer>();
			
	// Stack for Operators: 'ops'
	Stack<Character> ops = new Stack<Character>();
    // System.out.println(tokens.length);
	  for (int i = 0; i < tokens.length; i++)
		{
			//Current token is a whitespace, skip it
			if (tokens[i] == ' ')
				continue;

				// Current token is a number, push it to stack for numbers
			if (tokens[i] >= '0' && tokens[i] <= '9' && type==10)
			 {
				StringBuffer sbuf = new StringBuffer();
				// There may be more than one digits in number
				while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
					sbuf.append(tokens[i++]);
				values.push(Integer.parseInt(sbuf.toString()));
			 }
				else if(tokens[i] >= '0' && tokens[i] <= '1' && type==2){
					StringBuffer sbuf = new StringBuffer();
					// There may be more than one digits in number
					while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '1')
						sbuf.append(tokens[i++]);
					values.push(Integer.parseInt(sbuf.toString(),2));
					
				}
				
				else if(((tokens[i] >= '0' && tokens[i] <= '9') || (tokens[i] >= 'a' && tokens[i] <= 'f'))  && type==16){
					StringBuffer sbuf = new StringBuffer();
					// There may be more than one digits in number
					while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || (tokens[i] >= 'a' && tokens[i] <= 'f')))
						sbuf.append(tokens[i++]);
					values.push(Integer.parseInt(sbuf.toString(),16));
					
				}
				
				// Current token is an opening brace, push it to 'ops'
				else if (tokens[i] == '(')
					ops.push(tokens[i]);

				// Closing brace encountered, solve entire brace
				else if (tokens[i] == ')')
				{
					while (ops.peek() != '(')
					values.push(applyOp(ops.pop(), values.pop(), values.pop()));
					ops.pop();
				}

				// Current token is an operator.
				else if (tokens[i] == '+' || tokens[i] == '-' ||
						tokens[i] == '*' || tokens[i] == '/')
				{
					// While top of 'ops' has same or greater precedence to current
					// token, which is an operator. Apply operator on top of 'ops'
					// to top two elements in values stack
					while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
					values.push(applyOp(ops.pop(), values.pop(), values.pop()));

					// Push current token to 'ops'.
					ops.push(tokens[i]);
				}
			}

			// Entire expression has been parsed at this point, apply remaining
			// ops to remaining values
			while (!ops.empty())
				values.push(applyOp(ops.pop(), values.pop(), values.pop()));

			// Top of 'values' contains result, return it
			return values.pop();
		}

		// Returns true if 'op2' has higher or same precedence as 'op1',
		// otherwise returns false.
		public static boolean hasPrecedence(char op1, char op2)
		{
			if (op2 == '(' || op2 == ')')
				return false;
			if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
				return false;
			else
				return true;
		}

		// A utility method to apply an operator 'op' on operands 'a' 
		// and 'b'. Return the result.
		public static int applyOp(char op, int b, int a)
		{
			switch (op)
			{
			case '+':
				return a + b;
			case '-':
				return a - b;
			case '*':
				return a * b;
			case '/':
				if (b == 0)
					throw new
					UnsupportedOperationException("Cannot divide by zero");
				return a / b;
			}
			return 0;
		}

		// Driver method to test above methods
		public static void main(String[] args)
		{
			Scanner sc1=new Scanner(System.in);
			System.out.println("Input number 2-binary,number 10-decimal,number 16-hexadecimal");
			int type=sc1.nextInt();
			System.out.println("Input expression");
			Scanner sc2=new Scanner(System.in);
			String expr=sc2.nextLine();
			int result=Calculation.evaluate(expr,type);
			switch(type)
			{
			case 10:
				System.out.println(result);
			break;
			
			case 2:
				System.out.println(Integer.toBinaryString(result));
			break;
			case 16:
				System.out.print(Integer.toHexString(result));break;
			}
			sc1.close();
			sc2.close();
		}
	}



