package com.dub.spring.util;

import java.util.ListIterator;

public class SimpleListIterator<E> implements ListIterator<E> {
	
	ListNode<E> n1, n2, head;
	
	public SimpleListIterator(SimpleList<E> list) {
		this.head = list.head;
		n1 = list.head;
		n2 = list.head.getNext();
	} 

	@Override
	public boolean hasNext() {
		return (n1.getNext() != head);
	}

	@Override
	public E next() {
		n1 = n1.getNext();
		return n1.getObj();
	}

	@Override
	public boolean hasPrevious() {
		n2 = head;
		while (n2.getNext() != n1) {
			n2 = n2.getNext();
		}
		return (n2 != head) ? true : false;
	}

	@Override
	public E previous() {
		n2 = head;
		while (n2.getNext() != n1) {
			n2 = n2.getNext();
		}
		n1 = n2;
		return n2.getObj();
	}

	@Override
	public int nextIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int previousIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(E e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(E e) {
		// TODO Auto-generated method stub
		
	}

}
