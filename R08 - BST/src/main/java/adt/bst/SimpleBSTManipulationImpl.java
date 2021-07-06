package adt.bst;


/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		return equals((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
	}
	
	private boolean equals(BSTNode<T> node1, BSTNode<T> node2) {
		boolean result = false;
		if(node1.isEmpty() && node2.isEmpty()) {
			result = true;
		} else if(!node1.isEmpty() && !node2.isEmpty()) {
			if(node1.equals(node2)) {
				result = equals((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft()) && equals((BSTNode<T>) node2.getRight(), (BSTNode<T>) node2.getRight());
			} else {
				result = false;
			}
		} else {
			result = false;
		} return result;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		return isSimilar((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
	}
	
	private boolean isSimilar(BSTNode<T> node1, BSTNode<T> node2) {
		boolean result = false;
		if(node1.isEmpty() && node2.isEmpty()) {
			result = true;
		} else if(!node1.isEmpty() && !node2.isEmpty()) {
			result = isSimilar((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft()) && isSimilar((BSTNode<T>) node2.getRight(), (BSTNode<T>) node2.getRight());
		} else {
			result = false;
		} return result;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		T result;
		if(tree.isEmpty()) {
			result =  null;
		} else {
			result = orderStatistic(tree, tree.minimum(), k, 1);
		} return result;
	}
	
	public T orderStatistic(BST<T> tree, BSTNode<T> node, int k, int i) {
		T result = null;
		if(!node.isEmpty()) {
			if(k == i) {
				result = node.getData();
			} else {
				result  = orderStatistic(tree, tree.sucessor(node.getData()), k, i+1);
			}
		} return result;
	}

}
