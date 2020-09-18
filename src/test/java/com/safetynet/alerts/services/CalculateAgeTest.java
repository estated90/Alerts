package com.safetynet.alerts.services;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
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
	@Tag("ValidTest")
	void givenBirthdayDate_whenCalculating_thenReturnAge() {
		// GIVEN
		int ageGoal = 30;
		LocalDate date = LocalDate.now().minusYears(ageGoal);
		// When
		int ageCalculated = calculateAge.ageCalculation(date);
		// Then
		assertEquals(ageGoal, ageCalculated);
	}

	@Test
	@Tag("ValidTest")
	public void givenAOneYoPersons_whenAgeCalculation_thenReturnCorrectAge() {
		// GIVEN
		int ageGoal = 1;
		LocalDate date = LocalDate.now().minusYears(ageGoal);
		// When
		int ageCalculated = calculateAge.ageCalculation(date);
		// Then
		assertEquals(ageGoal, ageCalculated);
	}

	@Test
	@Tag("ValidTest")
	public void givenASixMonthPerson_whenAgeCalculation_thenReturnOneYear() {
		// GIVEN
		int ageGoal = 1;
		LocalDate date = LocalDate.now().minusMonths(6);
		// When
		int ageCalculated = calculateAge.ageCalculation(date);
		// Then
		assertEquals(ageGoal, ageCalculated);
	}

	@Test
	@Tag("InvalidTest")
	public void givenFutureBirthday_whenAgeCalculation_thenIllegalArgumentException() {
		// GIVEN
		LocalDate date = LocalDate.now().plusYears(6);
		// When

		// Then
		assertThatIllegalArgumentException().isThrownBy(() -> {
			calculateAge.ageCalculation(date);
		});
	}
	
	@Test
	@Tag("InvalidTest")
	public void givenFutureOneDayBirthday_whenAgeCalculation_thenIllegalArgumentException() {
		// GIVEN
		LocalDate date = LocalDate.now().plusDays(1);
		// When

		// Then
		assertThatIllegalArgumentException().isThrownBy(() -> {
			calculateAge.ageCalculation(date);
		});
	}
	
	@Test
	@Tag("InvalidTest")
	public void givenNullBirthday_whenAgeCalculation_thenIllegalArgumentException() {
		// GIVEN
		LocalDate date = null;
		// When

		// Then
		assertThatNullPointerException().isThrownBy(() -> {
			calculateAge.ageCalculation(date);
		});
	}
}
