package com.sri.Jpanew11.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sri.Jpanew11.dao.PagingAndSortingPerson;
import com.sri.Jpanew11.dao.PersonJpaRepository;
import com.sri.Jpanew11.dao.PersonRepository;
import com.sri.Jpanew11.dao.filterCRUDMethods.TestFilterRepository;
import com.sri.Jpanew11.exception.NoSuchPersonException;
import com.sri.Jpanew11.model.Person;

@RestController
@Transactional
public class PersonController {
	
	@Autowired
	TestFilterRepository testFilterRepository;
	
	@Autowired
	EntityManager entityManager;

	@Autowired
	PersonJpaRepository personJpaRepository;

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
		// Iterable<Person> findAll =
		// pagingAndSortingPerson.findAll(Sort.by(Direction.DESC, "firstname"));
		String orders = "firstname";
		Iterable<Person> findAll2 = pagingAndSortingPerson.findAll(Sort.by(orders));

		return findAll2;

	}

	@GetMapping("/Persons/lastname/{lastname}")
	public Person getbyLasttName(@PathVariable String lastname) {

		Person findByLastname = personJpaRepository.findByLastname(lastname);

		return findByLastname;
	}

	@GetMapping("/Persons/firstname/{firstname}")
	public Person getbyFirstName(@PathVariable String firstname) {

		Person findByFirstname = personJpaRepository.findByFirstname(firstname);

		return findByFirstname;
	}

	@GetMapping("/Persons/firstname/{firstname}/lastname/{lastname}") // filtering based on two params
	public Person getbyFirstNameandlastname(@PathVariable String firstname, @PathVariable String lastname) {

		Person findByFirstnameandlastname = personJpaRepository.findByFirstnameAndLastname(firstname, lastname);

		System.out.println(findByFirstnameandlastname);

		return findByFirstnameandlastname;
	}

	@DeleteMapping("/Persons/{id}")
	public ResponseEntity<Person> deleteById(@PathVariable String id) {

		if (personJpaRepository.existsById(id)) {

			Person p = personJpaRepository.findById(id).get();

			personJpaRepository.deleteById(id);

			return ResponseEntity.ok(p);
		}
		throw new NoSuchPersonException("No person with such id ->" + id);

	}

	@DeleteMapping("/Persons/firstname/{firstname}")
	public ResponseEntity<Person> deleteByfirstname(@PathVariable String firstname) {

		if (personJpaRepository.existsByFirstname(firstname)) {

			Person p = personJpaRepository.findByFirstname(firstname);

			personJpaRepository.deleteByFirstname(firstname);

			return ResponseEntity.ok(p);
		}
		throw new NoSuchPersonException("No person with such firstname ->" + firstname);

	}

	@PutMapping("/Persons/")
	public ResponseEntity<Person> updatePerson(@RequestBody Person p) {

		if (personJpaRepository.existsById(p.getId())) {
			Person p1 = personJpaRepository.findById(p.getId()).get();
			p1.setFirstname(p.getFirstname());
			p1.setLastname(p.getLastname());
			
			return ResponseEntity.ok(p);
		} else
			throw new NoSuchPersonException("No such person exists to update");
	}
	
	@GetMapping("/Persons/distinctfirstname/{firstname}") // filtering based on two params
	public List<Person> getbydistinctFirstName(@PathVariable String firstname) {

		List<Person> FindByDistinctFirstname = personJpaRepository.findPersonDistinctByFirstname(firstname);

		System.out.println(FindByDistinctFirstname);

		return FindByDistinctFirstname;
	}

}
