package com.safetynet.alerts.services;

import java.time.LocalDate;
import java.time.Period;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CalculateAge {

	private static final Logger logger = LogManager.getLogger("ageCalculation");

	public int ageCalculation(LocalDate birthdate) {
		int period = 0;
		try {
			LocalDate birthdatePerson = birthdate;
			period = Period.between(birthdatePerson, LocalDate.now()).getYears();
		} catch (Exception e) {
			logger.error("Cannot calculate the age", e);
		}
			
			return period;
	}
}
