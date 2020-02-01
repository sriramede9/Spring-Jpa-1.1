package com.sri.Jpanew11.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sri.Jpanew11.dao.PersonJpaRepository;
import com.sri.Jpanew11.dao.PersonQueryByExampleExecuter;
import com.sri.Jpanew11.model.Person;

@RestController
@RequestMapping("/Persons/Example")
@Transactional
public class PersonController2 {

//	@Autowired
//	PersonQueryByExampleExecuter personQueryByExampleExecuter;
	@Autowired
	PersonJpaRepository personJpaRepository;

	@GetMapping("/Persons")
	public Example<Person> getPerson() {

		Person p = new Person();
		p.setLastname("King");

		Example<Person> exp = Example.of(p);
		return exp;
	}

	// let's define example matcher which is part of Example object
	@GetMapping("/Persons/ExampleMatcher")
	public Example<Person> getPersonWithMatcher() {

		Person p = new Person();
		p.setLastname("King");

		ExampleMatcher em = ExampleMatcher.matching()// all the results mathing this
				.withIgnorePaths("firstname") // ignore the firstname property
				.withIgnoreCase() // ignore case
				.withIncludeNullValues(). // okay with null values
				withStringMatcher(StringMatcher.DEFAULT); // enum type which can accept regex

		Example<Person> exp = Example.of(p, em);
		return exp;
	}

	@PostMapping("/Persons/ExampleMatcher/")
	public List<Person> findPeople(@RequestBody Person person) {

//		Person p = new Person();
//		p.setLastname("King");
//		
//		Person p2=new Person();
//		p.setLastname("king");

		ExampleMatcher em = ExampleMatcher.matching()// all the results mathing this
				.withIgnorePaths("firstname") // ignore the firstname property
				.withIgnoreCase() // ignore case
				.withIncludeNullValues(). // okay with null values
				withStringMatcher(StringMatcher.DEFAULT); // enum type which can accept regex

		// Example<Person> exp = Example.of(p,em);

		return personJpaRepository.findAll(Example.of(person, em));
	}
}
