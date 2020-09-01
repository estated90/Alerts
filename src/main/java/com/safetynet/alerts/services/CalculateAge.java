package com.safetynet.alerts.services;

import java.time.LocalDate;
import java.time.Period;

public class CalculateAge {

	public int ageCalculation(LocalDate birthdate) {
		
		LocalDate birthdatePerson = birthdate;
		Period period = Period.between(birthdatePerson, LocalDate.now());
		return period.getYears();
		
	}
}
