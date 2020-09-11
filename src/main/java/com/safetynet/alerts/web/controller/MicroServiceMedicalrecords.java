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

import com.safetynet.alerts.interfaces.IMedicalrecords;
import com.safetynet.alerts.model.Medicalrecord;

@RestController
public class MicroServiceMedicalrecords {
	@Autowired
	private IMedicalrecords medicalrecords;

	@GetMapping(value = "medicalrecord")
	public List<Medicalrecord> allmedicalrecords() {
		return medicalrecords.returnAllMedicalRecord();
	}

	@PostMapping(value = "/medicalrecord")
	public ResponseEntity<Void> addMedicalrecord(@Valid @RequestBody Medicalrecord medicalrecord) {

		Medicalrecord medicalrecordAdded = medicalrecords.saveMedicalRecord(medicalrecord);
		if (medicalrecordAdded == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{lastName}")
				.buildAndExpand(medicalrecordAdded.getLastName()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping(path = "/medicalrecord")
	public ResponseEntity<Void> updateMedicalrecord(@Valid @RequestBody Medicalrecord medicalrecord) {
		Medicalrecord medicalrecordAdded = medicalrecords.updateMedicalRecord(medicalrecord);
		if (medicalrecordAdded == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{lastName}")
				.buildAndExpand(medicalrecordAdded.getLastName()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/medicalrecord")
	public ResponseEntity<Void> deleteMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
		Medicalrecord medicalrecordAdded = medicalrecords.deleteMedicalRecord(medicalrecord);
		if (medicalrecordAdded == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{lastName}")
				.buildAndExpand(medicalrecordAdded.getLastName()).toUri();

		return ResponseEntity.created(location).build();
	}
}
