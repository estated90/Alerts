package com.safetynet.alerts.model;

public class Persons {

	private String name;
	private String lastName;
	private String address;
	private String city;
	private long zip;
	private String phone;
	private String email;

	public Persons() {
	}

	public Persons(String name, String lastName, String address, String city, long zip, String phone, String email) {
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public long getZip() {
		return zip;
	}

	public void setZip(long zip) {
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
	
	@Override
	public String toString() {
	       return "Persons{" +
	                "name=" + name +
	                ", lastName='" + lastName + '\'' +
	                ", address=" + address +
	                ", city=" + city +
	                ", zip =" + zip +
	                ", phone =" + phone +
	                ", email =" + email +
	                '}';
	}
}
