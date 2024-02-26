import java.util.ArrayList;

/**
 * A Helper Runner class
 * @author Fatima Mbodji
 */
public class testRunner {

	public static void main(String[] args) throws InvalidNotationFormatException {

		System.out.println("Running ");
		String complexInfix = "(3+(((5*7)-(((8/2)-1)*4))*6))";
		String complexPostfix = "357*82/1-4*-6*+";
		String postfixResult = Notation.convertInfixToPostfix(complexInfix);
		System.out.println(postfixResult);
		if (postfixResult == complexPostfix)
			System.out.println("OK");
		else
			System.out.println("NOT OK");

		String resultString = "(" + '8' + '+' + '3' + ")";
		System.out.println(resultString);
		
		 MyStack<Integer> stack = new MyStack<>(5);
		  
		  try { 
			  stack.push(15); stack.push(8); stack.push(10);
			  System.out.println(stack); System.out.println(stack.top());
			  System.out.println(stack.pop()); System.out.println(stack);
		  }catch(StackOverflowException | StackUnderflowException e) { }
		  finally {
			  System.out.println("Size Stack: " + stack.size());
			  System.out.println("Empty status: " + stack.isEmpty());
			  System.out.println("Full status: " + stack.isFull());
			  System.out.println("");
		  }
		  
		  ArrayList<Integer> list = new ArrayList<>(); 
		  list.add(10); list.add(20);
		  list.add(30); list.add(40); 
		  list.add(50); list.add(60); list.add(70);
		  list.add(80); list.add(90);
		  
		  
		  try {
			  stack.fill(list); 
		  }catch(StackOverflowException e) { } 
		  finally{
		  System.out.println(stack); System.out.println("Size Stack: " + stack.size());
		  System.out.println("Empty status: " + stack.isEmpty());
		  System.out.println("Full status: " + stack.isFull()); System.out.println("");
		  } System.out.println(""); 
		  
		  
		  /////////////////////////////////////////////
		  
		  
		  MyQueue<Integer> queue = new MyQueue<>(5);
		  
		  try { 
			  System.out.println(queue.enqueue(23));
			  System.out.println(queue.enqueue(4)); 
			  System.out.println(queue.enqueue(34));
			  System.out.println(queue.enqueue(3)); 
			  System.out.println(queue.enqueue(6));
			  queue.dequeue(); 
			  queue.dequeue();  
			  System.out.println(queue.enqueue(8));
		  }catch (QueueOverflowException | QueueUnderflowException e) { } 
		  finally {
		  	System.out.println(queue);
		  	System.out.println("Size Queue: " + queue.size());		
		  	System.out.println("Empty status: " + queue.isEmpty());
		  	System.out.println("Full status: " + queue.isFull());
		  	System.out.println("");
		  } 
		  
		  try { 
			  queue.fill(list);  
		  }catch(QueueOverflowException e) { } 
		  finally{
			  System.out.println(queue); 
			  System.out.println("Size Queue Before clearing: " + queue.size());
			  queue.clear();
			  System.out.println("Size Queue After clearing: " + queue.size());
			  System.out.println("Empty status: " + queue.isEmpty());
			  System.out.println("Full status: " + queue.isFull()); 
			  System.out.println("");
		  } 
		  System.out.println("");
		 

	}

}
