import java.util.ArrayList;
import java.util.Queue;

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
	 */
	@SuppressWarnings("unchecked")
	public MyQueue(int size)
	{
		Queue = (T[]) new Object[size];
	}
	
	
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean isFull() {
		return size() == Queue.length;
	}
	
	@Override
	public int size() {
		return size;
	}
	
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

	@Override
	public void fill(ArrayList list) throws QueueOverflowException {
		ArrayList<T> myList = new ArrayList<>();
		myList = (ArrayList<T>) list.clone();
		for(int i=0; i<list.size(); i++)
		{
			enqueue(myList.get(i));
		}
		
	}
	
	public void clear() {
		while (! isEmpty())
		{
			try {
				dequeue();
			} catch (QueueUnderflowException e) { }
		}
	}
	
	

}
