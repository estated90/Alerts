package com.safetynet.alerts.web.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	/**
	 * 
	 * @return return All Person
	 */
	@GetMapping(value = "person")
	public List<Person> showPersons() {
		logger.info("getting all persons");
		return personsDao.returnAllPerson();
	}
	/**
	 * 
	 * @param person with valid parameters
	 * @return ResponseEntity error or success
	 */
	@PostMapping(value = "/person")
	public ResponseEntity<String> addPerson(@Valid @RequestBody Person person) {
		logger.info("adding person for: {}", person);
		Person personAdded = personsDao.savePerson(person);
		if (personAdded == null) {
			logger.info("Person was not created");
			return ResponseEntity.badRequest().body("The person was not added");
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(PATH)
				.buildAndExpand(personAdded.getLastName()).toUri();
		logger.info("{} was created", person);
		return ResponseEntity.created(location).build();
	}
	/**
	 * 
	 * @param person with valid parameters
	 * @return ResponseEntity error or success
	 */
	@PutMapping(path = "/person")
	public ResponseEntity<String> updateUser(@Valid @RequestBody Person person) {
		logger.info("updating person for: {}", person);
		Person personModified = personsDao.updatePerson(person);
		if (personModified == null) {
			logger.info("Person was not updated");
			return ResponseEntity.badRequest().body("The person was not updated");
		}
		logger.info("{} was updated", person);
		return ResponseEntity.noContent().build();
	}
	/**
	 * 
	 * @param person with valid parameters
	 * @return ResponseEntity error or success
	 */
	@DeleteMapping(path = "/person")
	public ResponseEntity<String> deleteUser(@RequestBody Person person) {
		logger.info("deleting person : {}", person);
		Person personDeleted = personsDao.deletePerson(person);
		if (personDeleted == null) {
			logger.info("Person {} {} was not found", person.getLastName(), person.getFirstName());
			return ResponseEntity.badRequest().body("The person was not found");
		}
		logger.info("{} was deleted", person);
		return ResponseEntity.ok(person +" deleted");
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
