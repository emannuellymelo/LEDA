package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isFull()) {
			throw new QueueOverflowException();
		} else if(element == null) {
			
		} else {
			if(this.head == -1) {
				head = 0;
			}
			this.tail++;
			if(tail == array.length-1 && this.elements < array.length-1) {
				array[tail] = element;
				tail = -1;
			} else {
				array[tail] = element;
			} this.elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			this.elements--;
			if(head == array.length) {
				head = 0;
			}
			T removedElement;
			removedElement = array[head];
			array[head] = null;
			head++;
			return removedElement;
		}
	}

	@Override
	public T head() {
		if(isEmpty()) {
			return null;
		} else {
			return array[head];
		}
	}

	@Override
	public boolean isEmpty() {
		if(this.elements == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if(this.elements == array.length) {
			return true;
		} else {
			return false;
		}
	}

}
