package com.safetynet.alerts.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alerts.dao.PersonsDaoImpl;
import com.safetynet.alerts.model.Persons;

@RestController
public class MicroServicePerson {

	@Autowired
	private PersonsDaoImpl personsDao;

	// Récupérer un produit par son Id
	@GetMapping(value = "person")
	public MappingJacksonValue showPersons() {
		
		List<Persons> persons = personsDao.returnAll();
		
		SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("station", "medications", "allergies", "birthdate");

		FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);

		MappingJacksonValue produitsFiltres = new MappingJacksonValue(persons);

		produitsFiltres.setFilters(listDeNosFiltres);
		return produitsFiltres;

	}

}
