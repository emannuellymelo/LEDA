package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(array == null) {
			throw new IllegalArgumentException();
		} if(leftIndex > array.length-1 || rightIndex > array.length-1) {
			
		} else {
			for(int i = leftIndex; i <= rightIndex; i++) {
				int menor = i;
				for(int j = i; j <= rightIndex; j++) {
					if(array[j].compareTo(array[menor]) < 0) {
						menor = j;
					}
				}
				T aux = array[i];
				array[i] = array[menor];
				array[menor] = aux;
			}
		}
	}
}
