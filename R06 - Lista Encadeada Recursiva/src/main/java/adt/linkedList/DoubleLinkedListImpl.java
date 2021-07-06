package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	
	@Override
	public void insert(T element) {
		if(element != null) {
			if(this.isEmpty()) {
				DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(), new DoubleLinkedListNode<T>());
				newHead.getPrevious().setNext(newHead);
				((DoubleLinkedListNode<T>) newHead.getNext()).setPrevious(newHead);
				this.setHead(newHead);
				this.setLast(newHead);
			} else {
				DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>)this.last.getNext(), this.last);
				((DoubleLinkedListNode<T>) newHead.getNext()).setPrevious(newHead);
				this.last.setNext(newHead);
				this.setLast(newHead);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if(element != null) {
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>();
			newHead.setData(element);
			newHead.setNext(this.getHead());
			newHead.setPrevious(new DoubleLinkedListNode<T>());
			((DoubleLinkedListNode<T>) this.getHead()).setPrevious(newHead);
			
			if(this.getHead().isNIL()) {
				this.last = newHead;
			}
			this.setHead(newHead);
		}
	}
	
	@Override
	public void remove(T element) {
		if(element != null && !this.isEmpty()) {
			if(this.getHead().getData().equals(element)) {
				removeFirst();
			} else {
				DoubleLinkedListNode<T> auxiliar = (DoubleLinkedListNode<T>) this.getHead();
				DoubleLinkedListNode<T> previous = (DoubleLinkedListNode<T>) this.getHead();
				while(!auxiliar.isNIL() && !auxiliar.getData().equals(element)) {
					previous = auxiliar;
					auxiliar = (DoubleLinkedListNode<T>) auxiliar.getNext();
				}
				if(!auxiliar.isNIL()) {
					previous.setNext(auxiliar.next);
				}
			}
		}
	}

	@Override
	public void removeFirst() {
		if(!this.isEmpty()) {
			if(!this.getHead().isNIL()) {
				this.setHead(this.getHead().getNext());
				if(this.getHead().isNIL()) {
					this.last = (DoubleLinkedListNode<T>) this.getHead();
				} else {
					((DoubleLinkedListNode<T>) this.getHead()).setPrevious(new DoubleLinkedListNode<T>());
				}
			}
		}
		
	}

	@Override
	public void removeLast() {
		if(!this.isEmpty()) {
			if(!this.getLast().isNIL()) {
				this.last = this.last.getPrevious();
				if(this.last.isNIL()) {
					this.setHead(this.last);
				} else {
					this.last.setNext(new DoubleLinkedListNode<>());
				}
			}
		}	
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
