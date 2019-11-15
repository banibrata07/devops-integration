package com.scm.service;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import  javax.jms.ObjectMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.scm.model.github.commit.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class JmsReceiver{
    
    
    @JmsListener(destination = "inbound.topic", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessageFromTopic(final Message jsonMessage) throws JMSException {
    	String messageData = null;
    	//System.out.println("Received message " + jsonMessage);
    	String response = null;
    	if(jsonMessage instanceof ObjectMessage) {
    		ObjectMessage objectMessage = (ObjectMessage) jsonMessage;
    		 //System.out.println(objectMessage);
    		 List<MyPojo> pojoList = (List<MyPojo>) objectMessage.getObject();
    	    System.out.println("----------------------------Received Message----------------------------");
    	    for(MyPojo pojo : pojoList){
    	         //System.out.println("pojo--"+pojo);
    	    }
    	}
    	//return response;
    }
}