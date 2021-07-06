package problems;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		int leftIndex = 0;
		int rightIndex = array.length-1;
		verifyOrder(array);
		Integer floorResultIndex = binarySearch(array, x, leftIndex, rightIndex);
		if(floorResultIndex == -1) {
			return null;
		} else {
			return array[floorResultIndex];
		}

	}
	
	public Integer binarySearch(Integer[] array, Integer x, int leftIndex, int rightIndex) {
		if(leftIndex <= rightIndex) {
			int middle = (leftIndex + rightIndex)/2;
			
			if(array[middle] == x) {
				return middle;
			} else if(array[middle] < x) {
				int recursionResult = binarySearch(array, x, middle+1, rightIndex);
				return recursionResult == -1 ? middle : recursionResult;
			} else {
				return binarySearch(array, x, leftIndex, middle-1);
			}
		}
		return -1;
	}
	
	public Integer[] verifyOrder(Integer[] array) {
		boolean ordered = true;
		for(int e = 0; e < array.length-1; e++) {
			if(array[e].compareTo(array[e+1]) > 0) {
				ordered = false;
				break;
			}
		}
		if(!ordered) {
			quickSort(array, 0, array.length-1);
		}
		return array;
	}
	
	public void quickSort(Integer[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex) {
			int pivotIndex = partition(array, leftIndex, rightIndex);
			quickSort(array, leftIndex, pivotIndex-1);
			quickSort(array, pivotIndex+1, rightIndex);
		}
	}
	
	private int partition(Integer[] array, int leftIndex, int rightIndex) {
		Integer pivot = array[leftIndex];
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
