package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		if(this.top == -1) {
			return null;
		} else {
			return array[top];
		}
	}

	@Override
	public boolean isEmpty() {
		if(this.top == -1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if(this.top == this.array.length-1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(isFull()) {
			throw new StackOverflowException();
		} else if(element == null) {
		
		} else {
			this.top++;
			array[this.top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();
		} else {
			T removedElement;
			removedElement = array[this.top];
			array[this.top] = null;
			this.top--;
			return removedElement;
		}
	}

}
