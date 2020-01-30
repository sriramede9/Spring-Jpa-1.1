package com.sri.Jpanew11.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sri.Jpanew11.dao.PagingAndSortingPerson;
import com.sri.Jpanew11.dao.PersonRepository;
import com.sri.Jpanew11.exception.NoSuchPersonException;
import com.sri.Jpanew11.model.Person;

@RestController
@Transactional
public class PersonController {

	@Autowired
	PagingAndSortingPerson pagingAndSortingPerson;

	@Autowired
	PersonRepository personRepository;

	@GetMapping("/Persons")
	public Iterable<Person> getPerson() {
		Iterable<Person> findAll = personRepository.findAll();
		return findAll;
	}

	@PostMapping("/Persons")
	public ResponseEntity<Object> addPerson(@RequestBody Person p) {

		personRepository.save(p);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/Persons/{id}")
	public Person getbyId(@PathVariable String id) {

		if (personRepository.existsById(id)) {

			Optional<Person> findById = personRepository.findById(id);
			return findById.get();
		} else
			throw new NoSuchPersonException("No person with such Id -->" + id);

	}

	@GetMapping("/Persons/Sortbylastname")
	public Iterable<Person> getSortedbyLastName() {

		// Sort sort;
		Iterable<Person> findAll = pagingAndSortingPerson.findAll(Sort.by(Direction.DESC, "lastname"));

		return findAll;

	}
	@GetMapping("/Persons/Sortbyfirstname")
	public Iterable<Person> getSortedbyFirstName() {
		
		// Sort sort;
		Iterable<Person> findAll = pagingAndSortingPerson.findAll(Sort.by(Direction.DESC, "firstname"));
		
		return findAll;
		
	}
}
