package com.dub.spring.minimumSpanningTree;


/** POJO represents vertex for AJAX initialization request */
public class JSONVertex {
	
	/**
	 * 
	 */
	private String name;
	private Integer parent;// parent index
	private int key; 
	
	public JSONVertex(MSTVertex v) {
		this.name = v.getName();
		this.parent = v.getParent();
		this.key = v.getKey();
	}
	
	
	public JSONVertex() {
	}


	
	public int getKey() {
		return key;
	}


	public void setKey(int key) {
		this.key = key;
	}


	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name + " " + parent;
	}

}
