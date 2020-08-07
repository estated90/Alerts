package com.safetynet.alerts.data;

import java.util.List;

import org.springframework.stereotype.Component;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Medicalrecord;
import com.safetynet.alerts.model.Person;

@Component
public class Data {

	public List<Person> person;

	public List<Person> getPerson() {
		return person;
	}

	public void setPerson(List<Person> person) {
		this.person = person;
	}
	
	public List<Firestation> firestation;

	public List<Firestation> getFirestation() {
		return firestation;
	}

	public void setFirestation(List<Firestation> firestation) {
		this.firestation = firestation;
	}
	
	public List<Medicalrecord> medicalRecord;

	public List<Medicalrecord> getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(List<Medicalrecord> medicalRecord) {
		this.medicalRecord = medicalRecord;
	}
	
}
