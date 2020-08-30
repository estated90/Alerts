
package com.safetynet.alerts.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.googlecode.jmapper.annotations.JGlobalMap;
import com.safetynet.alerts.model.Person;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "firstName", "lastName", "birthdate", "medications", "allergies" })
@JGlobalMap
public class MedicalrecordDto {

	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("birthdate")
	private Date birthdate;
	@JsonProperty("medications")
	private List<String> medications = new ArrayList<>();;
	@JsonProperty("allergies")
	private List<String> allergies = new ArrayList<>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public MedicalrecordDto() {
	}

	/**
	 * 
	 * @param allergies
	 * @param firstName
	 * @param lastName
	 * @param birthdate
	 * @param medications
	 */
	public MedicalrecordDto(String firstName, String lastName, Date birthdate, List<String> medications,
			List<String> allergies, Person person) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
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
	public Date getBirthdate() {
		return birthdate;
	}

	@JsonProperty("birthdate")
	public void setBirthdate(Date date) {
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

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("firstName", firstName).append("lastName", lastName)
				.append("birthdate", birthdate).append("medications", medications).append("allergies", allergies)
				.toString();
	}

}
