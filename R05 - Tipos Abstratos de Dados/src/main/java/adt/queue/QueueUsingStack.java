package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(stack1.isFull()) {
			throw new QueueOverflowException();
		} else if(element == null){
			
		} else {
			while(!stack1.isEmpty()) {
				try {
					stack2.push(stack1.pop());
				} catch (StackOverflowException | StackUnderflowException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				stack1.push(element);
			} catch (StackOverflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			while(!stack2.isEmpty()) {
				try {
					stack1.push(stack2.pop());
				} catch (StackOverflowException | StackUnderflowException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(stack1.isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			T removedElement = stack1.top();
			try {
				stack1.pop();
			} catch (StackUnderflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return removedElement;
		}
	}

	@Override
	public T head() {
		if(stack1.isEmpty()) {
			return null;
		} else {
			return stack1.top();
		}
	}

	@Override
	public boolean isEmpty() {
		if(stack1.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if(stack1.isFull()) {
			return true;
		} else {
			return false;
		}
	}

}
