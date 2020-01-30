package com.sri.Jpanew11.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sri.Jpanew11.model.Person;

public interface PagingAndSortingPerson extends PagingAndSortingRepository<Person, String> {

}
