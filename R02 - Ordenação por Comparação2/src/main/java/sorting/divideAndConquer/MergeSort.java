package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(array == null) {
			throw new IllegalArgumentException();
		} else if (leftIndex >= rightIndex || array.length <= 1 || leftIndex > array.length-1 || rightIndex > array.length-1 || leftIndex < 0 || rightIndex < 0) {
			return;
		} else {
			int middle = (leftIndex+rightIndex)/2;
			sort(array, leftIndex, middle);
			sort(array, middle+1, rightIndex);
			merge(array, leftIndex, middle, rightIndex);
		}
	}
	
	private void merge(T[] array, int leftIndex, int middle, int rightIndex) {
		T[] auxiliar = array.clone();
		
		int l = leftIndex;
		int m = middle+1;
		int p = leftIndex;
		
		while(l <= middle && m <= rightIndex) {
			if(auxiliar[l].compareTo(auxiliar[m]) < 0) {
				array[p] = (T) auxiliar[l];
				l++;
			} else {
				array[p] = (T) auxiliar[m];
				m++;
			} p++;
		}
		
		while(l <= middle) {
			array[p] = (T) auxiliar[l];
			l++;
			p++;
		}
		
		while(m <= rightIndex) {
			array[p] = (T) auxiliar[m];
			m++;
			p++;
		}
	}
}
