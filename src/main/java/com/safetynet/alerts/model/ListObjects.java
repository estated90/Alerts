
package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "persons",
    "firestations",
    "medicalrecords"
})
public class ListObjects {

    @JsonProperty("persons")
    private List<Persons> persons = new ArrayList<>();
    @JsonProperty("firestations")
    private List<Firestation> firestations = new ArrayList<>();
    @JsonProperty("medicalrecords")
    private List<Medicalrecord> medicalrecords = new ArrayList<>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ListObjects() {
    }

    /**
     * 
     * @param persons
     * @param firestations
     * @param medicalrecords
     */
    public ListObjects(List<Persons> persons, List<Firestation> firestations, List<Medicalrecord> medicalrecords) {
        super();
        this.persons = persons;
        this.firestations = firestations;
        this.medicalrecords = medicalrecords;
    }

    @JsonProperty("persons")
    public List<Persons> getPersons() {
        return persons;
    }

    @JsonProperty("persons")
    public void setPersons(List<Persons> persons) {
        this.persons = persons;
    }

    public ListObjects withPersons(List<Persons> persons) {
        this.persons = persons;
        return this;
    }

    @JsonProperty("firestations")
    public List<Firestation> getFirestations() {
        return firestations;
    }

    @JsonProperty("firestations")
    public void setFirestations(List<Firestation> firestations) {
        this.firestations = firestations;
    }

    public ListObjects withFirestations(List<Firestation> firestations) {
        this.firestations = firestations;
        return this;
    }

    @JsonProperty("medicalrecords")
    public List<Medicalrecord> getMedicalrecords() {
        return medicalrecords;
    }

    @JsonProperty("medicalrecords")
    public void setMedicalrecords(List<Medicalrecord> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }

    public ListObjects withMedicalrecords(List<Medicalrecord> medicalrecords) {
        this.medicalrecords = medicalrecords;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("persons", persons).append("firestations", firestations).append("medicalrecords", medicalrecords).toString();
    }

}
