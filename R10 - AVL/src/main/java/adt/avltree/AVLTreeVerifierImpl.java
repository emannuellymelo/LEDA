package adt.avltree;

import adt.bst.BSTNode;
import adt.bst.BSTVerifier;
import adt.bst.BSTVerifierImpl;
import adt.bt.BTNode;

/**
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

	@Override
	public boolean isAVLTree() {
		boolean result = false;
		if(this.getAVLTree() != null) {
			if(this.getAVLTree().isEmpty()) {
				result = true;
			} else if(isBST(this.getAVLTree().getRoot())){
				result = isAVLTree(this.getAVLTree().getRoot());
			}
		} return result;
	}
	
	private boolean isAVLTree(BTNode<T> node) {
		boolean result = true;
		if(!node.isEmpty()) {
			if(node != null && Math.abs(this.getAVLTree().calculateBalance((BSTNode<T>) node)) > 1) {
				result = false;
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
