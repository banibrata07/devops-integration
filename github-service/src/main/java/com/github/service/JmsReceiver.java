package com.github.service;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsReceiver{
    
    
    @JmsListener(destination = "inbound.topic", containerFactory = "connectionFactory")
    public void receiveMessageFromTopic(final Message jsonMessage) throws JMSException {
    	String messageData = null;
    	System.out.println("Received message " + jsonMessage);
    	String response = null;
    	if(jsonMessage instanceof TextMessage) {
    		TextMessage textMessage = (TextMessage)jsonMessage;
    		messageData = textMessage.getText();
    	    System.out.println(messageData);
    	}
    	//return response;
    }
}