
package com.safetynet.alerts.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Person {

	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;
	@JsonIgnore
	private Firestation firestation;
	@JsonIgnore
	private Medicalrecord medicalrecords;

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
			String email, Firestation firestation, Medicalrecord medicalrecords) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Firestation getFirestation() {
		return firestation;
	}

	public void setFirestation(Firestation firestation) {
		this.firestation = firestation;
	}

	public Medicalrecord getMedicalrecord() {
		return medicalrecords;
	}

	public void setMedicalrecord(Medicalrecord medicalrecords) {
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
