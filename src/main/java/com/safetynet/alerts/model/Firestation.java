
package com.safetynet.alerts.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "address",
    "station"
})
public class Firestation {

    @JsonProperty("address")
    private String address;
    @JsonProperty("station")
    private String station;

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
    public Firestation(String address, String station) {
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

    public Firestation withAddress(String address) {
        this.address = address;
        return this;
    }

    @JsonProperty("station")
    public String getStation() {
        return station;
    }

    @JsonProperty("station")
    public void setStation(String station) {
        this.station = station;
    }

    public Firestation withStation(String station) {
        this.station = station;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("address", address).append("station", station).toString();
    }

}
