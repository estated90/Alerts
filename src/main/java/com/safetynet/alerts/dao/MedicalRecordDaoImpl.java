package com.safetynet.alerts.dao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.ListObjects;
import com.safetynet.alerts.model.Medicalrecords;

@Service
public class MedicalRecordDaoImpl {

	@Autowired
	private ListObjects listObject;

	public List<Medicalrecords> returnAllMedicalRecord() {
		return listObject.getMedicalrecords();
	}

	public Medicalrecords saveMedicalRecord(Medicalrecords medicalRecord) {
		listObject.getMedicalrecords().add(medicalRecord);
		return medicalRecord;
	}

	public Medicalrecords updateMedicalRecord(@Valid Medicalrecords medicalRecord) {
		String firstName = medicalRecord.getFirstName();
		String lastName = medicalRecord.getLastName();
		List<Medicalrecords> medicalRecords = listObject.getMedicalrecords();
		Medicalrecords medicalrecordFiltered = medicalRecords.stream()
				.filter(str -> str.getFirstName().equals(firstName) && str.getLastName().equals(lastName)).findAny()
				.orElse(null);
		if (medicalrecordFiltered == null) {
			return null;
		} else {
			medicalRecords.set(medicalRecords.indexOf(medicalrecordFiltered), medicalRecord);
			return medicalrecordFiltered;
		}
	}

	public Medicalrecords deleteMedicalRecord(Medicalrecords medicalRecord) {
		String firstName = medicalRecord.getFirstName();
		String lastName = medicalRecord.getLastName();
		List<Medicalrecords> medicalRecords = listObject.getMedicalrecords();
		Medicalrecords medicalrecordFiltered = medicalRecords.stream()
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
