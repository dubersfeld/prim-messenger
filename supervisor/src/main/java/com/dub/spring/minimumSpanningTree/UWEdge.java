package com.dub.spring.minimumSpanningTree;

public class UWEdge implements Comparable<UWEdge> {
	
	/** This class represents an undirected weighted edge 
	 * for the MST algorithm. 
	 * It is needed to sort edges by nondecreasing weight
	 */ 
	private int i1;// indices of vertices
	private int i2;
	private int weight;
			
	public UWEdge(int i1, int i2, int weight) {
		this.i1 = i1;
		this.i2 = i2;
		this.weight = weight;
	}		
	

	public int getI1() {
		return i1;
	}

	public void setI1(int i1) {
		this.i1 = i1;
	}

	public int getI2() {
		return i2;
	}

	public void setI2(int i2) {
		this.i2 = i2;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}




	@Override
	public int compareTo(UWEdge o) {
		if (this.weight < o.weight) {
			return -1;
		} else if (this.weight > o.weight) {
			return 1;
		} else {
			return 0;
		}
	}
			
	public String toString() {
		return i1 + " " + i2 + " " + weight;
	}
			
	
}
