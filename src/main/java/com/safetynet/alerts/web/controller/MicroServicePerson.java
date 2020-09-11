package com.safetynet.alerts.web.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.alerts.interfaces.IPersonsDao;
import com.safetynet.alerts.model.Person;

@RestController
public class MicroServicePerson {

	@Autowired
	private IPersonsDao personsDao;

	@GetMapping(value = "person")
	public List<Person> showPersons() {
		return personsDao.returnAllPerson();
	}

	@PostMapping(value = "/person")
	public ResponseEntity<Void> addPerson(@Valid @RequestBody Person person) {

		Person personAdded = personsDao.savePerson(person);
		if (personAdded == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{lastName}")
				.buildAndExpand(personAdded.getLastName()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping(path = "/person")
	public ResponseEntity<Void> updateUser(@Valid @RequestBody Person person) {
		Person personModified = personsDao.updatePerson(person);
		if (personModified == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{lastName}")
				.buildAndExpand(personModified.getLastName()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/person")
	public ResponseEntity<Void> deleteUser(@RequestBody Person person) {
		Person personModified = personsDao.deletePerson(person);
		if (personModified == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{lastName}")
				.buildAndExpand(personModified.getLastName()).toUri();

		return ResponseEntity.created(location).build();
	}
}
