package sorting.simpleSorting;

import sorting.AbstractSorting;


/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(array == null) {
			throw new IllegalArgumentException();
		} if(leftIndex > array.length-1 || rightIndex > array.length-1) {
			
		} else {
			for(int i = leftIndex; i < rightIndex; i++) {
				for(int j = leftIndex; j < rightIndex; j++ ) {
					if(array[j].compareTo(array[j+1]) > 0) {
						T maior = array[j];
						array[j] = array[j+1];
						array[j+1] = maior;
					}
				}
			}
		}
	}
}
