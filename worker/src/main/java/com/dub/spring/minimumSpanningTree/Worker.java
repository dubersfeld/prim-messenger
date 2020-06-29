package com.dub.spring.minimumSpanningTree;


import java.util.HashMap;
import java.util.Map;

import com.dub.spring.minimumSpanningTree.InitMessage.Column;

/**
 * Update handling uses columns and distanceArray
 * Worker receives a DistMin object from supervisor
 * and returns a new local MWOE to supervisor
 * */
public class Worker {
	
	private int Ncol;// number of columns
	private int N;// graph size 
	private Map<Integer, Column> columns;
	private WorkerDistanceArray distanceArray;
	
	private boolean first;
	
	public Worker() {
		
	}
	
	public Worker(InitMessage init) {
		/**
		 * Initialize worker with a submatrix of graph distance matrix
		 * */
		this.Ncol = init.getNcol();
		this.N = init.getN();
		columns = new HashMap<>();
		for (Integer vertex : init.getColumns().keySet()) {
			columns.put(vertex, init.getColumns().get(vertex));
		}
		init.display();
		
		this.distanceArray = new WorkerDistanceArray(init); 
		this.first = true;
	}
	
	public void init(InitMessage init) {
		/**
		 * Initialize worker with a submatrix of graph distance matrix
		 * */
		this.Ncol = init.getNcol();
		this.N = init.getN();
		columns = new HashMap<>();
		for (Integer vertex : init.getColumns().keySet()) {
			columns.put(vertex, init.getColumns().get(vertex));
		}
		init.display();
		
		this.distanceArray = new WorkerDistanceArray(init); 
		this.first = true;
	}
	
	public int getNcol() {
		return Ncol;
	}
	public void setNcol(int ncol) {
		Ncol = ncol;
	}
	public int getN() {
		return N;
	}
	public void setN(int n) {
		N = n;
	}
	public Map<Integer, Column> getColumns() {
		return columns;
	}
	public void setColumns(Map<Integer, Column> columns) {
		this.columns = columns;
	}
	public WorkerDistanceArray getDistanceArray() {
		return distanceArray;
	}
	public void setDistanceArray(WorkerDistanceArray distanceArray) {
		this.distanceArray = distanceArray;
	}
	
	public DistMin getMin() {
		return distanceArray.getMin();
	}
	
	public boolean isFinished() {
		return distanceArray.isFinished();
	}
	
	public void updateAdjacency(DistMin distMin) {
		/**
		 * Updates the local MWOE array
		 * */
		int i0 = distMin.getVertex();//row index
		// mark vertex if vertex i0 is local to worker
		for (int j = 0; j < Ncol; j++) {
			if (distanceArray.getVertices()[j] == i0) {
				distanceArray.setMarked(j);
				System.out.println("Marked " + i0);
			}
		}// for
	
		// update distances of all local vertices adjacent to v
		
		// get matrix row that belongs to v
		displayRow(i0);
		distanceArray.display();
				
		for (int j = 0; j < Ncol; j++) {
			if (distanceArray.isMarked(j)) continue;
			int vertex = distanceArray.getVertices()[j];
			Integer dArray = distanceArray.getDistances()[j];
			Integer dRow = columns.get(vertex).getDistances()[i0];

			// change if dRow < dArray only		
			if ((dRow != null && dArray == null)
							||
				(dArray != null && dRow != null && dRow  < dArray)) 
			{
				// actual distance update		
				distanceArray.setDistance( j, columns.get(vertex).getDistances()[i0] );
				
				// actual reference update
				distanceArray.setReference(j, i0);		
			}
						
		}// for
		
		distanceArray.display();
	}// updateAdjacency
	
	// used in debugging only
	private void displayRow(int i) {
		System.out.println("displayRow");
		int j = 0;
		int[] vertices = new int[Ncol];
		for (Integer vertex : columns.keySet()) {
			vertices[j++] = vertex;
		}
		for (j = 0; j < Ncol; j++) {
			System.out.print(vertices[j] + " ");
		}
		System.out.println();
		for (j = 0; j < Ncol; j++) {
			System.out.print(columns.get(vertices[j]).getDistances()[i] + " ");
		}
		System.out.println();
	}
	
	// used in debugging only
	private void displayColumns() {
		System.out.println("Vertices");
		int[] locVert = new int[Ncol];
		int j = 0;
		for (Integer vertex : columns.keySet()) {
			locVert[j++] = vertex;
		}
		
		System.out.println("Columns");
		for (j = 0; j < Ncol; j++) {// for each column
			System.out.print(locVert[j] + " ");
		}
		System.out.println();
		for (int i = 0; i < N; i++) {// for each row
			for (j = 0; j < Ncol; j++) {
				System.out.print(columns.get(locVert[j]).getDistances()[i] + " ");
				
			}
			System.out.println();
		}	
	}// displayColums

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}
	
	
	
}
