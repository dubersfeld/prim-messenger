package com.dub.spring.minimumSpanningTree;


public class MSTVertex extends Vertex {

	/**
	 * MSTVertex subclasses Vertex for MSTSearch.
	 * It contains all additional fields specific to Depth First Search
	 */

	private Integer parent = null;// parent index
	private int key = 0;
	
	public MSTVertex() {
		super();
	}
	
	public MSTVertex(Vertex source) {
		super(source);
		this.parent = null;
	}
	
	public MSTVertex(MSTVertex source) {
		super(source);
		this.parent = source.parent;
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

	
	public String toString() {
		return name + " " + parent;
	}

}
