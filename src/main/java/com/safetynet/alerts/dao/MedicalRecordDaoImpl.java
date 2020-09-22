package com.safetynet.alerts.dao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.ListObjects;
import com.safetynet.alerts.model.Medicalrecord;

@Service
public class MedicalRecordDaoImpl {

	@Autowired
	private ListObjects listObject;

	public List<Medicalrecord> returnAllMedicalRecord() {
		return listObject.getMedicalrecords();
	}

	public Medicalrecord saveMedicalRecord(Medicalrecord medicalRecord) {
		listObject.getMedicalrecords().add(medicalRecord);
		return medicalRecord;
	}

	public Medicalrecord updateMedicalRecord(@Valid Medicalrecord medicalRecord) {
		String firstName = medicalRecord.getFirstName();
		String lastName = medicalRecord.getLastName();
		List<Medicalrecord> medicalRecords = listObject.getMedicalrecords();
		Medicalrecord medicalrecordFiltered = medicalRecords.stream()
				.filter(str -> str.getFirstName().equals(firstName) && str.getLastName().equals(lastName)).findAny()
				.orElse(null);
		if (medicalrecordFiltered == null) {
			return null;
		} else {
			medicalRecords.set(medicalRecords.indexOf(medicalrecordFiltered), medicalRecord);
			return medicalrecordFiltered;
		}
	}

	public Medicalrecord deleteMedicalRecord(Medicalrecord medicalRecord) {
		String firstName = medicalRecord.getFirstName();
		String lastName = medicalRecord.getLastName();
		List<Medicalrecord> medicalRecords = listObject.getMedicalrecords();
		Medicalrecord medicalrecordFiltered = medicalRecords.stream()
				.filter(str -> str.getFirstName().equals(firstName) && str.getLastName().equals(lastName)).findAny()
				.orElse(null);
		if (medicalrecordFiltered == null) {
			return null;
		} else {
			medicalRecords.remove(medicalRecords.indexOf(medicalrecordFiltered));
			return medicalrecordFiltered;
		}
	}
}
