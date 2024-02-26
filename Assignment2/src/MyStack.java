import java.util.ArrayList;

/**
 *Stack class which implements the QueueInterface
 * @param <T> The type of the Stack
 * @author Fatima Mbodji
 */

public class MyStack<T> implements StackInterface<T> {
	
	private T[] Stack;
	private int top;
	
	/**
	 * Default constructor
	 * Create a Stack object of size 10
	 */
	@SuppressWarnings("unchecked")
	public MyStack()
	{
		Stack = (T[]) new Object[10];
	}
	
	
	/**
	 * Parameterized constructor
	 * @param size - the size of the Stack
	 */
	@SuppressWarnings("unchecked")
	public MyStack(int size)
	{
		Stack = (T[]) new Object[size];
	}

	
	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return top<=0;
	}
	

	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	@Override
	public boolean isFull() {	
		return top == Stack.length;
	}
	
	
	/**
	 * Adds an element to the top of the Stack 
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException if stack is full
	 */
	@Override
	public boolean push(T e) throws StackOverflowException {
		if (isFull())
			throw new StackOverflowException("This Stack is full");
		else
		{
			Stack[top] = e;
			top++;
			return true;
		}
	}
	
	
	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	@Override
	public T pop() throws StackUnderflowException {
		T data;
		if (isEmpty())
			throw new StackUnderflowException("This Stack is empty");
		else
		{
			top--;
			data = Stack[top];
			Stack[top] = null;
			return data;
		}	
	}


	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	@Override
	public T top() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException("This Stack is empty");
		else
			return Stack[top-1];
	}

	
	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	@Override
	public int size() {
		return top;
	}

	
	/**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString() {
		//String str="Elements: ";
		String str="";
		for (int i=0;i<size();i++)
		{
			str+= Stack[i];
		}
		return str;
	}
	
	
	/**
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * @param delimiter Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
	@Override
	public String toString(String delimiter) {
		//String str="Elements: ";
		String str="";
		for (int i=0;i<size();i++)
		{
			str+= Stack[i] + delimiter;
		}
		return str;
	}

	
	 /**
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
	  * list reference within your Stack, you will be allowing direct access to the data of
	  * your Stack causing a possible security breech.
	  * @param list elements to be added to the Stack from bottom to top
	  * @throws StackOverflowException if stack gets full
	  */
	@Override
	public void fill(ArrayList<T> list) throws StackOverflowException {
		ArrayList<T> myList = new ArrayList<>();
		myList = (ArrayList<T>) list.clone();
		for(int i=0; i<list.size(); i++)
		{
			push(myList.get(i));
		}
	}

}
