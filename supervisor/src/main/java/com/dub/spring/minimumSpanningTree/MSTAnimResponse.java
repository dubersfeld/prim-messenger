package com.dub.spring.minimumSpanningTree;


import java.util.ArrayList;
import java.util.List;

/** container for AJAX response */
public class MSTAnimResponse {
	
	private Graph graph;
	private StatusCode status;
	private List<JSONSnapshot> snapshots;
	
	public MSTAnimResponse() {
		status = null;
		snapshots = new ArrayList<>();
	}
	
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	

	public StatusCode getStatus() {
		return status;
	}

	public void setStatus(StatusCode status) {
		this.status = status;
	}

	

	public List<JSONSnapshot> getSnapshots() {
		return snapshots;
	}

	public void setSnapshots(List<JSONSnapshot> snapshots) {
		this.snapshots = snapshots;
	}



	public enum StatusCode {
		OK, ERROR, INIT
	}
}
