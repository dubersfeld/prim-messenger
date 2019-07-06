package com.dub.spring.events.source;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.dub.spring.events.model.WorkerMessageModel;
import com.dub.spring.events.model.InitWrapper;

import com.dub.spring.minimumSpanningTree.DistMin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class SimpleSourceBean {
	
	@Autowired
	ObjectMapper mapper;
	
    private Source source;// provided

    // implementation of Source interface injected by Spring Cloud Stream
    @Autowired
    public SimpleSourceBean(Source source) {
        this.source = source;
    }

            
    public void broadcastInit(InitWrapper wrapper) {
        /** This method is called only once */ 	
    	// first convert to JSON string
    	try {
    		String wrapperStr = mapper.writeValueAsString(wrapper);
    		WorkerMessageModel change = new WorkerMessageModel(
         				"INIT",
         				wrapperStr
         			);
    		System.out.println("Sending message to all workers");
    		source.output().send(MessageBuilder.withPayload(change).build());

    	} catch (JsonProcessingException e) {
    		e.printStackTrace();
    	}  
    }// broadcastInit
    
    public void broadcastStep(DistMin distMin) {
    	/** This method is called at each step */
    	// first convert to JSON string
    	try {
    		String distMinStr = mapper.writeValueAsString(distMin);
    		WorkerMessageModel change = new WorkerMessageModel(
         				"STEP",
         				distMinStr
         			);
    		System.out.println("Sending message to all workers "
    				+ distMinStr);
    		source.output().send(MessageBuilder.withPayload(change).build());

    	} catch (JsonProcessingException e) {
    		e.printStackTrace();
    	}  
    	
    }
     
   
}