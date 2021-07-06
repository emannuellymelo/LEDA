package adt.bst;

import adt.bt.BTNode;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		boolean result = false;
		if(this.getBSt() != null) {
			if(this.getBSt().isEmpty()) {
				result = true;
			} else {
				result = isBST(this.getBSt().getRoot());
			}
		} return result;
	}
	
	private boolean isBST(BTNode<T> node) {
		boolean result = true;
		if(!node.isEmpty()) {
			if(node.getLeft().getData() != null && node.getLeft().getData().compareTo(node.getData()) > 0) {
				result = false;
			}
			if(node.getRight().getData() != null && node.getRight().getData().compareTo(node.getData()) < 0) {
				result = false;
			}
			
			if(result) {
				result = isBST(node.getLeft()) && isBST(node.getRight());
			}
		} return result;
	}
	
}
