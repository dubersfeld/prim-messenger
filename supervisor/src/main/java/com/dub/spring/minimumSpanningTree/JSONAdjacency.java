package com.dub.spring.minimumSpanningTree;

/** Encapsulation of an adjacency list with weights */
public class JSONAdjacency {
	
	private WeightedEdge[] adjacency;
	
	public JSONAdjacency(int N) {
		this.adjacency = new WeightedEdge[N];
	}
	
	

	public WeightedEdge[] getAdjacency() {
		return adjacency;
	}

	public void setAdjacency(WeightedEdge[] adjacency) {
		this.adjacency = adjacency;
	}
	
	
	// for debugging only
	public void display() {
		for (int i = 0; i < this.adjacency.length; i++) {
			System.out.println(this.adjacency[i]);
		}
	}
	
}
