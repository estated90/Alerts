package com.safetynet.alerts.services;

import java.time.LocalDate;
import java.time.Period;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.interfaces.ICalculateAge;

/**
 * @author nicol
 *
 */
@Service
public class CalculateAge implements ICalculateAge {

	private static final Logger logger = LogManager.getLogger("ageCalculation");

	/**
	 * @param birthdate to calculate from
	 * @return age of the persons
	 * @throws NullPointerException if not a date
	 * @throws IllegalArgumentException if date after today's date
	 */
	@Override
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
