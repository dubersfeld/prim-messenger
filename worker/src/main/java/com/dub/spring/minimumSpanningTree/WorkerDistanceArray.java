package com.dub.spring.minimumSpanningTree;

public class WorkerDistanceArray {

	private int Ncol;// number of columns
	private int[] vertices;
	private int[] refVertices;// reference vertices
	private boolean[] marked;// a vertex is marked if it already belongs to MST
	private Integer[] distances;// null means that two vertices are not adjacent
	
	public WorkerDistanceArray(InitMessage init) {
		this.Ncol = init.getNcol();
		vertices = new int[Ncol];
		refVertices = new int[Ncol];
		marked = new boolean[Ncol];
		distances = new Integer[Ncol];
		
		int j = 0;
		
		for (Integer vertex : init.getColumns().keySet()) {
			marked[j] = false;
			vertices[j] = vertex;
			refVertices[j] = 0;
			// initialize with distances extracted from first row
			distances[j++] = init.getColumns().get(vertex).getDistances()[0]; 
		}
			
		display();
	}
	
	public int getNcol() {
		return Ncol;
	}
	public void setNcol(int ncol) {
		Ncol = ncol;
	}
	public int[] getVertices() {
		return vertices;
	}
	public void setVertices(int[] vertices) {
		this.vertices = vertices;
	}
	public int[] getRefVertices() {
		return refVertices;
	}
	public void setRefVertices(int[] refVertices) {
		this.refVertices = refVertices;
	}
	
	public void setReference(int j, int refVertex) {
		this.refVertices[j] = refVertex;
	}
	
	public boolean[] getMarked() {
		return marked;
	}
	public void setMarked(boolean[] marked) {
		this.marked = marked;
	}
	
	public boolean isMarked(int i) {
		return marked[i];
	}
	public void setMarked(int i) {
		this.marked[i] = true;
	}
	
	public Integer[] getDistances() {
		return distances;
	}
	public void setDistances(Integer[] distances) {
		this.distances = distances;
	}
	
	public void setDistance(int j, Integer distance) {
		this.distances[j] = distance;
	}
	
	// for debugging only
	public void display() {
		System.out.println("\nWorkerDistanceArray display");
		for (int j = 0; j < Ncol; j++) {
			System.out.print(vertices[j] + " ");
		}
		System.out.println();
		for (int j = 0; j < Ncol; j++) {
			System.out.print(refVertices[j] + " ");
		}
		System.out.println();
		for (int j = 0; j < Ncol; j++) {
			System.out.print(distances[j] + " ");
		}
		System.out.println();
		for (int j = 0; j < Ncol; j++) {
			System.out.print(marked[j] + " ");
		}
		System.out.println();
		
	}// display
	
	public DistMin getMin() {
		
		Integer min = null;
		int jMin = 0;
		int j = 0;
		
		for (j = 0; j < Ncol; j++) {
			if (marked[j]) {
				continue;
			}
			if (distances[j] != null) {			
				if (min == null || min > distances[j]) {
					min = distances[j];
					jMin = j;
				} 
			} else {// distances[j] == null	
				if (min == null) {
					jMin = j;
				}
			}
		}// for
	
		return new DistMin(vertices[jMin], distances[jMin], refVertices[jMin]);
			
	}

	public boolean isFinished() {
		for (int j = 0; j < Ncol; j++) {
			if (!this.isMarked(j)) {
				return false;
			}
		}
		return true;
	}	
}
