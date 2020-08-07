
package com.safetynet.alerts.model;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "firstName",
    "lastName",
    "birthdate",
    "medications",
    "allergies"
})
public class Medicalrecord {

    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("birthdate")
    private String birthdate;
    @JsonProperty("medications")
    private List<String> medications = null;
    @JsonProperty("allergies")
    private List<String> allergies = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Medicalrecord() {
    }

    /**
     * 
     * @param allergies
     * @param firstName
     * @param lastName
     * @param birthdate
     * @param medications
     */
    public Medicalrecord(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Medicalrecord withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Medicalrecord withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @JsonProperty("birthdate")
    public String getBirthdate() {
        return birthdate;
    }

    @JsonProperty("birthdate")
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Medicalrecord withBirthdate(String birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    @JsonProperty("medications")
    public List<String> getMedications() {
        return medications;
    }

    @JsonProperty("medications")
    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public Medicalrecord withMedications(List<String> medications) {
        this.medications = medications;
        return this;
    }

    @JsonProperty("allergies")
    public List<String> getAllergies() {
        return allergies;
    }

    @JsonProperty("allergies")
    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public Medicalrecord withAllergies(List<String> allergies) {
        this.allergies = allergies;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("firstName", firstName).append("lastName", lastName).append("birthdate", birthdate).append("medications", medications).append("allergies", allergies).toString();
    }

}
