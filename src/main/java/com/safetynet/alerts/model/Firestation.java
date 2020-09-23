
package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @author nicolas
 *
 */

public class Firestation {

	@NotNull(message = "address cannot be null")
    private String address;
	@NotNull(message = "station cannot be null")
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
     * @param address of the firestation
     * @param station number
     */
    public Firestation(String address, int station) {
        super();
        this.address = address;
        this.station = station;
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
