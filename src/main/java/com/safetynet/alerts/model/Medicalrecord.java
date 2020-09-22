
package com.safetynet.alerts.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Medicalrecord {

	@NotNull(message = "Firstname cannot be null")
	private String firstName;
	@NotNull(message = "Lastname cannot be null")
	private String lastName;
	@NotNull(message = "birthdate cannot be null")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthdate;
	private List<String> medications = new ArrayList<>();
	private List<String> allergies = new ArrayList<>();
	@JsonIgnore
	private Person person;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Medicalrecord() {
	}

	/**
	 * 
	 * @param allergies
	 * @param firstName
	 * @param lastName
	 * @param birthdate
	 * @param medications
	 */
	public Medicalrecord(String firstName, String lastName, LocalDate birthdate, List<String> medications,
			List<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate date) {
		this.birthdate = date;
	}

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("firstName", firstName).append("lastName", lastName)
				.append("birthdate", birthdate).append("medications", medications).append("allergies", allergies).append("person", person)
				.toString();
	}

}
