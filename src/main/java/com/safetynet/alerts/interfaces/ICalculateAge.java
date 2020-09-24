package com.safetynet.alerts.interfaces;

import java.time.LocalDate;

public interface ICalculateAge {

	/**
	 * @param birthdate to calculate from
	 * @return age of the persons
	 * @throws NullPointerException if not a date
	 * @throws IllegalArgumentException if date after today's date
	 */
	int ageCalculation(LocalDate birthdate);

}