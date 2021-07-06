package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;

public class ThreeWayQuickSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot,
	 * particionamos o array colocando os menores a esquerda do pivot 
	 * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia 
	 * recursivamente na particao a esquerda do pivot e na particao a direita do pivot. 
	 * 
	 * Considerando um array com muitos elementos repetidos, a estrategia do quicksort 
	 * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria 
	 * eh conhecida como quicksort tree way e consiste da seguinte ideia:
	 * - selecione o pivot e particione o array de forma que
	 *   * arr[l..i] contem elementos menores que o pivot
	 *   * arr[i+1..j-1] contem elementos iguais ao pivot.
	 *   * arr[j..r] contem elementos maiores do que o pivot. 
	 *   
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
	 * recursivamente. 
	 **/
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(array == null) {
			throw new IllegalArgumentException();
		} else if (leftIndex >= rightIndex || array.length <= 1 || leftIndex > array.length-1 || rightIndex > array.length-1 || leftIndex < 0 || rightIndex < 0) {
			return;
		} else {
			int[] pivotReference = partition(array, leftIndex, rightIndex).clone();
			int left = pivotReference[0];
		    int right = pivotReference[1];
			sort (array, leftIndex, left-1);
		    sort (array, right+1, rightIndex);
		}
	}
	
	public int[] partition(T[] array, int leftIndex, int rightIndex) {
	    T pivotValue = array[leftIndex];
	    int left = leftIndex;
	    int right = rightIndex;
	    int i = leftIndex;
	
	    while (i <= right){
	    	if (array[i].compareTo(pivotValue) < 0){
	    		util.Util.swap(array, left++, i++);
	         } else if (pivotValue.compareTo(array[i]) < 0){
	            util.Util.swap(array, i, right--);
	         } else{
	            i++;
	         }    
	    }
	    int[] pivotReference = new int[2];
	    pivotReference[0] = left;
	    pivotReference[1] = right;
	    return pivotReference;
	}

}
