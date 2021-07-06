package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BT;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 *  - insert
 *  - preOrder
 *  - postOrder
 *  - remove
 *  - height
 *  - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int balance = 0;
		if(node != null && !node.isEmpty()) {
			int leftHeight = height(node.getLeft());
			int rightHeight = height(node.getRight());
			balance = leftHeight - rightHeight;
		}
		return balance;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if(Math.abs(balance) > 1) {
			if(calculateBalance(node) > 1 && calculateBalance((BSTNode<T>) node.getLeft()) >= 0) {
				rightRotation(node);
			} else if(calculateBalance(node) < -1 && calculateBalance((BSTNode<T>) node.getRight()) <= 0) {
				leftRotation(node);
			} else if(calculateBalance(node) > 1 && calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
				leftRotation((BSTNode<T>) node.getLeft());
				rightRotation(node);
			} else if(calculateBalance(node) < -1 && calculateBalance((BSTNode<T>) node.getRight()) > 0) {
				rightRotation((BSTNode<T>) node.getRight());
				leftRotation(node);
			}
		}
	}
	
	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		
		while(parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}
	
	protected void leftRotation(BSTNode<T> node) {
		if (node != null) {
			BSTNode<T> aux = Util.leftRotation(node);
			if (this.root.equals(node)) {
				this.root = aux;
			}
		}
	}
	
	protected void rightRotation(BSTNode<T> node) {
		if (node != null) {
			BSTNode<T> aux = Util.rightRotation(node);
			if (this.root.equals(node)) {
				this.root = aux;
			}
		}
	}
	
	@Override
	public void insert(T element) {
		if(element != null) {
			insert(this.root, element);
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if(node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
			node.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());

		} else {
			if(element.compareTo(node.getData()) > 0){
				this.insert((BSTNode<T>) node.getRight(), element);
			} else if(element.compareTo(node.getData()) < 0) {
				this.insert((BSTNode<T>) node.getLeft(), element);
			} rebalance(node);
		}
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = super.search(element);

		super.remove(element);
		this.rebalanceUp(node);
	}
	
}
