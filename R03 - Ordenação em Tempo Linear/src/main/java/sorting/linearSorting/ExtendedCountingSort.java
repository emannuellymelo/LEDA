package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if(!verifyEntry(array, leftIndex, rightIndex)) {
			return;
		}
		int biggest = findBiggestNumber(array, leftIndex, rightIndex);
		int smallest = findSmallestNumber(array, leftIndex, rightIndex);
		
		Integer[] frequencyArray = new Integer[biggest - smallest + 1];
		
		for(int e = 0; e < frequencyArray.length; e++) {
			frequencyArray[e] = 0;
		}
		
		for(int e = leftIndex; e <= rightIndex; e++) {
			frequencyArray[array[e] - smallest] += 1;
		}
		
		for(int e = 1; e < frequencyArray.length; e++) {
			frequencyArray[e] += frequencyArray[e-1];
		}
		
		Integer[] orderedArray = new Integer[array.length];
		
		for(int e = rightIndex; e >= leftIndex; e--) {
			frequencyArray[array[e] - smallest] -= 1;
			orderedArray[frequencyArray[array[e] - smallest]] = array[e];
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
	
	public int findSmallestNumber(Integer[] array, int leftIndex, int rightIndex) {
		int smallest = array[leftIndex];
		for(int e = leftIndex; e <= rightIndex; e++) {
			if(array[e] <= smallest) {
				smallest = array[e];
			}
		}
		return smallest;
	}

}
