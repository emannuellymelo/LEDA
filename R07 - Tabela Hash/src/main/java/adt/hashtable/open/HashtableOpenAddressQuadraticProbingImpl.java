package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if(this.isFull()) {
			throw new HashtableOverflowException();
		} else if(element != null && this.search(element) == null) {
			int probe = 0;
			boolean end = false;
			
			while(!end) {
				int hash = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, probe);
				if(this.table[hash] == null || this.table[hash].equals(this.deletedElement)) {
					this.table[hash] = element;
					this.elements++;
					end = true;
				} else if(this.table[hash] != null && !this.table[hash].equals(deletedElement)) {
					this.COLLISIONS++;
				} probe++;
			}
		}
	}

	@Override
	public void remove(T element) {
		if(element != null && this.search(element) != null && !this.isEmpty()) {
			int probe = 0;
			boolean end = false;
			
			while(!end) {
				int hash = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, probe);
				if(this.table[hash] != null) {
					if(this.table[hash].equals(element)) {
						this.table[hash] = new DELETED();
						this.elements--;
						end = true;
					} 
					if(probe > 0) {
						this.COLLISIONS--;
					}
				} probe++;
			}
		}
	}

	@Override
	public T search(T element) {
		if(element != null && !this.isEmpty()) {
			int probe = 0;
			
			while(probe < this.capacity()) {
				int hash = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, probe);
				if(this.table[hash] != null) {
					if(this.table[hash].equals(element)) {
						return element;
					}
				} probe++;
			}
		} return null;
	}

	@Override
	public int indexOf(T element) {
		if(element != null && this.search(element) != null && !this.isEmpty()) {
			int probe = 0;
			
			while(probe < this.capacity()) {
				int hash = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, probe);
				if(this.table[hash] != null) {
					if(this.table[hash].equals(element)) {
						return hash;
					}
				} probe++;
			}
		} return -1;
	}
}
