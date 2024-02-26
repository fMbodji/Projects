import java.util.ArrayList;

/**
 *Queue class which implements the QueueInterface
 * @param <T> The type of the Queue
 * @author Fatima Mbodji
 */
public class MyQueue<T> implements QueueInterface<T>{
	
	private T Queue[];
	private int front, rear, size;
	
	
	/**
	 * Default constructor
	 */
	@SuppressWarnings("unchecked")
	public MyQueue()
	{
		 Queue = (T[]) new Object[10];
	}
	
	
	/**
	 * Parameterized constructor 
	 * @param size The size of the queuem
	 */
	@SuppressWarnings("unchecked")
	public MyQueue(int size)
	{
		Queue = (T[]) new Object[size];
	}
	
	
	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	
	/**
	 * Determines of the Queue is Full
	 * @return true if Queue is full, false if not
	 */
	@Override
	public boolean isFull() {
		return size() == Queue.length;
	}
	
	
	/**
	 * Returns number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	@Override
	public int size() {
		return size;
	}
	
	
	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException if queue is full
	 */
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if (isFull())
			throw new QueueOverflowException("This Queue is full");
		else
		{
			Queue[rear] = e;
			rear=(rear+1)%Queue.length;
			size++;
			//System.out.println("Front: " + front + "  Rear: " + rear);
			//System.out.println("Length Queue: " + Queue.length);
			return true;
		}
	}

	
	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException if queue is empty
	 */
	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty())
			throw new QueueUnderflowException("This Queue is empty");
		else
		{
			T data = Queue[front];
			front = (front+1)%Queue.length;
			size--;
			//System.out.println("Front: " + front + "  Rear: " + rear);
			//System.out.println("Length Queue: " + Queue.length);
			return data;
		}
	}

	
	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	@Override 
	public String toString() {
		//String str="Elements: ";
		String str="";
		for (int i=0; i<size();i++)
		{
			str+= Queue[(front+i)%Queue.length];
		}
		return str;
	}

	
	/**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * @param delimiter Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	@Override
	public String toString(String delimiter) {
		//String str="Elements: ";
		String str="";
		for (int i=0; i<size();i++)
		{
			str+= Queue[(front+i)%Queue.length] + delimiter;
		}
		return str;
	}

	
	 /**
	  * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	  * is the first element in the Queue
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
	  * list reference within your Queue, you will be allowing direct access to the data of
	  * your Queue causing a possible security breech.
	  * @param list elements to be added to the Queue
	  * @throws QueueOverflowException if queue is full
	  */
	@Override
	public void fill(ArrayList list) throws QueueOverflowException {
		ArrayList<T> myList = new ArrayList<>();
		myList = (ArrayList<T>) list.clone();
		for(int i=0; i<list.size(); i++)
		{
			enqueue(myList.get(i));
		}
		
	}
	
	/**
	 * Removes all of the elements from this queue.
	 * 
	 */
	public void clear() {
		while (! isEmpty())
		{
			try {
				dequeue();
			} catch (QueueUnderflowException e) { }
		}
	}
	
	

}
