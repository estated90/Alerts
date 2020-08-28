package com.safetynet.alerts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author nicolas
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "address",
    "station"
})
public class PersonPerStation {
	@JsonProperty("station")
	private String station;
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
	@JsonProperty("numberOfAdult")
	private int numberOfAdult;
	@JsonProperty("numberOfChildren")
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
	@JsonProperty("station")
	public String getStation() {
		return station;
	}

	/**
	 * @param station the station to set
	 */
	@JsonProperty("station")
	public void setStation(String station) {
		this.station = station;
	}

	/**
	 * @return the firstName
	 */
	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the address
	 */
	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	@JsonProperty("address")
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the zip
	 */
	@JsonProperty("zip")
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	@JsonProperty("zip")
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the phone
	 */
	@JsonProperty("phone")
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	@JsonProperty("phone")
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the numberOfAdult
	 */
	@JsonProperty("numberOfAdult")
	public int getNumberOfAdult() {
		return numberOfAdult;
	}

	/**
	 * @param numberOfAdult the numberOfAdult to set
	 */
	@JsonProperty("numberOfAdult")
	public void setNumberOfAdult(int numberOfAdult) {
		this.numberOfAdult = numberOfAdult;
	}

	/**
	 * @return the numberOfChildren
	 */
	/**
	 * @return
	 */
	@JsonProperty("numberOfChildren")
	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	/**
	 * @param numberOfChildren the numberOfChildren to set
	 */
	@JsonProperty("numberOfChildren")
	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}
	
}
