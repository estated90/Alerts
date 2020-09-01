
package com.safetynet.alerts.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.safetynet.alerts.dto.FirestationDto;
import com.safetynet.alerts.dto.MedicalrecordDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "firstName", "lastName", "address", "city", "zip", "phone", "email", "firestation", "medicalrecords"})
public class Person {

	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("address")
	private String address;
	@JsonProperty("city")
	private String city;
	@JsonProperty("zip")
	private String zip;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("email")
	private String email;
	@JsonProperty("firestation")
	private FirestationDto firestation;
	@JsonProperty("medicalrecords")
	private MedicalrecordDto medicalrecords;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Person() {
	}

	/**
	 * 
	 * @param zip
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param city
	 * @param phone
	 * @param email
	 * @param firestation
	 * @param medicalrecords
	 */
	public Person(String firstName, String lastName, String address, String city, String zip, String phone,
			String email, FirestationDto firestation, MedicalrecordDto medicalrecords) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		this.firestation = firestation;
		this.medicalrecords = medicalrecords;
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

	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(String address) {
		this.address = address;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("zip")
	public String getZip() {
		return zip;
	}

	@JsonProperty("zip")
	public void setZip(String zip) {
		this.zip = zip;
	}

	@JsonProperty("phone")
	public String getPhone() {
		return phone;
	}

	@JsonProperty("phone")
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("firestation")
	public FirestationDto getFirestation() {
		return firestation;
	}

	@JsonProperty("firestation")
	public void setFirestation(FirestationDto firestation) {
		this.firestation = firestation;
	}

	@JsonProperty("medicalrecords")
	public MedicalrecordDto getMedicalrecord() {
		return medicalrecords;
	}

	@JsonProperty("medicalrecords")
	public void setMedicalrecord(MedicalrecordDto medicalrecords) {
		this.medicalrecords = medicalrecords;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("firstName", firstName).append("lastName", lastName)
				.append("address", address).append("city", city).append("zip", zip).append("phone", phone)
				.append("email", email).append("firestation", firestation).append("medicalrecords", medicalrecords)
				.toString();
	}

}
