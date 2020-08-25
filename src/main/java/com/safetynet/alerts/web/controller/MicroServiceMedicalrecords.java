package com.safetynet.alerts.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.interfaces.IMedicalrecords;
import com.safetynet.alerts.model.Medicalrecord;

@RestController
public class MicroServiceMedicalrecords {
	@Autowired
	private IMedicalrecords medicalrecords;

	@GetMapping(value = "medicalrecord")
	public List<Medicalrecord> medicalrecords() {
		return medicalrecords.returnAll();

	}
}
