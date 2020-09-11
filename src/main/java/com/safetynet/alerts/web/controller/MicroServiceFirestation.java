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

import com.safetynet.alerts.interfaces.IFirestastionDao;
import com.safetynet.alerts.model.Firestation;


@RestController
public class MicroServiceFirestation {
	@Autowired
	private IFirestastionDao firestationDao;

	@GetMapping(value = "firestation")
	public List<Firestation> firestation() {
		return firestationDao.returnAllFirestation();
	}
	@PostMapping(value = "/firestation")
	public ResponseEntity<Void> addFirestation(@Valid @RequestBody Firestation firestation) {

		Firestation firestationAdded = firestationDao.saveFirestation(firestation);
		if (firestationAdded == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{lastName}")
				.buildAndExpand(firestationAdded.getStation()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping(path = "/firestation")
	public ResponseEntity<Void> updateUser(@Valid @RequestBody Firestation firestation) {
		Firestation firestationModified = firestationDao.updateFirestation(firestation);
		if (firestationModified == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{lastName}")
				.buildAndExpand(firestationModified.getStation()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/firestation")
	public ResponseEntity<Void> deleteUser(@RequestBody Firestation firestation) {
		Firestation firestationDeleted = firestationDao.deleteFirestation(firestation);
		if (firestationDeleted == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{lastName}")
				.buildAndExpand(firestationDeleted.getStation()).toUri();

		return ResponseEntity.created(location).build();
	}
}
