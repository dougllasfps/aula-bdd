package com.example.demo.api.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.model.entity.Person;
import com.example.demo.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PersonController.class)
public class PersonResourceTest {

	@Autowired
	MockMvc http;
	
	@MockBean
	PersonService service;
	
	@Test
	public void shouldReturnOkWhenSavingAPerson() throws Exception {
		
		Person person = generatePerson();
		
		BDDMockito.given(service.save(Mockito.any(Person.class))).willReturn(person);
		
		String json = new ObjectMapper().writeValueAsString(
				Person
					.builder()
					.id(1)
					.age(10)
					.name("name").build()
				);
		
		ResultActions request = http.perform(
				post("/api/person")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		request
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(person.getId()))
			.andExpect(jsonPath("$.name").value(person.getName()))
			.andExpect(jsonPath("$.age").value(person.getAge()))
			;
				
	}

	private Person generatePerson() {
		return Person.builder().id(1).name("name").age(1).build();
	}
}
