package com.dub.spring.minimumSpanningTree;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class InitMessage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3700261124146614165L;
	
	private int[] vertices;
	private int N, Ncol;
	//private Integer[][] distances;
	private Map<Integer, Column> columns;
	
	public InitMessage() {
		
	}

	
	public InitMessage(DistanceMatrix distMat , int[] vertices) {
		this.N = distMat.getN(); 
		this.Ncol = vertices.length;
		this.vertices = new int[Ncol];
		for (int j = 0; j < Ncol; j++) {
			this.vertices[j] = vertices[j];
		}
		columns = new HashMap<Integer, Column>();
		Column column;
		for (int j = 0; j < vertices.length; j++) {
			// create a new Column
			column = new Column(distMat.getDistances(), vertices[j]);
			// add column to map
			columns.put(vertices[j], column);
		}
	}

	public int[] getVertices() {
		return vertices;
	}

	public void setVertices(int[] vertices) {
		this.vertices = vertices;
	}

	/*
	public Integer[][] getDistances() {
		return distances;
	}

	public void setDistances(Integer[][] distances) {
		this.distances = distances;
	}
	*/
	
	public Integer getDistance(int i, int j) {
		
		return columns.get(j).getDistances()[i];
	}
	
	public void display() {
		System.out.println("InitMessage");
		for (int j = 0; j < Ncol; j++) {
			System.out.print(vertices[j] + " ");
		}
		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < Ncol; j++) {
				System.out.print(getDistance(i, vertices[j]) + " ");// + i + " " + vertices[j]);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	
	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public int getNcol() {
		return Ncol;
	}

	public void setNcol(int ncol) {
		Ncol = ncol;
	}

	public Map<Integer, Column> getColumns() {
		return columns;
	}

	public void setColumns(Map<Integer, Column> columns) {
		this.columns = columns;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	// simple wrapper object
	public static class Column {
		
		private Integer[] distances;
		
		public Column() {
		}
		
		public Column(Integer[][] dists, int j) {
			distances = new Integer[dists.length];
			for (int i = 0; i < dists.length; i++) {
				distances[i] = dists[i][j];
			}
		}

		
		public Integer[] getDistances() {
			return distances;
		}

		public void setDistances(Integer[] distances) {
			this.distances = distances;
		}
	}
	
}
