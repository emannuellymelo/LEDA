package adt.linkedList;

import java.util.ArrayList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		if(data == null) {
			return true;
		} return false;
	}

	@Override
	public int size() {
		if(this.isEmpty()) {
			return 0;
		} else {
			return 1 + this.next.size();
		}
	}

	@Override
	public T search(T element) {
		if(!this.isEmpty() && element != null) {
			if(this.data.equals(element)) {
				return this.data;
			} else {
				return this.next.search(element);
			}
		} return null;
	}

	@Override
	public void insert(T element) {
		if(element != null) {
			if(this.isEmpty()) {
				this.data = element;
				this.next =	new RecursiveSingleLinkedListImpl<T>();
			} else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if(this.isEmpty()) {
			
		} else if(element != null) {
			if(this.data.equals(element)) {
				this.data = this.next.data;
				this.next = this.next.next;
			} else {
				this.next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resultArray = new ArrayList<>();
		toArrayAux(resultArray, this);
		return (T[]) resultArray.toArray();
	}
	
	public void toArrayAux(ArrayList<T> resultArray, RecursiveSingleLinkedListImpl<T> recursiveSingleLinkedList) {
		if(!this.isEmpty()) {
			resultArray.add(this.data);
			this.next.toArrayAux(resultArray, this.next);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
