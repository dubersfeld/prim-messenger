package com.dub.spring.events.model;


import java.util.HashMap;
import java.util.Map;

import com.dub.spring.minimumSpanningTree.InitMessage;

// simple wrapper for InitMessage 
public class InitWrapper {

	Map<Integer, InitMessage> initMessages;
	
	public InitWrapper() {
		initMessages = new HashMap<>();
	}

	public Map<Integer, InitMessage> getInitMessages() {
		return initMessages;
	}

	public void setInitMessages(Map<Integer, InitMessage> initMessages) {
		this.initMessages = initMessages;
	}
}

