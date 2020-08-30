package com.safetynet.alerts.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.googlecode.jmapper.annotations.JGlobalMap;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Medicalrecord;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "firstName", "lastName", "address", "city", "zip", "phone", "email", "firestation", "medicalrecords"})
@JGlobalMap
public class PersonDto {


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

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public PersonDto() {
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
	public PersonDto(String firstName, String lastName, String address, String city, String zip, String phone,
			String email, Firestation firestation, Medicalrecord medicalrecords) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
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

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("firstName", firstName).append("lastName", lastName)
				.append("address", address).append("city", city).append("zip", zip).append("phone", phone)
				.append("email", email).toString();
	}

}


