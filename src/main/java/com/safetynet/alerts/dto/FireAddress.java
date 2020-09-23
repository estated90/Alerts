package com.safetynet.alerts.dto;

import java.util.ArrayList;
import java.util.List;

public class FireAddress {

	private int station;
	private List<FireAddressInhabitant> inhabitant = new ArrayList<>();
	/**
	 * 
	 */
	public FireAddress() {
	}
	/**
	 * @param station in charge
	 * @param inhabitant list of the house
	 */
	public FireAddress(int station, List<FireAddressInhabitant> inhabitant) {
		super();
		this.station = station;
		this.inhabitant = inhabitant;
	}
	/**
	 * @return the station
	 */
	public int getStation() {
		return station;
	}
	/**
	 * @param station the station to set
	 */
	public void setStation(int station) {
		this.station = station;
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
