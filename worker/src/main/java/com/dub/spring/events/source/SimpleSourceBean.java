package com.dub.spring.events.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.dub.spring.events.model.MessageModel;
import com.dub.spring.minimumSpanningTree.WorkerResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class SimpleSourceBean {
	
	ObjectMapper mapper = new ObjectMapper();
	
    private Source source;// provided

    // implementation of Source interface injected by Spring Cloud Stream
    @Autowired
    public SimpleSourceBean(Source source) {
        this.source = source;
    }

    
    public void publishWorkerResponse(WorkerResponse response) {
    	 
        System.out.println("\npublishWorkerResponse begin");
        
        // first convert to JSON String
    	String responseStr;
		try {
			responseStr = mapper.writeValueAsString(response);
		 	MessageModel change =  new MessageModel(
	                "WORKER_RESPONSE",
	                responseStr
	                );
	    	//System.out.println("Sending message to broker");
	       
	    	System.out.println("Sending response "
					+ response.getDistMin().display());
	    	source.output().send(MessageBuilder.withPayload(change).build());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	   
     }
    
    
}