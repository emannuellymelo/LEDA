package adt.avltree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		if(array != null) {
			Arrays.sort(array);
			Map<Integer, List<T>> levels = new TreeMap<>();
			fillWithoutRebalance(levels, 0, array.length-1, 0, array);
			
			for(List<T> list : levels.values()) {
				list.forEach(element -> super.insert(element));
			}
		}
	}
	
	private void fillWithoutRebalance(Map<Integer, List<T>> map, int leftIndex, int rightIndex, int level, T[] array) {
		if(leftIndex <= rightIndex) {
			int middle = (leftIndex + rightIndex)/2;
			if(map.containsKey(level)) {
				map.get(level).add(array[middle]);
			} else {
				map.put(level, new ArrayList<>());
				map.get(level).add(array[middle]);
			}
			
			fillWithoutRebalance(map, leftIndex, middle-1, level + 1, array);
			fillWithoutRebalance(map, middle + 1, rightIndex, level + 1, array);
		}
	}
	
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if(Math.abs(balance) > 1) {
			if(calculateBalance(node) > 1 && calculateBalance((BSTNode<T>) node.getLeft()) >= 0) {
				this.LLcounter++;
				rightRotation(node);
			} else if(calculateBalance(node) < -1 && calculateBalance((BSTNode<T>) node.getRight()) <= 0) {
				this.RRcounter++;
				leftRotation(node);
			} else if(calculateBalance(node) > 1 && calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
				this.LRcounter++;
				leftRotation((BSTNode<T>) node.getLeft());
				rightRotation(node);
			} else if(calculateBalance(node) < -1 && calculateBalance((BSTNode<T>) node.getRight()) > 0) {
				this.RLcounter++;
				rightRotation((BSTNode<T>) node.getRight());
				leftRotation(node);
			}
		}
	}
	
}
