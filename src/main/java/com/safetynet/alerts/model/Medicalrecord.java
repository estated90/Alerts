
package com.safetynet.alerts.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.safetynet.alerts.dto.PersonDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "firstName", "lastName", "birthdate", "medications", "allergies", "person" })
public class Medicalrecord {

	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("birthdate")
	private LocalDate birthdate;
	@JsonProperty("medications")
	private List<String> medications = new ArrayList<>();
	@JsonProperty("allergies")
	private List<String> allergies = new ArrayList<>();
	@JsonProperty("person")
	private PersonDto person;

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
			List<String> allergies, PersonDto person) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
		this.person = person;
	}

	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("birthdate")
	public LocalDate getBirthdate() {
		return birthdate;
	}

	@JsonProperty("birthdate")
	public void setBirthdate(LocalDate date) {
		this.birthdate = date;
	}

	@JsonProperty("medications")
	public List<String> getMedications() {
		return medications;
	}

	@JsonProperty("medications")
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	@JsonProperty("allergies")
	public List<String> getAllergies() {
		return allergies;
	}

	@JsonProperty("allergies")
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	/**
	 * @return the person
	 */
	@JsonProperty("person")
	public PersonDto getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	@JsonProperty("person")
	public void setPerson(PersonDto person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("firstName", firstName).append("lastName", lastName)
				.append("birthdate", birthdate).append("medications", medications).append("allergies", allergies).append("person", person)
				.toString();
	}

}
