package com.dub.spring.events.handler;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.dub.spring.events.model.InitWrapper;
import com.dub.spring.events.model.MessageModel;
import com.dub.spring.events.source.SimpleSourceBean;
import com.dub.spring.minimumSpanningTree.DistMin;
import com.dub.spring.minimumSpanningTree.InitMessage;
import com.dub.spring.minimumSpanningTree.Worker;
import com.dub.spring.minimumSpanningTree.WorkerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * This class handles all messages from supervisor
 * only two types of messages are allowed: INIT and STEP
 * INIT is used only once to initialize the worker
 * STEP is used in a loop
 * */
@EnableBinding(Sink.class)
public class MessageHandler {
	
	@Autowired
	private Worker worker;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Value("${worker.id}")
	int workerId;
	
	@Autowired
	SimpleSourceBean simpleSourceBean;

    //private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    @StreamListener(Sink.INPUT)
    public void workerSink(MessageModel message) {
       
    	String type = message.getType();
    	
    	if (type.equals("INIT")) {
    		// handling of an INIT message
    		InitWrapper wrapper;
    		System.out.println("\nReceived InitWrapper " 
    				+ message.getPayload()); 
    		
    		try {
    			wrapper = mapper.readValue(message.getPayload(), InitWrapper.class);
    			
    			System.out.println("Received initMessage " 
    			+ wrapper.getInitMessages().get(workerId).getVertices().length);
    				
    			InitMessage initMessage = wrapper.getInitMessages().get(workerId);
        		
    			worker.init(initMessage);// here worker is initialized
    			
    			DistMin distMin = worker.getMin();
    			boolean finished = worker.isFinished();
    			WorkerResponse response = new WorkerResponse(distMin, finished);
    			
    			// now worker should send response to broker  	    		
    			simpleSourceBean.publishWorkerResponse(response);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	
    	} else if (type.equals("STEP")) {
    		// in this case the payload is a DistMin object
    		DistMin distMin;
    		System.out.println("\nReceived DistMin " 
    				+ message.getPayload()); 
    		try {
				distMin = mapper.readValue(message.getPayload(), DistMin.class);
			
				if (worker.isFirst()) {
					System.out.println("First step");
					worker.setFirst(false);
				} else {
					worker.updateAdjacency(distMin);		
				}
			
				// find new local MWOE
				DistMin newDistMin = worker.getMin();
				boolean finished = worker.isFinished();
				WorkerResponse response = new WorkerResponse(newDistMin, finished);
			
				// now worker should send response to broker  	    		
    			simpleSourceBean.publishWorkerResponse(response);	
    		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     	}
    	
    	System.out.println("workerId " + workerId);
    }
   
}
    