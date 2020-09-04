package com.safetynet.alerts.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.dto.PersonPerStationDto;
import com.safetynet.alerts.services.MicroservicesServices;

@RestController
public class UrlsControllers {
	
	private static final Logger logger = LogManager.getLogger("JsonReader");
	@Autowired
	private MicroservicesServices microservicesServices;

	@GetMapping("/firestations")
	public PersonPerStationDto returnFirestationFiltered(@RequestParam("stationNumber") int station) {
		logger.info("GET/firestations?stationNumber={}", station);
		PersonPerStationDto result = microservicesServices.firestationListPerson(station);
		logger.info("GET/firestations?stationNumber={} Result: {}",station, result);
		return result;
	}

}
