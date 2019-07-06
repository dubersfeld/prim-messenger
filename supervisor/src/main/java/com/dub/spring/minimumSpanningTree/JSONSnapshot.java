package com.dub.spring.minimumSpanningTree;

/** This class represents an MST graph as a JSON object.
 * It is used in response to Ajax requests */

public class JSONSnapshot {
	
	/** An array of vertices */
	private JSONVertex[] vertices;// vertices name only
	private JSONAdjacency[] adjacencies;
	private int N;
	private int cost;
	
	
	public JSONSnapshot(int N) {
		vertices = new JSONVertex[N];
		adjacencies = new JSONAdjacency[N];
		this.N = N;
	}
	
	public JSONSnapshot(JSONVertex[] vertices, JSONAdjacency[] adjacencies) {
		this.N = vertices.length;
		this.vertices = new JSONVertex[N];
		this.adjacencies = new JSONAdjacency[N];
		for (int i = 0; i < N; i++) {
			this.vertices[i] = vertices[i];
		}
		for (int i = 0; i < N; i++) {
			this.adjacencies[i] = adjacencies[i];
		}
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public JSONVertex[] getVertices() {
		return vertices;
	}

	public void setVertices(JSONVertex[] vertices) {
		this.vertices = vertices;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	
	public JSONAdjacency[] getAdjacencies() {
		return adjacencies;
	}

	public void setAdjacencies(JSONAdjacency[] adjacencies) {
		this.adjacencies = adjacencies;
	}

	// for debugging only
	public void displayVertices() {
		System.out.println("\nsnapshot: vertices");
		for (int i = 0; i < N; i++) {
			System.out.println(vertices[i]);
		}
	}
	
	public void displayAdj() {
		System.out.println("\nsnapshot: adjacencies");
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < adjacencies[i].getAdjacency().length; k++) {
				System.out.println(adjacencies[i].getAdjacency()[k]);
			}
		}
	}// displayAdj
}
