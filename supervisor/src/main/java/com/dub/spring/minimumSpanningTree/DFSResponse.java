package com.dub.spring.minimumSpanningTree;


/** container object for Ajax response 
 * contains snapshots of the graph created by the DFS loop 
 **/
public class DFSResponse {
	private Status status;
	private StepResult result;
	

	public DFSResponse() {
		status = null;
		result = new StepResult();
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public StepResult getResult() {
		return result;
	}

	public void setResult(StepResult result) {
		this.result = result;
	}





	public static enum Status {
		OK, ERROR
	}
}
