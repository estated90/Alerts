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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

import com.safetynet.alerts.dao.FirestationDaoImpl;
import com.safetynet.alerts.model.Firestation;

@Service
@RestController
public class MicroServiceFirestation {
	
	private final Logger logger = LoggerFactory.getLogger(MicroServiceFirestation.class);
	
	@Autowired
	private FirestationDaoImpl firestationDao;
	/**
	 * 
	 * @return List Firestation
	 */
	@GetMapping(value = "firestation", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Firestation> firestation() {
		logger.info("getting all firestation");
		return firestationDao.returnAllFirestation();
	}
	/**
	 * 
	 * @param firestation with valid parameters
	 * @return ResponseEntity error or success
	 */
	@PostMapping(value = "/firestation", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> addFirestation(@Valid @RequestBody Firestation firestation) {
		logger.info("creating new firestation: {}", firestation);
		Firestation firestationAdded = firestationDao.saveFirestation(firestation);
		if (firestationAdded == null) {
			logger.info("Firestation was not created");
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{station}")
				.buildAndExpand(firestationAdded.getStation()).toUri();
		logger.info("{} was created", firestation);
		return ResponseEntity.created(location).build();
	}
	/**
	 * 
	 * @param firestation with valid parameters
	 * @return ResponseEntity error or success
	 */
	@PutMapping(path = "/firestation")
	public ResponseEntity<String> updateUser(@Valid @RequestBody Firestation firestation) {
		logger.info("updating firestation: {}", firestation);
		Firestation firestationModified = firestationDao.updateFirestation(firestation);
		if (firestationModified == null) {
			logger.info("Firestation with id {} not found", firestation);
			return ResponseEntity.unprocessableEntity().body("Address was unknown");
		}
		logger.info("{} was updated", firestation);
		return ResponseEntity.noContent().build();
	}
	/**
	 * 
	 * @param firestation with valid parameters
	 * @return ResponseEntity error or success
	 */
	@DeleteMapping(path = "/firestation")
	public ResponseEntity<String> deleteUser(@RequestBody Firestation firestation) {
		Firestation firestationDeleted = firestationDao.deleteFirestation(firestation);
		logger.info("deleting firestation : {}", firestation);
		if (firestationDeleted == null) {
			logger.info("Firestation with id {} not found", firestation);
			return ResponseEntity.unprocessableEntity().body("Address and Id were unknown");
		}
		logger.info("{} was deleted", firestation);
		return ResponseEntity.ok(firestation +" deleted");
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
