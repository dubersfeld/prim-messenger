package com.dub.spring.minimumSpanningTree;


import java.util.List;

import com.dub.spring.util.SimpleList;


/** Vertex has an adjacency list of vertices */
public class Vertex {

	/**
	 * 
	 */
	protected String name = "";   
		
	protected List<WeightedEdge> adjacency;// all adjacent vertices with edge weights encapsulated
	
	public Vertex() {
		adjacency = new SimpleList<WeightedEdge>();
	}
	
	public Vertex(Vertex source) {
		this.name = source.name;
		this.adjacency = new SimpleList<WeightedEdge>();
		for (int i = 0; i < source.adjacency.size(); i++) {
			this.adjacency.add(new WeightedEdge(source.adjacency.get(i)));
		}
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<WeightedEdge> getAdjacency() {
		return adjacency;
	}

	public void setAdjacency(List<WeightedEdge> adjacency) {
		this.adjacency = adjacency;
	}
	
	// helper function that return adjacency index of a vertex index
	public Integer getAdjIndex(int index) {
		int n = this.adjacency.size();
		int k = 0;
		for (k = 0; k < n; k++) {
			if (this.adjacency.get(k).getTo() == index) {
				break;
			}
		}// for
		if (k < n) {
			return k;
		} else {
			return null;
		}
	}

}
