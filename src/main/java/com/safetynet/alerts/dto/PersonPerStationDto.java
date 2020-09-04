package com.safetynet.alerts.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.googlecode.jmapper.annotations.JGlobalMap;

/**
 * @author nicolas
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JGlobalMap
public class PersonPerStationDto {
	@JsonProperty("station")
	private int station;
	@JsonProperty("person")
	private List<PersonDto> person = new ArrayList<>();
	@JsonProperty("numberOfAdult")
	private int numberOfAdult;
	@JsonProperty("numberOfChildren")
	private int numberOfChildren;
    
	/**
     * No args constructor for use in serialization
     * 
     */
    public PersonPerStationDto() {
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
	public PersonPerStationDto(int station, List<PersonDto> personDto, int numberOfAdult, int numberOfChildren) {
		super();
		this.station = station;
		this.person = personDto;
		this.numberOfAdult = numberOfAdult;
		this.numberOfChildren = numberOfChildren;
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

	public List<PersonDto> getPerson() {
		return person;
	}

	public void setPerson(List<PersonDto> personDto) {
		this.person = personDto;
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
