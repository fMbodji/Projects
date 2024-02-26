
/** 
 * Notation: Utility class that converts an infix expression to a postfix expression,
 *  a postfix expression to an infix expression,
 *  and evaluates a postfix expression. 
 * @author Fatima Mbodji
 */
public class Notation {
	
	private static MyStack<Character> stackOperator = new MyStack<>(50);
	private static MyQueue<Character> postfixSolutionQueue = new MyQueue<>(50);
	
	private static MyStack<String> stackPostfix = new MyStack<>(50);
	
	private static MyStack<Double> stackEval = new MyStack<>(50);
	
	
	/**
	 * Convert an infix expression to a postfix expression
	 * @param infix The expression to be converted
	 * @return the converted postfix expression 
	 * @throws InvalidNotationFormatException
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		char currentChar ;
		for (int i=0; i<infix.length();i++)
		{
			currentChar=infix.charAt(i);
			if( currentChar ==' ' )
			{
				continue;
			}
			else if ( Character.isDigit(currentChar) )
			{
				try {
					postfixSolutionQueue.enqueue(currentChar);
				} catch (QueueOverflowException e) { }
			}
			else if ( currentChar =='(' )
			{
				try {
					stackOperator.push(currentChar);
				}catch(StackOverflowException e) {
					//
				}
			}
			else if ( isOperator(currentChar) )
			{
				try {
					char topOfStack = stackOperator.top();
					if( isOperator(topOfStack) )
					{
						if ( hasHigherOrEqualPrecedence(topOfStack,currentChar) )
						{
							 postfixSolutionQueue.enqueue(stackOperator.pop());
						}
					}
					stackOperator.push(currentChar);
				} catch (StackUnderflowException | StackOverflowException | QueueOverflowException e) { }
			}
			else if ( currentChar ==')' )
			{
				try {
					char topOfStack = stackOperator.top();
					while ( topOfStack != '(' )
					{
						postfixSolutionQueue.enqueue(stackOperator.pop());
						topOfStack = stackOperator.top();
					}
					if ( topOfStack != '(' )
						throw new InvalidNotationFormatException("Mismatched parenthesis"); //Parenthesis thrown Exception
					else
						stackOperator.pop();
					
				} catch (StackUnderflowException | QueueOverflowException e) { } 
			}	
		}
		
		while(!stackOperator.isEmpty())
			try {
				postfixSolutionQueue.enqueue(stackOperator.pop());
			} catch (StackUnderflowException | QueueOverflowException e ) { }
		String result = postfixSolutionQueue.toString();
		postfixSolutionQueue.clear();
		return result ;
	}
	

	
	
	/**
	 * Convert a postfix expression to an infix expression
	 * @param postfix The expression to be converted
	 * @return the converted infix expression 
	 * @throws InvalidNotationFormatException
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		char currentChar ;
		for (int i=0; i<postfix.length(); i++)
		{
			currentChar=postfix.charAt(i);
			if( currentChar ==' ' )
			{
				continue;
			}
			else if ( Character.isDigit(currentChar) )
			{
				try {
					stackPostfix.push(String.valueOf(currentChar));
				} catch (StackOverflowException e) { }
			}
			else if ( isOperator(currentChar) )
			{ 
				if(stackPostfix.size()>=2)
				{
					try {
						String operand2 = stackPostfix.pop();
						String operand1 = stackPostfix.pop();
						String resultString = "(" + operand1 + currentChar + operand2 + ")";
						stackPostfix.push(resultString);
					} catch (StackUnderflowException | StackOverflowException e) { }
				}
				else
				{
					throw new InvalidNotationFormatException("It must be at least two values in the Stack");
				}
			}	
		}
		
		if (stackPostfix.size() != 1) 
		{
	        throw new InvalidNotationFormatException("Error: Invalid expression");
	    }
		String infix="";
		try { 
			infix = stackPostfix.pop().toString();
		} catch (StackUnderflowException e) { }
	    return infix; 
	}
	
	
	
	
	/**
	 * Evaluate the arithmetical value of a postfix expression
	 * @param postfixExpr The expression to be evaluated
	 * @return a double value, which is the result after evaluation
	 * @throws InvalidNotationFormatException
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		char currentChar ;
		for (int i=0; i<postfixExpr.length();i++)
		{
			currentChar=postfixExpr.charAt(i);
			if( currentChar ==' ' )
			{
				continue;
			}
			else if ( Character.isDigit(currentChar) )
			{
				try {
					stackEval.push((double) Character.getNumericValue(currentChar));
				} catch (StackOverflowException e) {
					//
				}
			}
			else if ( isOperator(currentChar) )
			{
				if(stackEval.size()>=2)
				{
					try {
						double firstPopped = (stackEval.pop());  
						double secondPopped =  (stackEval.pop()); 
						double result;
						switch(currentChar) {
						case '*':
                            result = secondPopped * firstPopped;
                            stackEval.push(result);
                            break;
                        case '/':
                            result = secondPopped / firstPopped;
                            stackEval.push(result);
                            break;
                        case '+':
                            result = secondPopped + firstPopped;
                            stackEval.push(result);
                            break;
                        case '-':
                            result = secondPopped - firstPopped;
                            stackEval.push(result);
                            break;
						}
					} catch (StackUnderflowException | StackOverflowException e) { 
					}
				}
				else
					throw new InvalidNotationFormatException("It must be at least two values in the Stack");
			}
		}
		
		if (stackEval.size() == 1) 
		{
			try {
				double finalResult = stackEval.pop();
				return finalResult;
			} catch (StackUnderflowException e) { }
		}
			throw new InvalidNotationFormatException("Invalid expression");
	}	

//MY HELPER METHODS

	/** 
	 * Check if a character is an operator
	 * @param character The character to be checked
	 * @return true if operator, false otherwise
	 */
	public static boolean isOperator(char character)
	{
		if ( character == '+' || character == '-' || character =='*' || character =='/' )
			return true;
		return false;
	}
	
	
	/**
	 * Check the precedence of the operator 
	 * @param operator The operator to be checked
	 * @return an int value which represent the precedence
	 */
	public static int precedence(char operator)
	{
		switch (operator) {
			case '+':
			case '-':
				return 1;
			case '/':
			case '*':
				return 2;
			default :
				return 0; 
		}
	}

	
	/**
	 * Compare the precedence of two values
	 * @param operator1 The first operator
	 * @param operator2 The second operator 
	 * @return true if the first operator has higher or equal precedence, false otherwise
	 */
	public static boolean hasHigherOrEqualPrecedence(char operator1, char operator2)
	{
		return precedence(operator1) >= precedence(operator2);
	}
	
}





