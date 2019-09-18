package com.example.demo.model.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.entity.Person;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PersonRepositoryTest {

	@Autowired
	PersonRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void shouldSave() {
		repository.save(Person.builder().build());
	}
	
	@Test
	public void shouldfindAll() {
		entityManager.persist(Person.builder().build());
		
		List<Person> all = repository.findAll();
		Assertions.assertThat(all).hasSize(1);
	}
}
