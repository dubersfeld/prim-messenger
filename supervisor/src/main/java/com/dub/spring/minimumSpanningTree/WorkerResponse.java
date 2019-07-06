package com.dub.spring.minimumSpanningTree;


/**
 * A worker  should send back its local minimum 
 * with vertex and reference vertex involved
 * */
public class WorkerResponse {
		
	private DistMin distMin;
	private Code status;
	
	public WorkerResponse() {
		
	}
	
	public WorkerResponse(DistMin distMin) {
		this.distMin = distMin;
	}
	
	public WorkerResponse(DistMin distMin, boolean finished) {
		this.distMin = distMin;
		this.status = finished ? Code.FINISHED : Code.WORKING;
	}
	
	
	
	public DistMin getDistMin() {
		return distMin;
	}

	public void setDistMin(DistMin distMin) {
		this.distMin = distMin;
	}

	public Code getStatus() {
		return status;
	}

	public void setStatus(Code status) {
		this.status = status;
	}




	public enum Code {
		FINISHED, WORKING;
	}

}
