package com.dub.spring.minimumSpanningTree;

/** POJO represents an edge for Ajax initialization request */
public class JSONEdge {
	
	protected int from;// origin
	protected int to;// end
	
	public JSONEdge() {
	}

	public JSONEdge(int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}
	
	public String toString() {// for debug only
		return from + ", " + to;
	}

}
