package com.safetynet.alerts.services;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.*;

class CalculateAgeTest {

	@InjectMocks
	private static CalculateAge calculateAge;
	
	@BeforeAll
	private static void init() {
		calculateAge = new CalculateAge();
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void givenBirthdayDate_whenCalculating_thenReturnAge() {
		//GIVEN
		LocalDate date = LocalDate.now().minusYears(30);
		//When
		int age = calculateAge.ageCalculation(date);
		//Then
		assertEquals(30, age);
	}
}
