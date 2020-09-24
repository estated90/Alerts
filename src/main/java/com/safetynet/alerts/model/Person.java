
package com.safetynet.alerts.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Person {

	@NotNull(message = "Firstname cannot be null")
	private String firstName;
	@NotNull(message = "Lastname cannot be null")
	private String lastName;
	@NotNull(message = "address cannot be null")
	private String address;
	@NotNull(message = "city cannot be null")
	private String city;
	@NotNull(message = "zip cannot be null")
	private String zip;
	@Size(min = 6, max = 14, message 
    = "phone number must be between 6 and 14 characters")
	private String phone;
	@Email(message = "Email should be valid")
    @NotNull
	private String email;
	@JsonIgnore
	private Firestation firestation;
	@JsonIgnore
	private Medicalrecords medicalrecord;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Person() {
	}

	/**
	 * 
	 * @param zip of the person
	 * @param firstName of the person
	 * @param lastName of the person
	 * @param address of the person
	 * @param city of the person
	 * @param phone of the person
	 * @param email of the person
	 * @param firestation of the person
	 * @param medicalrecords of the person
	 */
	public Person(String firstName, String lastName, String address, String city, String zip, String phone,
			String email, Firestation firestation, Medicalrecords medicalrecords) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		this.firestation = firestation;
		this.medicalrecord = medicalrecords;
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

	public Medicalrecords getMedicalrecord() {
		return medicalrecord;
	}

	public void setMedicalrecord(Medicalrecords medicalrecords) {
		this.medicalrecord = medicalrecords;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("firstName", firstName).append("lastName", lastName)
				.append("address", address).append("city", city).append("zip", zip).append("phone", phone)
				.append("email", email).append("firestation", firestation).append("medicalrecords", medicalrecord)
				.toString();
	}

}
