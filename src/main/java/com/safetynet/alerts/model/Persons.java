
package com.safetynet.alerts.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "firstName", "lastName", "address", "city", "zip", "phone", "email", "station", "medication", "allergies" })
@JsonFilter("monFiltreDynamique")
public class Persons {

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
	@JsonProperty("station")
	private String station;
	@JsonProperty("medications")
	private List<String> medications;
	@JsonProperty("allergies")
	private List<String> allergies;
    @JsonProperty("birthdate")
    private Date birthdate;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Persons() {
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
	 * @param station
	 * @param medications
	 * @param allergies
	 */
	public Persons(String firstName, String lastName, String address, String city, String zip, String phone,
			String email, String station, List<String> medications, List<String> allergies, Date birthdate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		this.station = station;
		this.medications = medications;
		this.allergies = allergies;
		this.birthdate = birthdate;
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
	@JsonProperty("station")
	public String getStation() {
		return station;
	}
	@JsonProperty("station")
	public void setStation(String station) {
		this.station = station;
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
	@JsonProperty("birthdate")
	public Date getBirthdate() {
		return birthdate;
	}
	@JsonProperty("birthdate")
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("firstName", firstName).append("lastName", lastName)
				.append("address", address).append("city", city).append("zip", zip).append("phone", phone)
				.append("email", email).append("station", station).append("Medication", medications)
				.append("Allergies", allergies).toString();
	}

}
