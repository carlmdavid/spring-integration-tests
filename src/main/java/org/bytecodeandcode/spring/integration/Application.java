package org.bytecodeandcode.spring.integration;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableJms
public class Application {
	
	public static final String PERSON_QUEUE = "person.queue";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
//	@Autowired
//	private ActiveMQProperties activeMqProperties;
	
	@Bean
	public Queue queue() {
		return new ActiveMQQueue(PERSON_QUEUE);
	}
	
	/*@Bean
	public JmsTemplate jmsTemplate(ActiveMQConnectionFactory connectionFactory, Queue queue) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setDefaultDestination(queue);
//		jmsTemplate.setReceiveTimeout(20000);
//		jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
		
		return jmsTemplate;
	}*/
	
	/*@Bean
	public ConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory(activeMqProperties.getBrokerUrl());
	}*/
}
