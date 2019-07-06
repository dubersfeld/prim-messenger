package com.dub.spring.util;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/** A custom partial implementation of List. 
 * Only methods that are used in this application are implemented 
 * */
public class SimpleList<E> implements List<E> {
	
	int N;
	ListNode<E> head; // sentinel
	
	public SimpleList() {
		N = 0;
		head = new ListNode<E>();
		head.setNext(head);
	} 

	@Override
	public int size() {
		return N;
	}

	@Override
	public boolean isEmpty() {
		return (head.getNext() == head);
	}

	@Override
	public boolean contains(Object o) {
		ListNode<E> n1 = head.getNext();
		while (n1 != head) {
			if (o.equals(n1.getObj())) {
				break;
			}
			n1 = n1.getNext();
		}// while
		return (n1 != head);
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		if (isEmpty()) {
			return null;
		} else {
			Object[] array = new Object[N];
			ListNode<E> n1 = head.getNext();
			int i = 0;
			while (n1 != head) {
				array[i++] = n1.getObj();
				n1 = n1.getNext();
			}
			return array;
		}
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E e) {
		ListNode<E> n1 = head;
		while (n1.getNext() != head) {
			n1 = n1.getNext();
		}
		ListNode<E> node = new ListNode<>();
		node.setObj(e);
		n1.setNext(node);
		node.setNext(head);
		this.N++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		if (this.contains(o)) {
			ListNode<E> n1, n2;
			n1 = head;
			n2 = head.getNext();

			while (!o.equals(n2.getObj())) {
				n1 = n2;
				n2 = n2.getNext();	
			}
		
			if (n1 == head) {
				head.setNext(n2.getNext());
			} else {
				n1.setNext(n2.getNext());
			}
			N--;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		head.setNext(head);
		N = 0;
		
	}

	@Override
	public E get(int index) {
		int i = 0;
		ListNode<E> n1 = head.getNext();
		while (n1 != null) {
			if (i == index) {
				break;
			}
			n1 = n1.getNext();
			i++;
		}
		if (n1 != head) {
			return (E)n1.getObj();
		} else {
			return null;
		}
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E remove(int index) {
		if (index < N) {
			int i = 0;
			ListNode<E> n1, n2; 
			n1 = head;
			n2 = head.getNext();
			for (i = 0; i < index; i++) {
				n1 = n2;
				n2 = n2.getNext();
			}
			if (n1 == head) {
				head.setNext(n2.getNext());
			} else {
				n1.setNext(n2.getNext());
			}
			N--;
			return (E)n2.getObj();
		} else {
			return null;
		}
		
	}

	@Override
	public int indexOf(Object o) {
		ListNode<E> n1 = head.getNext();
		int i = 0;
		while (n1 != head) {
			if (o.equals(n1.getObj())) {
				break;
			}
			n1 = n1.getNext();
			i++;
		}// while
		
		return (n1 != head) ? i : -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		SimpleListIterator<E> it = new SimpleListIterator<>(this);
		return it;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
