package com.dub.spring.minimumSpanningTree;

import java.io.Serializable;
import java.util.List;


/** Graph represents a weighted undirected graph. 
 * It has vertices and adjacency lists that are represented by a custom class SimpleList 
 * that partially implements the interface List (only used methods are implemented) 
 * */
public class Graph implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Vertex[] vertices;
	protected int N;
	
	public Graph(int N) {
		vertices = new Vertex[N];
		this.N = N;
	}
	
	public Vertex[] getVertices() {
		return vertices;
	}
	public void setVertices(Vertex[] vertices) {
		this.vertices = vertices;
	}
	

	public void display() {// used for debugging only
		System.out.println("Graph.display");
		for (int i1 = 0; i1 < N; i1++) {
			System.out.println(vertices[i1].getName());
		}
		System.out.println();
	}
	
	public void display2() {// used for debugging only
		System.out.println("Graph.display2");
		for (int i1 = 0; i1 < N; i1++) {// for each vertex
			System.out.print(vertices[i1].getName() + " -> ");
			for (int i2 = 0; i2 < vertices[i1].getAdjacency().size(); i2++) {
				int lind = this.vertices[i1].getAdjacency().get(i2).getTo();
				System.out.print(this.vertices[lind].getName() + " ");
			}
			System.out.println();
		}
		System.out.println();	
	}// display2	
	
	public final void display3() {// used for debugging only
		System.out.println("Graph.display3");
		for (int i1 = 0; i1 < N; i1++) {// for each vertex
			System.out.print(i1 + " -> ");
			for (int k = 0; k < vertices[i1].getAdjacency().size(); k++) {
				int lind = this.vertices[i1].getAdjacency().get(k).getTo();
				System.out.print(lind + " ");
			}
			System.out.println();
		}
		System.out.println();	
	}// display2	
	
	
	
	
	public void randomizeWeights() {
		
		/* for debugging only */
		int[] weights = {11,22,33,44,55,
						 66,77,88,99,12,
						 23,34,45,56,67,
						 78,89,13,24,35,
						 46,57,68,79};
		
		int[][] check = new int[N][N];
		
		for (int i1 = 0; i1 < N; i1++) {       
			for (int i2 = 0; i2 < N; i2++) {
			      check[i1][i2] = 0;    
			}    
		}
		   
		int weight;
		    
		int count = 0;
		for (int i1 = 0; i1 < N; i1++) {// for each vertex
			Vertex u = this.vertices[i1];
			Vertex v;
		    List<WeightedEdge> conn = u.getAdjacency();
		    
		    for (int k = 0; k < conn.size(); k++) {
		    	int i2 = conn.get(k).getTo();
		    	v = this.vertices[i2];
		    	//weight = (int)Math.floor(Math.random() * 20) + 2;// range
		    	weight = weights[count++ % 22];
		    	if (check[i1][i2] == 0 && check[i2][i1] == 0) {
		    		System.out.println("set weight " + weight + " to edge " 
		    					+ i1 + " " 
		    					+ i2);
		    		conn.get(k).setWeight(weight);
		    		
		    		// symmetry needed for weight
		    		
		    		List<WeightedEdge> conn2 = v.getAdjacency();
		    		int k2 = v.getAdjIndex(i1);
		    		
		    		conn2.get(k2).setWeight(weight);
		    		    		
		    		check[i1][i2] = 1;
			        check[i2][i1] = 1;
		    	}
		    }// for  
		}// for
				
	}// randomizeWeights
	
	// convenience method
	public Integer getWeight(int i, int j) {
		
		List<WeightedEdge> edges = this.vertices[i].getAdjacency();
		
		for (WeightedEdge edge : edges) {
			if (edge.getTo() == j) return edge.getWeight();
		}
		
		return null;
	}
	
}
