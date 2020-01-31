package com.sri.Jpanew11.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sri.Jpanew11.model.Person;

public interface PersonJpaRepository extends JpaRepository<Person, String> {

	Person findByLastname(String lastname);

	Person findByFirstname(String firstname);

	Person findByFirstnameAndLastname(String firstname, String lastname);// filtering based on two params

	void deleteByFirstname(String firstname);

	boolean existsByFirstname(String firstname);

	Person findDistinctPersonByFirstname(String firstname);

	List<Person> findPersonDistinctByFirstname(String firstname);
}
