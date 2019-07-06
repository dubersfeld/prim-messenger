package com.dub.spring.minimumSpanningTree;

/** this class encapsulates a weighted edge 
 * in an adjacency list representation
 * */
public class WeightedEdge {
	
	private int to; // adjacent vertex index
	private int weight = 0;
	
	public WeightedEdge(int to) {
		this.to = to;
	}

	public WeightedEdge(WeightedEdge source) {
		this.to = source.to;
		this.weight = source.weight;
	}
	
	public WeightedEdge(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}
	
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
	public String toString() {
		return to + " " + weight;
	}

}
