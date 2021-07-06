package adt.linkedList;


public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		if(this.head.isNIL()) {
			return true;
		} return false;
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> auxiliar = this.head;
		
		while(!auxiliar.isNIL()) {
			size++;
			auxiliar = auxiliar.getNext();
		} return size;
	}

	@Override
	public T search(T element) {
		T result = null;
		if(element != null && !this.isEmpty()) {
			SingleLinkedListNode<T> auxiliar = this.head;
			while(!auxiliar.isNIL() && auxiliar.data != element) {
				auxiliar = auxiliar.next;
			}
			if(auxiliar.isNIL()) {
				result = null;
			} else {
				result = auxiliar.data;
			}
		} return result;
	}

	@Override
	public void insert(T element) {
		if(element != null) {
			SingleLinkedListNode<T> auxiliar = this.head;
			if(this.isEmpty()) {
				this.head = new SingleLinkedListNode(element, this.head);
			} else {
				while(!auxiliar.isNIL()) {
					auxiliar = auxiliar.next;
				}
				auxiliar.data = element;
				auxiliar.next = new SingleLinkedListNode();
			}
		}
	}

	@Override
	public void remove(T element) {
		if(!this.isEmpty() && element != null) {
			if(this.head.data.equals(element)) {
				this.head = this.head.next;
			} else {
				SingleLinkedListNode<T> auxiliar = this.head;
				while(!auxiliar.isNIL() && !auxiliar.data.equals(element)) {
					auxiliar = auxiliar.next;
				}
				if(!auxiliar.isNIL()) {
					auxiliar.data = auxiliar.next.data;
					auxiliar.next = auxiliar.next.next;
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] resultArray = (T[]) new Object[this.size()];
		if(!this.isEmpty()) {
			SingleLinkedListNode<T> auxiliar = this.head;
			int index = 0;
			while(!auxiliar.isNIL()) {
				resultArray[index] = auxiliar.data;
				auxiliar = auxiliar.next;
				index++;
			}
		} return resultArray;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
