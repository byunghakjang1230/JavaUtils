package com.jbh1230.datastructure;

public class SingleLinkedList<E> {

	private int listSize = 0;
	private Node<E> first;
	private Node<E> prev;
	
	private class Node<E>{
		private E value;
		private Node<E> next;
		
		Node(E val, Node<E> next){
			this.value 	= val;
			this.next 	= next;
		}
	}
	
	public int add(E val) {
		Node<E> node = new Node(val, null);
		if(this.listSize == 0)
			this.first = node;
		this.prev = node;
		return this.listSize++;
	}
	
	public E get(int idx) {
		checkOutofLength(idx);
		Node<E> findNode = new Node<>(null, this.first);
		for(int i = 0; i <= idx; i++)
			findNode = findNode.next;
		
		return findNode.value;
	}
	
	public void remove(int idx) {
		checkOutofLength(idx);
		Node<E> findNode = new Node<>(null, this.first);
		for(int i = 0; i <= idx; i++)
			findNode = findNode.next;
		unLinkedNode(findNode);
	}
	
	private void checkOutofLength(int idx) {
		if(idx < 0 || idx >= this.listSize)
			new IndexOutOfBoundsException();
	}
	
	public void unLinkedNode(Node<E> el) {
		el = el.next;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(Node<E> x = first; x != null; x = x.next) {
			sb.append(x.value.toString() + "");
			if(x.next != null)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
}
