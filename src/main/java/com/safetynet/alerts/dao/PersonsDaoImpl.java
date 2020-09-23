package com.safetynet.alerts.dao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.ListObjects;
import com.safetynet.alerts.model.Person;

@Service
public class PersonsDaoImpl {

	@Autowired
	private ListObjects listObject;
	

	public List<Person> returnAllPerson() {
		return listObject.getPersons();
	}

	public Person savePerson(Person person) {
		listObject.getPersons().add(person);
		return person;
	}

	public Person updatePerson(@Valid Person person) {
		String firstName = person.getFirstName();
		String lastName = person.getLastName();
		List<Person> persons = listObject.getPersons();
		Person personFiltered = persons.stream()
				.filter(str -> str.getFirstName().equals(firstName) && str.getLastName().equals(lastName)).findAny()
				.orElse(null);
		if (personFiltered == null) {
			return null;
		} else {
			persons.set(persons.indexOf(personFiltered), person);
			return personFiltered;
		}
	}

	public Person deletePerson(Person person) {
		String firstName = person.getFirstName();
		String lastName = person.getLastName();
		List<Person> persons = listObject.getPersons();
		Person personFiltered = persons.stream()
				.filter(str -> str.getFirstName().equals(firstName) && str.getLastName().equals(lastName)).findAny()
				.orElse(null);
		if (personFiltered == null) {
			return null;
		} else {
			persons.remove(persons.indexOf(personFiltered));
			return personFiltered;
		}
	}
}
