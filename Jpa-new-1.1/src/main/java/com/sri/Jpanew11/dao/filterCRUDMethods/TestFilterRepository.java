package com.sri.Jpanew11.dao.filterCRUDMethods;

import com.sri.Jpanew11.model.Person;

public interface TestFilterRepository extends MyBaseRepository<Person, String> {
	Person findByLastname(String lastname);
}
