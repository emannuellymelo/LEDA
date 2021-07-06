package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(array == null) {
			throw new IllegalArgumentException();
		} else if (leftIndex >= rightIndex || array.length <= 1 || leftIndex > array.length-1 || rightIndex > array.length-1 || leftIndex < 0 || rightIndex < 0) {
			return;
		} else {
			int pivotIndex = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, pivotIndex-1);
			sort(array, pivotIndex+1, rightIndex);
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
}
