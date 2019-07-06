package com.dub.spring.minimumSpanningTree;

import java.io.Serializable;

/**
 * This class encapsulates 
 * a Minimal Weight Outgoing Edge (MWOE)
 * */
public class DistMin implements Comparable<DistMin>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4455743249952853732L;
	private int vertex;// vertex index
	private Integer distance;// distance to current tree
	private int refVertex;// tree vertex giving minimum distance
	
	public DistMin() {		
	}
	
	public DistMin(int vertex, Integer distance, int refVertex) {
		this.vertex = vertex;
		this.distance = distance;
		this.refVertex = refVertex;
	}
	
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public int getRefVertex() {
		return refVertex;
	}
	public void setRefVertex(int refVertex) {
		this.refVertex = refVertex;
	}

	public int getVertex() {
		return vertex;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}

	@Override
	public int compareTo(DistMin that) {
		if (this.distance == null && that.distance == null) {
			return 0;
		} else if (this.distance == null && that.distance != null) {
			return 1;
		} else if (this.distance != null && that.distance == null) {
			return -1;
		} else if (this.distance != null && that.distance != null) {
			if (this.distance > that.distance) {
				return 1;
			} else if (this.distance < that.distance) {
				return -1;
			} else {
				return 0;
			}
		}
		return 0;
	}
	
	public String display() {
		return vertex + " " + distance + " " + refVertex;
	}
	

}
