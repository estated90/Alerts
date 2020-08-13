package com.safetynet.alerts.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.safetynet.alerts.dao.PersonsDao;
import com.safetynet.alerts.model.Persons;

@RestController
public class MicroServicePerson {


	@Autowired
	private PersonsDao personsDao;

	// Récupérer un produit par son Id
	@GetMapping(value = "PersonInfo/firstName/{firstName}/lastName/{lastName}")
	public Persons ShowPersons(@PathVariable String firstName,@PathVariable String lastName) {
		return personsDao.findById(firstName, lastName);
	}

}
