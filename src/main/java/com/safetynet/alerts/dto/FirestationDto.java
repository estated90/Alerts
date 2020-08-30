package com.safetynet.alerts.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.googlecode.jmapper.annotations.JGlobalMap;

/**
 * @author nicolas
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "address",
    "station"
})
@JGlobalMap
public class FirestationDto {


    @JsonProperty("address")
    private String address;
    @JsonProperty("station")
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

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("station")
    public String getStation() {
        return station;
    }

    @JsonProperty("station")
    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("address", address).append("station", station).toString();
    }

}
