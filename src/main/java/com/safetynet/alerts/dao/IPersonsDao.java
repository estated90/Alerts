package com.safetynet.alerts.dao;

import java.util.List;

import com.safetynet.alerts.model.Persons;

public interface IPersonsDao {

	List<Persons> findAll();

	Persons findById(String name, String lastName);

	Persons save(Persons persons);

}
