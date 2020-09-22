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
		for(Person person2 : persons) {
			if(person2.getFirstName().equals(firstName)&&person2.getLastName().contentEquals(lastName)) {
				persons.set(persons.indexOf(person2), person);
				break;
			}
		}
		return person;
	}

	public Person deletePerson(Person person) {
		String firstName = person.getFirstName();
		String lastName = person.getLastName();
		List<Person> persons = listObject.getPersons();
		for(Person person2 : persons) {
			if(person2.getFirstName().equals(firstName)&&person2.getLastName().contentEquals(lastName)) {
				persons.remove(persons.indexOf(person2));
				break;
			}
		}
		return person;
	}
}
