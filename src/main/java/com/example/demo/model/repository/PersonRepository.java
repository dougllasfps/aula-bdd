package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Person;

public interface PersonRepository  extends JpaRepository<Person, Integer>{

}
