package com.safetynet.alerts.dto;

import java.util.ArrayList;
import java.util.List;

public class Flood {

	private String address;
	private List<FireAddressInhabitant> inhabitant = new ArrayList<>();
	/**
	 * 
	 */
	public Flood() {
	}
	/**
	 * @param address of the coverage
	 * @param inhabitant of the addresses
	 */
	public Flood(String address, List<FireAddressInhabitant> inhabitant) {
		super();
		this.address = address;
		this.inhabitant = inhabitant;
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
	 * @return the inhabitant
	 */
	public List<FireAddressInhabitant> getInhabitant() {
		return inhabitant;
	}
	/**
	 * @param inhabitant the inhabitant to set
	 */
	public void setInhabitant(List<FireAddressInhabitant> inhabitant) {
		this.inhabitant = inhabitant;
	}
	
	
}
