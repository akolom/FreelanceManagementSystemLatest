package com.ea.neon.sender;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
/**
 * @author Sabeen
 * */

@Component("messageSender")
public class ApplySender implements MessageSender{

	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate;

	
	
	/* 
	 * method to send call sender to send message to broker
	 */
	public void sendMessage(final Object message) {
		
		System.out.println("Message sending to Broker.....");
		this.jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage((Serializable) message);
			}
		});
	}
	
	

}
