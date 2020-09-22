package com.safetynet.alerts.web.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.alerts.dao.PersonsDaoImpl;
import com.safetynet.alerts.model.Person;


@RestController
public class MicroServicePerson {
	
	private final Logger logger = LoggerFactory.getLogger(MicroServiceFirestation.class);
	
	@Autowired
	private PersonsDaoImpl personsDao;
	
	private static final String PATH = "/{lastName}";

	@GetMapping(value = "person")
	public List<Person> showPersons() {
		logger.info("getting all persons");
		return personsDao.returnAllPerson();
	}

	@PostMapping(value = "/person")
	public ResponseEntity<String> addPerson(@Valid @RequestBody Person person) {
		logger.info("adding person for: {}", person);
		Person personAdded = personsDao.savePerson(person);
		if (personAdded == null) {
			logger.info("Person was not created");
			return ResponseEntity.unprocessableEntity().body("The person was not added");
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(PATH)
				.buildAndExpand(personAdded.getLastName()).toUri();
		logger.info("{} was created", person);
		return ResponseEntity.created(location).build();
	}

	@PutMapping(path = "/person")
	public ResponseEntity<String> updateUser(@Valid @RequestBody Person person) {
		logger.info("updating person for: {}", person);
		Person personModified = personsDao.updatePerson(person);
		if (personModified == null) {
			logger.info("Person was not updated");
			return ResponseEntity.unprocessableEntity().body("The person was not updated");
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(PATH)
				.buildAndExpand(personModified.getLastName()).toUri();
		logger.info("{} was updated", person);
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/person")
	public ResponseEntity<String> deleteUser(@RequestBody Person person) {
		logger.info("deleting person : {}", person);
		Person personDeleted = personsDao.deletePerson(person);
		if (personDeleted == null) {
			logger.info("Person {} {} was not found", person.getLastName(), person.getFirstName());
			return ResponseEntity.unprocessableEntity().body("The person was not found");
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(PATH)
				.buildAndExpand(personDeleted.getLastName()).toUri();
		logger.info("{} was deleted", person);
		return ResponseEntity.created(location).build();
	}
}
