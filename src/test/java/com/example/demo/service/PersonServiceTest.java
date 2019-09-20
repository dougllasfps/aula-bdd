package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.entity.Person;
import com.example.demo.model.repository.PersonRepository;
import com.example.demo.service.impl.PersonServiceImpl;

@RunWith(SpringRunner.class)
public class PersonServiceTest {

	@SpyBean
	PersonServiceImpl service;
	
	@MockBean
	PersonRepository repository;
	
	@Mock
	Person person;
	
	@Test
	public void shouldSaveAPerson() {
		Person saved = Person.builder().name("name").id(1).age(1).build();
		Mockito.when(repository.save(person)).thenReturn(saved);
		Mockito.doNothing().when(service).castException();
		
		
		Person result = service.save(person);
		
		assertThat(result.getId()).isEqualTo(saved.getId());
		assertThat(result.getName()).isEqualTo(saved.getName());
		assertThat(result.getAge()).isEqualTo(saved.getAge());
		
		Mockito.verify(repository, Mockito.times(1)).save(person);
	}
	
	@Test
	public void shouldCastErrorOnSavingPerson() {
		
		Throwable exception = Assertions.catchThrowable(() -> service.save(person));
		
		BDDAssertions
			.then(exception)
			.as("RuntimeException é lancada no erro de validacao")
			.isInstanceOf(RuntimeException.class)
			.as("verificando a msg de erro")
			.hasMessage("error");
	}
}
