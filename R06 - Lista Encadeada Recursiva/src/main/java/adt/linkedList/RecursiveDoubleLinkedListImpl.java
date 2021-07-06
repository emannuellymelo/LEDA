package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}
	
	@Override
	public void insert(T element) {
		if(element != null) {
			if(this.isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<T>());
				if(this.getPrevious() == null) {
					this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
				}
			} else {
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if(element != null) {
			if(this.isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<T>());
				this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
			} else {
				RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<T>();
				
				newNode.setData(this.getData());
				newNode.setNext(this.getNext());
				
				this.setData(element);
				this.setNext(newNode);
				
				newNode.setPrevious(this);
				
				((RecursiveDoubleLinkedListImpl<T>) newNode.getNext()).setPrevious(newNode);
			}
		}
	}
	
	@Override
	public void remove(T element) {
		if(this.isEmpty()) {
			
		} else if(element != null){
			if(this.getData().equals(element)) {
				if(this.previous.isEmpty() && this.getNext().isEmpty()) {
					this.setData(null);
					this.setNext(null);
					this.setPrevious(null);
				} else {
					this.setData(this.getNext().getData());
					this.setNext(this.getNext().getNext());
					if(this.getNext() != null) {
						((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
					}
				}
			} else {
				this.getNext().remove(element);
			}
		}
	}

	@Override
	public void removeFirst() {
		if(this.isEmpty()) {
			
		} else {
			if(this.previous.isEmpty() && this.getNext().isEmpty()) {
				this.setData(null);
				this.setNext(null);
				this.setPrevious(null);
			} else {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
				if(this.getNext() != null) {
					((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
				}
			}
		}
	}

	@Override
	public void removeLast() {
		if(this.isEmpty()) {
			
		} else {
			if(this.getNext().isEmpty()) {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
			} else {
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
