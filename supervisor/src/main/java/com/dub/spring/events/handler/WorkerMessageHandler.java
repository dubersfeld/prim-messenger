package com.dub.spring.events.handler;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.dub.spring.controller.CountHolder;
import com.dub.spring.controller.DistMinHolder;
import com.dub.spring.events.model.WorkerMessageModel;
import com.dub.spring.minimumSpanningTree.DistMin;
import com.dub.spring.minimumSpanningTree.WorkerResponse;
import com.dub.spring.minimumSpanningTree.WorkerResponse.Code;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * All response messages from workers are handled by this class
 * A synchronization is required because 
 * communication on message broker is intrinsically asynchronous
 * */
@EnableBinding(Sink.class)
public class WorkerMessageHandler {
	
	@Value("${worker-number}")
	int workerNumber;
	
	@Autowired
	private DistMinHolder distMinHolder; 
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	private CountHolder countHolder;// used as a monitor  

    //private static final Logger logger = LoggerFactory.getLogger(WorkerMessageHandler.class);

    @StreamListener(Sink.INPUT)
    public void loggerSink(WorkerMessageModel bookChange) {
       
        System.out.println("Received a message from worker " 
      							+ bookChange.getPayload());
        
        synchronized(countHolder) {
        	
        	// deserialize distMin
        	try {
        		WorkerResponse response = mapper.readValue(bookChange.getPayload(), WorkerResponse.class);
				
        		DistMin distMin = response.getDistMin();
        		Code status = response.getStatus();
				distMinHolder.getDistMin()[countHolder.getCount()] = distMin;
				distMinHolder.getFinished()[countHolder.getCount()] = status.equals(Code.FINISHED) ? true : false;
				countHolder.setCount(countHolder.getCount()+1);
				if (countHolder.getCount() == workerNumber) {
					// all workers have sent their response message
					countHolder.notifyAll();
				}
				
        	} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
}
    