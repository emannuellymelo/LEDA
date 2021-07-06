package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o menor sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 2 < 3),
 * essa heap deixa os elementos menores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap. OU seja, voce deve considerar que a heap usa o comparator
	 * interno e se o comparator responde compare(x,y) < 0 entao o x eh menor
	 * e sobe na heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[])resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve usar o comparator
	 * para subir os elementos na heap.
	 */
	private void heapify(int position) {
		if(!this.isLeaf(position) && this.isValidIndex(position)) {
			int minIndex = min(position, left(position), right(position));
			if(minIndex != position) {
				Util.swap(this.heap, position, minIndex);
				heapify(minIndex);
			}
		}
	}

	/*if(isValidIndex(right) && isValidIndex(left) && isValidIndex(position)) {
			/*if (this.comparator.compare(this.heap[left], this.heap[position]) < 0 && this.comparator.compare(this.heap[left], this.heap[right]) < 0){
				min = left;
			} else if(this.comparator.compare(this.heap[right], this.heap[position]) < 0 && this.comparator.compare(this.heap[left], this.heap[right]) > 0) {
				min = right;
			} else {
				min = position;
			}
			if (min !=  position) {
				Util.swap(this.heap, position, min);
				heapify(min);
			}
		}*/
	
	private boolean isValidIndex(int i) {
		return i >= 0 && i <= index;
	}
	
	private boolean isLeaf(int i) {
		return i > parent(index) && i <= index;
	}
	
	private int min(int i, int leftIndex, int rightIndex) {
		int result = -1;
		if(isValidIndex(i) && isValidIndex(leftIndex) && isValidIndex(rightIndex)) {
			if(this.heap[i].compareTo(this.heap[leftIndex]) < 0) {
				if(this.heap[i].compareTo(this.heap[rightIndex]) > 0) {
					result = rightIndex;
				} else {
					result = i;
				}
			} else {
				if(this.heap[leftIndex].compareTo(this.heap[rightIndex]) > 0) {
					result = rightIndex;
				} else {
					result = leftIndex;
				}
			}
		}
		return result;
	}
	

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////
		
		if(element != null) {
			index++;
			this.heap[index] = element;
			
			int auxiliarIndex = index;
			while(auxiliarIndex > 0 && this.heap[parent(auxiliarIndex)].compareTo(this.heap[auxiliarIndex]) < 0) {
				T auxiliarElement = this.heap[auxiliarIndex];
				this.heap[auxiliarIndex] = this.heap[parent(auxiliarIndex)];
				this.heap[parent(auxiliarIndex)] = auxiliarElement;
				auxiliarIndex = parent(auxiliarIndex);
			}
		}
	}

	@Override
	public void buildHeap(T[] array) {
		if(array != null) {
			this.heap = array;
			this.index = this.heap.length-1;
			this.buildHeap();
		}
	}
	
	private void buildHeap() {
		for(int e = parent(this.index); e >= 0; e--) {
			heapify(e);
		}
	}

	@Override
	public T extractRootElement() {
		T result = null;
		if(!this.isEmpty()) {
			T element = this.heap[0];
			this.heap[0] = this.heap[index];
			this.index --;
			this.heapify(0);
			result = element;
		} return result;
	}

	@Override
	public T rootElement() {
		T result = null;
		if(!this.isEmpty()) {
			result = this.heap[0];
		} return result;
	}

	@Override
	public T[] heapsort(T[] array) {
		Comparator<T> firstComparator = getComparator();
		this.setComparator((f1, f2) -> f1.compareTo(f2));
		this.buildHeap(array);
		
		List<T> auxiliar = new ArrayList<>();
		for(int e = 0; e < array.length; e++) {
			auxiliar.add(this.extractRootElement());
		}
		
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.setComparator(firstComparator);
		return auxiliar.toArray((T[]) new Comparable[0]);
	}

	@Override
	public int size() {
		return this.index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
