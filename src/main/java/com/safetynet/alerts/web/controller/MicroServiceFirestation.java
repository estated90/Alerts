package com.safetynet.alerts.web.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping(value = "firestation", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Firestation> firestation() {
		logger.info("getting all firestation");
		return firestationDao.returnAllFirestation();
	}
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
		return ResponseEntity.created(location).build();
	}
	@PutMapping(path = "/firestation")
	public ResponseEntity<String> updateUser(@Valid @RequestBody Firestation firestation) {
		logger.info("updating firestation: {}", firestation);
		Firestation firestationModified = firestationDao.updateFirestation(firestation);
		if (firestationModified == null) {
			logger.info("Firestation with id {} not found", firestation);
			return ResponseEntity.unprocessableEntity().body("Address was unknown");
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{station}")
				.buildAndExpand(firestationModified.getStation()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/firestation")
	public ResponseEntity<String> deleteUser(@RequestBody Firestation firestation) {
		Firestation firestationDeleted = firestationDao.deleteFirestation(firestation);
		logger.info("deleting firestation : {}", firestation);
		if (firestationDeleted == null) {
			logger.info("Firestation with id {} not found", firestation);
			return ResponseEntity.unprocessableEntity().body("Address and Id were unknown");
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{station}")
				.buildAndExpand(firestationDeleted.getStation()).toUri();

		return ResponseEntity.created(location).build();
	}
}
