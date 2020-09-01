package com.safetynet.alerts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author nicolas
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonPerStation {
	private String station;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private int numberOfAdult;
	private int numberOfChildren;
    
	/**
     * No args constructor for use in serialization
     * 
     */
    public PersonPerStation() {
    }
	
	/**
	 * @param station
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param city
	 * @param zip
	 * @param phone
	 * @param numberOfAdult
	 * @param numberOfChildren
	 */
	public PersonPerStation(String station, String firstName, String lastName, String address, String city, String zip,
			String phone, int numberOfAdult, int numberOfChildren) {
		super();
		this.station = station;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.numberOfAdult = numberOfAdult;
		this.numberOfChildren = numberOfChildren;
	}

	/**
	 * @return the station
	 */
	public String getStation() {
		return station;
	}

	/**
	 * @param station the station to set
	 */
	public void setStation(String station) {
		this.station = station;
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
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the numberOfAdult
	 */
	public int getNumberOfAdult() {
		return numberOfAdult;
	}

	/**
	 * @param numberOfAdult the numberOfAdult to set
	 */
	public void setNumberOfAdult(int numberOfAdult) {
		this.numberOfAdult = numberOfAdult;
	}

	/**
	 * @return the numberOfChildren
	 */
	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	/**
	 * @param numberOfChildren the numberOfChildren to set
	 */
	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}
	
}
