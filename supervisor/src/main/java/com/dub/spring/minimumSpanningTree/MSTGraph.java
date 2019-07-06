package com.dub.spring.minimumSpanningTree;

import java.util.List;


/** Graph has Vertices and Adjacency Lists */
public class MSTGraph extends Graph {
	
	/**
	 * This subclass of Graph implements a Depth First Search algorithm
	 */
	private static final long serialVersionUID = 1L;
	
	private int N;
	
	private DistanceMatrix distanceMatrix;
	
	private boolean finished = false;
	
	private int cost;// partial cost
	
	int[] keys; 
	
	int time = 0;
	
	
	public MSTGraph(int N) {
		super(N);
		this.N = N;
		cost = 0;
		keys = new int[N];
	}
	
	public MSTGraph(MSTGraph source) {// deep copy c'tor
		super(source.N);
		this.N = source.N;
		this.finished = false;
			 		
		for (int i = 0; i < source.N; i++) {
			MSTVertex dfsVertex = (MSTVertex)source.getVertices()[i];
			this.getVertices()[i] = new MSTVertex(dfsVertex);
		}
	
	}
	
	
	
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public void display() {// used for debugging only
		for (int i = 0; i < N; i++) {
			MSTVertex mstv = (MSTVertex)vertices[i];
			System.out.println(mstv);
		}
		System.out.println();
	}
	
	public void displayWeights() {
		System.out.println("displayWeights");
		for (Vertex v : vertices) {// for each vertex
			for (WeightedEdge edge : v.getAdjacency()) {
				System.out.println(v.getName() + " "  
							+ this.vertices[edge.getTo()] + " " 
							+ edge.getWeight());
			}// for
		}// for
	}
	
	public void displayMST() {// used for debugging only
		System.out.println("displayMST");
		for (int i1 = 0; i1 < N; i1++) {
			String name = vertices[i1].getName();
			Integer pIndex = ((MSTVertex)vertices[i1]).getParent();
			String parent = pIndex == null ? "" : vertices[pIndex].getName();
			System.out.println(name + " p " + parent);
		}
		System.out.println();
	}

	public DistanceMatrix getDistanceMatrix() {
		return distanceMatrix;
	}

	public void setDistanceMatrix(DistanceMatrix distanceMatrix) {
		this.distanceMatrix = distanceMatrix;
	}
	
	
	
}
