package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Person;
import com.example.demo.model.repository.PersonRepository;
import com.example.demo.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{

	private PersonRepository repository;

	public PersonServiceImpl( PersonRepository repository ) {
		super();
		this.repository = repository;
	}

	@Override
	public Person save(Person person) {
		return repository.save(person);
	}
	
	
}
