package com.sri.Jpanew11.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sri.Jpanew11.model.Person;

public interface PersonJpaRepository extends JpaRepository<Person, String> {

	Person findByLastname(String lastname);
	Person findByFirstname(String firstname);
	Person findByFirstnameAndLastname(String firstname, String lastname);// filtering based on two params
}
  