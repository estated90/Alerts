package com.safetynet.alerts.services;

import java.time.LocalDate;
import java.time.Period;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author nicol
 *
 */
@Service
public class CalculateAge {

	private static final Logger logger = LogManager.getLogger("ageCalculation");

	/**
	 * @param birthdate
	 * @return age
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public int ageCalculation(LocalDate birthdate) {
		LocalDate birthdatePerson = birthdate;
		int age = Period.between(birthdatePerson, LocalDate.now()).getYears();
		if (birthdate.isAfter(LocalDate.now())) {
			logger.info("Person birthday after today's date : {}", birthdate);
			throw new IllegalArgumentException("Person's birthday date after today's date");
		} else if(age == 0) {
			logger.info("infant with age in month");
			age++;
		}
		logger.info("Age calculated is {}", age);
		return age;
	}
}
