package com.safetynet.alerts.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.dao.PersonsDaoImpl;
import com.safetynet.alerts.model.Person;

@RestController
public class MicroServicePerson {

	@Autowired
	private PersonsDaoImpl personsDao;

	// Récupérer un produit par son Id
	@GetMapping(value = "person")
	public List<Person> showPersons() {
		return personsDao.returnAll();

	}

}
