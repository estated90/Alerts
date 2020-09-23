
package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author nicol
 *
 */
@Service
public class ListObjects {

	private List<Person> persons = new ArrayList<>();
	private List<Firestation> firestations = new ArrayList<>();
	private List<Medicalrecords> medicalrecords = new ArrayList<>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public ListObjects() {
	}

	/**
	 * 
	 * @param persons list of persons
	 * @param firestations list of 
	 * @param medicalrecords list of 
	 */
	public ListObjects(List<Person> persons, List<Firestation> firestations, List<Medicalrecords> medicalrecords) {
		super();
		this.persons = persons;
		this.firestations = firestations;
		this.medicalrecords = medicalrecords;
	}

	/**
	 * 
	 * @return Person
	 */
	public List<Person> getPersons() {
		return persons;
	}
	/**
	 * 
	 * @return Firestation
	 */
	public List<Firestation> getFirestations() {
		return firestations;
	}
	/**
	 * 
	 * @return Medicalrecords
	 */
	public List<Medicalrecords> getMedicalrecords() {
		return medicalrecords;
	}
}
