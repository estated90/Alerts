package com.safetynet.alerts.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.googlecode.jmapper.annotations.JGlobalMap;

/**
 * @author nicolas
 *
 */

@JGlobalMap
public class FirestationDto {

    private String address;
    private String station;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FirestationDto() {
    }

    /**
     * 
     * @param address
     * @param station
     */
    public FirestationDto(String address, String station) {
        super();
        this.address = address;
        this.station = station;
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

	@Override
    public String toString() {
        return new ToStringBuilder(this).append("address", address).append("station", station).toString();
    }

}