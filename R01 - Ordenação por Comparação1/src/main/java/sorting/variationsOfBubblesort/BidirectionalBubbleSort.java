package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;

/**
 * This bubble sort variation has two internal iterations. In the first, it
 * pushes big elements to the right, like the normal bubble sort does. Then in
 * the second, iterates the array backwards, that is, from right to left, while
 * pushing small elements to the left. This process is repeated until the array
 * is sorted.
 */
public class BidirectionalBubbleSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(array == null) {
			throw new IllegalArgumentException();
		} if(array.length == 0 || leftIndex > array.length-1 || rightIndex > array.length-1) {
			
		} else {
			int i = leftIndex;
			int j = rightIndex;
			while(i < j) {
				for(int e = i; e < j; e++) {
					if(array[e].compareTo(array[e+1]) > 0) {
						T maior = array[e];
						array[e] = array[e+1];
						array[e+1] = maior;
					}
				} j--;
			
				for(int e = j; e > i; e--)
					if(array[e].compareTo(array[e-1]) < 0) {
						T maior = array[e-1];
						array[e-1] = array[e];
						array[e] = maior;
					} i++;
			}
		}
	}
}
