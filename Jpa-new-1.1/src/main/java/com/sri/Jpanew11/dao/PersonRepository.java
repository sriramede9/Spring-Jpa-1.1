package com.sri.Jpanew11.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sri.Jpanew11.model.Person;

public interface PersonRepository extends CrudRepository<Person, String> {


	
}
