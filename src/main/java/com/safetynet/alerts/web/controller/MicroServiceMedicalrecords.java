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

import com.safetynet.alerts.dao.MedicalRecordDaoImpl;
import com.safetynet.alerts.model.Medicalrecord;

@RestController
public class MicroServiceMedicalrecords {
	
	private final Logger logger = LoggerFactory.getLogger(MicroServiceFirestation.class);
	
	@Autowired
	private MedicalRecordDaoImpl medicalrecords;
	
	@GetMapping(value = "medicalrecord")
	public List<Medicalrecord> allmedicalrecords() {
		logger.info("getting all medicalrecord");
		return medicalrecords.returnAllMedicalRecord();
	}

	@PostMapping(value = "/medicalrecord")
	public ResponseEntity<String> addMedicalrecord(@Valid @RequestBody Medicalrecord medicalrecord) {
		logger.info("adding medicalrecord for: {}", medicalrecord);
		Medicalrecord medicalrecordAdded = medicalrecords.saveMedicalRecord(medicalrecord);
		if (medicalrecordAdded == null) {
			logger.info("Medicalrecord was not created");
			return ResponseEntity.unprocessableEntity().body("The medicalrecord was not added");
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{lastName}")
				.buildAndExpand(medicalrecordAdded.getLastName()).toUri();
		logger.info("{} was created", medicalrecord);
		return ResponseEntity.created(location).build();
	}

	@PutMapping(path = "/medicalrecord")
	public ResponseEntity<String> updateMedicalrecord(@Valid @RequestBody Medicalrecord medicalrecord) {
		logger.info("updating firestation: {}", medicalrecord);
		Medicalrecord medicalrecordAdded = medicalrecords.updateMedicalRecord(medicalrecord);
		if (medicalrecordAdded == null) {
			logger.info("Medicalrecord for {} {} was not found", medicalrecord.getLastName(), medicalrecord.getFirstName());
			return ResponseEntity.unprocessableEntity().body(("The medicalrecord was not found"));
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{lastName}")
				.buildAndExpand(medicalrecordAdded.getLastName()).toUri();
		logger.info("{} was updated", medicalrecord);
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/medicalrecord")
	public ResponseEntity<String> deleteMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
		logger.info("deleting medicalrecord : {}", medicalrecord);
		Medicalrecord medicalrecordAdded = medicalrecords.deleteMedicalRecord(medicalrecord);
		if (medicalrecordAdded == null) {
			logger.info("Medicalrecord for {} {} was not found", medicalrecord.getLastName(), medicalrecord.getFirstName());
			return ResponseEntity.unprocessableEntity().body("The medicalrecord was not found");
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{lastName}")
				.buildAndExpand(medicalrecordAdded.getLastName()).toUri();
		logger.info("{} was deleted", medicalrecord);
		return ResponseEntity.created(location).build();
	}
}
