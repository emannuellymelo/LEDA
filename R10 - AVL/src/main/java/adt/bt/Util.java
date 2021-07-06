package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return - noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> rightPivot = (BSTNode<T>) node.getRight();
		BSTNode<T> leftPivot = (BSTNode<T>) rightPivot.getLeft();
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		
		if(parent != null) {
			if(parent.getLeft().equals(node)) {
				parent.setLeft(rightPivot);
			} else {
				parent.setRight(rightPivot);
			}
		}
		
		rightPivot.setParent(parent);
		node.setParent(rightPivot);
		node.setRight(leftPivot);
		rightPivot.setLeft(node);
		
		if(leftPivot != null) {
			leftPivot.setParent(node);
		} return rightPivot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> leftPivot = (BSTNode<T>) node.getLeft();
		BSTNode<T> rightPivot = (BSTNode<T>) leftPivot.getRight();
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		
		if(parent != null) {
			if(parent.getLeft().equals(node)) {
				parent.setLeft(leftPivot);
			} else {
				parent.setRight(leftPivot);
			}
		}
		
		leftPivot.setParent(parent);
		node.setParent(leftPivot);
		node.setLeft(rightPivot);
		leftPivot.setRight(node);
		
		if(rightPivot != null) {
			rightPivot.setParent(node);
		} return leftPivot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
