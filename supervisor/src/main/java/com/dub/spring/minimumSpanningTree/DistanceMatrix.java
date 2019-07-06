package com.dub.spring.minimumSpanningTree;


public class DistanceMatrix {
	
	private Integer[][] distances;
	private int N;// matrix size
	
	public DistanceMatrix(Graph graph) {
		
		N = graph.getVertices().length;
		
		distances = new Integer[N][N]; 
		
		for (int i = 0; i < N; i++) {
			System.out.println(graph.getVertices()[i].getName());
			System.out.println(graph.getVertices()[i].getAdjacency());
			for (int j = 0; j < N; j++) {
				distances[i][j] = graph.getWeight(i, j);
			}
			for (int j = 0; j < N; j++) {
				distances[j][j] = 0;
			}
		}	
	}// constructor
	
	public DistanceMatrix(int N) {
		
		this.N = N;
		
		distances = new Integer[N][N]; 
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				distances[i][j] = 42;
			}
	}// constructor

	
	// convenience methods
	public void display() {
		
		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(distances[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void displayRow(int i) {
		
		System.out.println();
		for (int j = 0; j < N; j++) {
			System.out.print(distances[i][j] + " ");
		}
		System.out.println();
	}
	
	public Integer getDistance(int i, int j) {
		return distances[i][j];
	}

	public Integer[][] getDistances() {
		return distances;
	}

	public void setDistances(Integer[][] distances) {
		this.distances = distances;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}	
}
