package com.dub.spring.events.model;


public class WorkerMessageModel {

    private String type;
    private String payload;
    
    public WorkerMessageModel() {
    }

    
    public WorkerMessageModel(String type, String payload) {
        this.type = type;
        this.payload = payload;
    }

	
	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
