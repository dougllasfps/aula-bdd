package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.model.entity.Person;
import com.example.demo.model.repository.PersonRepository;
import com.example.demo.service.impl.PersonServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

	PersonService service;
	
	@Mock
	PersonRepository repository;
	
	@Mock
	Person person;
	
	@Before
	public void setUp() {
		service = new PersonServiceImpl(repository);
	}
	
	@Test
	public void shouldSaveAPerson() {
		Person saved = Person.builder().name("name").id(1).age(1).build();
		Mockito.when(repository.save(person)).thenReturn(saved);
		
		Person result = service.save(person);
		
		assertThat(result.getId()).isEqualTo(saved.getId());
		assertThat(result.getName()).isEqualTo(saved.getName());
		assertThat(result.getAge()).isEqualTo(saved.getAge());
		
		Mockito.verify(repository, Mockito.times(1)).save(person);
	}
}
