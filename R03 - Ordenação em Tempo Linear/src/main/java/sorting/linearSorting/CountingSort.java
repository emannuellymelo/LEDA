package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Voce pode assumir que o maior inteiro armazenado não chega a 100.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if(!verifyEntry(array, leftIndex, rightIndex)) {
			return;
		}
		int biggest = findBiggestNumber(array, leftIndex, rightIndex);
		
		Integer[] frequencyArray = new Integer[biggest+1];
		
		for(int e = 0; e < frequencyArray.length; e++) {
			frequencyArray[e] = 0;
		}
		
		for(int e = leftIndex; e <= rightIndex; e++) {
			frequencyArray[array[e]] += 1;
		}
		
		for(int e = 1; e < frequencyArray.length; e++) {
			frequencyArray[e] += frequencyArray[e-1];
		}
		
		Integer[] orderedArray = new Integer[array.length];
		
		for(int e = rightIndex; e >= leftIndex; e--) {
			frequencyArray[array[e]] -= 1;
			orderedArray[frequencyArray[array[e]]] = array[e];
		}
		for(int e = leftIndex; e <= rightIndex; e++) {
			array[e] = orderedArray[e];
		}
		
	}
	
	public boolean verifyEntry(Integer[] array, int leftIndex, int rightIndex) {
		boolean valid = true;
		if(array == null) {
			valid = false;
			throw new IllegalArgumentException();
		}
		if(leftIndex >= rightIndex || array.length <= 1 || leftIndex >= array.length || rightIndex >= array.length || leftIndex < 0 || rightIndex < 0) {
			valid = false;
		}
		return valid;
	}
	
	public int findBiggestNumber(Integer[] array, int leftIndex, int rightIndex) {
		int biggest = array[leftIndex];
		for(int e = leftIndex; e <= rightIndex; e++) {
			if(array[e] >= biggest) {
				biggest = array[e];
			}
		}
		return biggest;
	}

}
