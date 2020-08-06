package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface IPersonsDao {

	List<Person> findAll();

	Person findById(String name, String lastName);

	Person save(Person persons);

}
