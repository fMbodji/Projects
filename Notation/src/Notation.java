
public class Notation {
	
	private static MyStack<Character> stackOperator = new MyStack<>(50);
	private static MyQueue<Character> postfixSolutionQueue = new MyQueue<>(50);
	
	private static MyStack<String> stackPostfix = new MyStack<>(50);
	
	private static MyStack<Double> stackEval = new MyStack<>(50);
	
	public static boolean isOperator(char operator)
	{
		if ( operator == '+' || operator == '-' || operator =='*' || operator =='/' )
			return true;
		return false;
	}
	
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

	
	public static boolean hasHigherOrEqualPrecedence(char operator1, char operator2)
	{
		return precedence(operator1) >= precedence(operator2);
	}
	
	
	
	
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
				} catch (QueueOverflowException e) {
					//
				}
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
					if ( topOfStack == '(' )
						stackOperator.pop();
					else
						throw new InvalidNotationFormatException("Mismatched parenthesis "); //Parenthesis thrown Exception
					
				} catch (StackUnderflowException | QueueOverflowException e) { } 
			}	
		}
		while(stackOperator.isEmpty())
			try {
				postfixSolutionQueue.enqueue(stackOperator.pop());
			} catch (StackUnderflowException | QueueOverflowException e ) { }
		String resutl = postfixSolutionQueue.toString();
		postfixSolutionQueue.clear();
		return resutl ;
	}

	
	
	
	
	
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
						String resultString = "(" + stackPostfix.pop() + currentChar + stackPostfix.pop() + ")";
						stackPostfix.push(resultString);
					} catch (StackUnderflowException | StackOverflowException e) { }
				}
				else
				{
					throw new InvalidNotationFormatException("It must be at least two values in the Stack");
				}
			}	
		}
		if (stackPostfix.size() != 1) {
	        throw new InvalidNotationFormatException("Error: Invalid expression");
	    }
		String infix="";
		try { 
			infix = stackPostfix.pop().toString();
		} catch (StackUnderflowException e) { }
	    return infix; 
	}
	
	
	
	
	

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
                            result = secondPopped / secondPopped;
                            stackEval.push(result);
                            break;
                        case '+':
                            result = secondPopped + secondPopped;
                            stackEval.push(result);
                            break;
                        case '-':
                            result = secondPopped - secondPopped;
                            stackEval.push(result);
                            break;
						}
					} catch (StackUnderflowException | StackOverflowException e) { 
					}
				}
				else
					throw new InvalidNotationFormatException("It must be at least two values in the Stack");
			}
			if (stackPostfix.size() == 1) {
				try {
					return stackEval.pop();
				} catch (StackUnderflowException e) { }
			}
			else
				throw new InvalidNotationFormatException("Invalid expression");
		}
		return 0;
	}
	
	

}


