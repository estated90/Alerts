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

import com.safetynet.alerts.dao.MedicalRecordDaoImpl;
import com.safetynet.alerts.model.Medicalrecords;

@RestController
public class MicroServiceMedicalrecords {
	
	private final Logger logger = LoggerFactory.getLogger(MicroServiceFirestation.class);
	
	@Autowired
	private MedicalRecordDaoImpl medicalrecords;
	/**
	 * 
	 * @return list of medicalrecords
	 */
	@GetMapping(value = "medicalrecord")
	public List<Medicalrecords> allmedicalrecords() {
		logger.info("getting all medicalrecord");
		return medicalrecords.returnAllMedicalRecord();
	}
	/**
	 * 
	 * @param medicalrecord with valid parameters
	 * @return ResponseEntity error or success
	 */
	@PostMapping(value = "/medicalrecord")
	public ResponseEntity<String> addMedicalrecord(@Valid @RequestBody Medicalrecords medicalrecord) {
		logger.info("adding medicalrecord for: {}", medicalrecord);
		Medicalrecords medicalrecordAdded = medicalrecords.saveMedicalRecord(medicalrecord);
		if (medicalrecordAdded == null) {
			logger.info("Medicalrecord was not created");
			return ResponseEntity.unprocessableEntity().body("The medicalrecord was not added");
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{lastName}")
				.buildAndExpand(medicalrecordAdded.getLastName()).toUri();
		logger.info("{} was created", medicalrecord);
		return ResponseEntity.created(location).build();
	}
	/**
	 * 
	 * @param medicalrecord with valid parameters
	 * @return ResponseEntity error or success
	 */
	@PutMapping(path = "/medicalrecord")
	public ResponseEntity<String> updateMedicalrecord(@Valid @RequestBody Medicalrecords medicalrecord) {
		logger.info("updating medicalrecord: {}", medicalrecord);
		Medicalrecords medicalrecordAdded = medicalrecords.updateMedicalRecord(medicalrecord);
		if (medicalrecordAdded == null) {
			logger.info("Medicalrecord for {} {} was not found", medicalrecord.getLastName(), medicalrecord.getFirstName());
			return ResponseEntity.unprocessableEntity().body(("The medicalrecord was not found"));
		}
		logger.info("{} was updated", medicalrecord);
		return ResponseEntity.noContent().build();
	}
	/**
	 * 
	 * @param medicalrecord with valid parameters
	 * @return ResponseEntity error or success
	 */
	@DeleteMapping(path = "/medicalrecord")
	public ResponseEntity<String> deleteMedicalrecord(@RequestBody Medicalrecords medicalrecord) {
		logger.info("deleting medicalrecord : {}", medicalrecord);
		Medicalrecords medicalrecordAdded = medicalrecords.deleteMedicalRecord(medicalrecord);
		if (medicalrecordAdded == null) {
			logger.info("Medicalrecord for {} {} was not found", medicalrecord.getLastName(), medicalrecord.getFirstName());
			return ResponseEntity.unprocessableEntity().body("The medicalrecord was not found");
		}
		logger.info("{} was deleted", medicalrecord);
		return ResponseEntity.ok(medicalrecord +" deleted");
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
