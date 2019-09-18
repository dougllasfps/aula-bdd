package com.example.demo.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entity.Person;
import com.example.demo.service.PersonService;

@RestController
@RequestMapping("/api/person")
public class PersonController {

	private final PersonService service;
	
	public PersonController(PersonService service) {
		super();
		this.service = service;
	}

	@PostMapping
	public ResponseEntity save(@RequestBody Person person) {
		Person result = service.save(person);
		return ResponseEntity.ok(result);
	}
}
