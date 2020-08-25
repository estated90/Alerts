package com.safetynet.alerts.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.interfaces.IFirestastionDao;
import com.safetynet.alerts.model.Firestation;


@RestController
public class MicroServiceFirestqtion {
	@Autowired
	private IFirestastionDao firestationDao;

	@GetMapping(value = "firestation")
	public List<Firestation> firestation() {
		return firestationDao.returnAll();

	}
}
