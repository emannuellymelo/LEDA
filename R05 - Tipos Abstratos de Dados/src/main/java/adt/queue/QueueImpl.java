package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		if(tail == -1) {
			return null;
		} else {
			return array[0];
		}
	}

	@Override
	public boolean isEmpty() {
		if(tail == -1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if(tail == array.length-1) {
			return true;
		} else {
			return false;
		}
	}

	private void shiftLeft() {
		for(int e = 0; e < array.length-1; e++) {
			array[e] = array[e+1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isFull()) {
			throw new QueueOverflowException();
		} else if(element == null) {
			
		} else {
			this.tail++;
			array[this.tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			T removedElement;
			removedElement = array[0];
			array[0] = null;
			shiftLeft();
			this.tail--;
			return removedElement;
		}
	}

}
