package org.bytecodeandcode.spring.integration.service;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.jms.Queue;

import org.bytecodeandcode.spring.batch.persistence.domain.Person;
import org.bytecodeandcode.spring.integration.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class PersonServiceIT {
	
	@Autowired
	private PersonService personService;

	@Test
	public void testPersonServiceAddAndGet() throws Exception{
		
		Person person = new Person();
		person.setFirstName("Foo");
		person.setLastName("Bar");
		personService.addPersonToQueue(person);
		
		person = personService.getPersonInQueue();
		assertThat(person, is(notNullValue()));
		
		
	}

}
