package com.dub.spring.util;

import java.util.List;


public class SimpleStack<T> {
	
	/** A generic stack implementation */
	private List<T> list;
	
	public SimpleStack() {
		list = new SimpleList<T>();
	}
	
	public T pop() {
		if (!list.isEmpty()) {
			//T top = list.get(list.size()-1);
		
			T top = list.remove(list.size()-1);
		
			return top;
		} else {
			return null;
		}
	}

	public void push(T obj) {
		list.add(obj);
	}
	
	public T peek() {
		if (!list.isEmpty()) {
			return list.get(list.size()-1);
		} else {
			return null;
		}
	}
	
	
	public boolean isEmpty() {
		return list.isEmpty();
	}

}
	

