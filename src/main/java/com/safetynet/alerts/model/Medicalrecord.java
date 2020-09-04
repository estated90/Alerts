
package com.safetynet.alerts.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.googlecode.jmapper.annotations.JGlobalMap;
import com.safetynet.alerts.dto.PersonDto;

public class Medicalrecord {

	private String firstName;
	private String lastName;
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
			List<String> allergies, Person person) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
		this.person = person;
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
