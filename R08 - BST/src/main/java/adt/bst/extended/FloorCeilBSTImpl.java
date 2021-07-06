package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		BSTImpl<Integer> tree = insert(array);
		Integer result = null;
		Integer element = floor(tree.getRoot(), numero);
		if(element != null) {
			result = element;
		} return result;
	}
	
	private Integer floor(BSTNode<Integer> node, double number) {
		Integer result;
		if(node.getData() == null) {
			result = null;
		} else if(number == node.getData()) {
			result = node.getData();
		} else if(number < node.getData()) {
			result = floor((BSTNode<Integer>) node.getLeft(), number);
		} else {
			Integer rightElement = floor((BSTNode<Integer>) node.getRight(), number);
			if(rightElement != null) {
				result = rightElement;
			} else {
				result = node.getData();
			}
		} return result;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		BSTImpl<Integer> tree = insert(array);
		Integer result = null;
		Integer element = ceil(tree.getRoot(), numero);
		if(element != null) {
			result = element;
		} return result;
	}
	
	private Integer ceil(BSTNode<Integer> node, double number) {
		Integer result;
		if(node.getData() == null) {
			result = null;
		} else if(number == node.getData()) {
			result = node.getData();
		} else if(number > node.getData()) {
			result = ceil((BSTNode<Integer>) node.getRight(), number);
		} else {
			Integer leftElement = ceil((BSTNode<Integer>) node.getLeft(), number);
			if(leftElement != null) {
				result = leftElement;
			} else {
				result = node.getData();
			}
		} return result;
	}
	
	private BSTImpl<Integer> insert(Integer[] array) {
		BSTImpl<Integer> bst = new BSTImpl<Integer>();
		for(int element : array) {
			bst.insert(element);
		} return bst;
	}

}
