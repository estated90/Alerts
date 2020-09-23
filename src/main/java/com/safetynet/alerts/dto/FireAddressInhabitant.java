package com.safetynet.alerts.dto;

import java.util.List;

import com.googlecode.jmapper.annotations.JMap;

public class FireAddressInhabitant {
	@JMap
	private String firstName;
	@JMap
	private String lastName;
	@JMap
	private String phone;
	private int age;
	private List<String> medications;
	/**
	 * 
	 */
	public FireAddressInhabitant() {
	}
	/**
	 * @param firstName person
	 * @param lastName person
	 * @param phone person
	 * @param age person
	 * @param medications person
	 */
	public FireAddressInhabitant(String firstName, String lastName, String phone, int age, List<String> medications) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.age = age;
		this.medications = medications;
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
	 * @return phone the phoneNumber
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phoneNumber to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * @return the medications
	 */
	public List<String> getMedications() {
		return medications;
	}
	/**
	 * @param medications the medications to set
	 */
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
}
