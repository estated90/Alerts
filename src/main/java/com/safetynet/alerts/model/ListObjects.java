
package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Service;

@Service
public class ListObjects {

	private List<Person> persons = new ArrayList<>();
	private List<Firestation> firestations = new ArrayList<>();
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
	public ListObjects(List<Person> persons, List<Firestation> firestations, List<Medicalrecord> medicalrecords) {
		super();
		this.persons = persons;
		this.firestations = firestations;
		this.medicalrecords = medicalrecords;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<Firestation> getFirestations() {
		return firestations;
	}

	public void setFirestations(List<Firestation> firestations) {
		this.firestations = firestations;
	}

	public List<Medicalrecord> getMedicalrecords() {
		return medicalrecords;
	}

	public void setMedicalrecords(List<Medicalrecord> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("persons", persons).append("firestations", firestations)
				.append("medicalrecords", medicalrecords).toString();
	}

}
