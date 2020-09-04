
package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @author nicolas
 *
 */

public class Firestation {

    private String address;
    private int station;
    @JsonIgnore
    private List<Person> person = new ArrayList<>(); 

    /**
     * No args constructor for use in serialization
     * 
     */
    public Firestation() {
    }

    /**
     * 
     * @param address
     * @param station
     */
    public Firestation(String address, int station, List<Person> person) {
        super();
        this.address = address;
        this.station = station;
        this.person = person;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    /**
	 * @return the person
	 */
	public List<Person> getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(List<Person> person) {
		this.person = person;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this).append("address", address).append("station", station).toString();
    }

}
