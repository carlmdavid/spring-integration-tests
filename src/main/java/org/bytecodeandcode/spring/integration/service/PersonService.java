package org.bytecodeandcode.spring.integration.service;

import javax.jms.Queue;

import org.bytecodeandcode.spring.batch.persistence.domain.Person;
import org.bytecodeandcode.spring.integration.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

	private static Logger logger = LoggerFactory.getLogger(PersonService.class);

	private JmsMessagingTemplate jmsMessagingTemplate;
	
	private Queue queue;

	private Person person;

	@Autowired
	public PersonService(JmsMessagingTemplate jmsMessagingTemplate, Queue queue) {
		this.jmsMessagingTemplate = jmsMessagingTemplate;
		this.queue = queue;
	}
	
	public Person getPersonInQueue() {
		logger.info("Getting next person in queue.");
		return (Person) jmsMessagingTemplate.receiveAndConvert(queue, Person.class);
	}
	

	/*@JmsListener(destination = Application.PERSON_QUEUE)
	public void getPersonFromQueue(Person person) {
		this.person = person;
	}
	
	public Person getCurrentPerson() {
		return this.person;
	}*/
	
	public void addPersonToQueue(Person p) {
		logger.info("Adding Person {} in queue.", p);
		jmsMessagingTemplate.convertAndSend(queue, p);
	}
	
}
