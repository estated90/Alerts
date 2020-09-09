package com.safetynet.alerts.dto;

import java.util.List;

import com.googlecode.jmapper.annotations.JMap;

public class PersonInfo {

	@JMap
	private String firstName;
	@JMap
	private String lastName;
	@JMap
	private String address;
	private int age;
	@JMap
	private String email;
	private List<String> medication;
	/**
	 * 
	 */
	public PersonInfo() {
	}
	/**
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param age
	 * @param email
	 * @param medication
	 */
	public PersonInfo(String firstName, String lastName, String address, int age, String email,
			List<String> medication) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.age = age;
		this.email = email;
		this.medication = medication;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the medication
	 */
	public List<String> getMedication() {
		return medication;
	}
	/**
	 * @param medication the medication to set
	 */
	public void setMedication(List<String> medication) {
		this.medication = medication;
	}
	
		
	
}
