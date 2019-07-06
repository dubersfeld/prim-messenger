package com.dub.spring.util;


public class ListNode<T> {
	
	private T obj;
	private ListNode<T> next;
	
	public ListNode() {
		
	}
	
	public ListNode(T obj) {
		this.obj = obj;
		this.next = null;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public ListNode<T> getNext() {
		return next;
	}

	public void setNext(ListNode<T> next) {
		this.next = next;
	}

	
}
