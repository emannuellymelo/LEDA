package orderStatistic;

/**
 * O quickselect eh um algoritmo baseado no quicksort para
 * descobrir/selectionar, em tempo linear, a k-esima estatistica de ordem
 * (k-esimo menor elemento) de um conjunto de dados.
 * 
 * O quiskselect escolhe um elemento para ser o pivot e particiona o array
 * inicial em dois subarrays da mesma forma que o quicksort (elementos menores
 * que o pivot a esquerda do pivot e elementos maiores que o pivot a direita
 * dele). Entretanto, ao inves de chamar o quicksort recursivo nas duas metades,
 * o quickselect eh executado (recursivamente) apenas na metade que contem o
 * elemento que ele procura (o k-esimo menor elemento). Isso reduz a
 * complexidade de O(n.log n) para O(n)
 * 
 * @author Adalberto
 *
 */
public class QuickSelect<T extends Comparable<T>> {

	/**
	 * O algoritmo quickselect usa a mesma abordagem do quicksort para calclar o
	 * k-esimo menor elemento (k-esima estatistica de ordem) de um determinado
	 * array de dados comparaveis. Primeiro ele escolhe um elemento como o pivot
	 * e particiona os daods em duas partes baseado no pivot (exatemente da
	 * mesma forma que o quicksort). Depois disso, ele chama recursivamente o
	 * mesmo algoritmo em apenas uma das metades (a que contem o k-esimo menor
	 * elemento). Isso reduz a completixade de O(n.log n) para O(n).
	 * 
	 * Caso o array seja vazio ou a ordem (posicao) do elemento desejado esteja
	 * fora do tamanho do array, o metodo deve retornar null.
	 * 
	 * 
	 * @param array
	 *            o array de dados a procurar o k-esimo menor elemento.
	 * @param k
	 *            a ordem do elemento desejado. 1 significa primeiro menor
	 *            elemento, 2 significa segundo menor elemento e assim por
	 *            diante
	 * @return
	 */
	public T quickSelect(T[] array, int k) {
		if(!verifyEntry(array, k)) {
			return null;
		}
		int leftIndex =  0;
		int rightIndex = array.length-1;
		return quickSelect(array,  leftIndex, rightIndex, k);
	}
	
	public T quickSelect(T[] array,  int leftIndex, int rightIndex, int k) {
		int pivotIndex = partition(array, leftIndex, rightIndex);
		if(pivotIndex+1 == k) {
			return array[pivotIndex];
		} else if(pivotIndex+1 > k) {
			return quickSelect(array, leftIndex, pivotIndex-1, k);
		} else {
			return quickSelect(array, pivotIndex+1, rightIndex,k);
		}
	}
	
	private int partition(T[] array, int leftIndex, int rightIndex) {
		T pivot = array[leftIndex];
		int i = leftIndex;
		for(int e = leftIndex+1; e <= rightIndex; e++) {
			if(array[e].compareTo(pivot) <= 0) {
				i++;
				util.Util.swap(array, i, e);
			}
		}
		util.Util.swap(array, leftIndex, i);
		return i;
	}
	
	private boolean verifyEntry(T[] array, int k) {
		if(array.length == 0 || k > array.length || k <= 0) {
			return false;
		}
		return true;
	}
	
}