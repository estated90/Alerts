package com.safetynet.alerts.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlsControllers {

	@GetMapping("/firestations")
	public void returnFirestationFiltered(@RequestParam("stationNumber") int station) {
		
	}

}
