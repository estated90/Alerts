package com.safetynet.alerts.dao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.interfaces.IMedicalrecords;
import com.safetynet.alerts.model.ListObjects;
import com.safetynet.alerts.model.Medicalrecord;


@Service
public class MedicalRecordDaoImpl implements IMedicalrecords {

	@Autowired
	private ListObjects listObject;

	@Override
	public List<Medicalrecord> returnAllMedicalRecord() {
		return listObject.getMedicalrecords();
	}
	@Override
	public Medicalrecord saveMedicalRecord(Medicalrecord medicalRecord) {
		listObject.getMedicalrecords().add(medicalRecord);
		return medicalRecord;
	}

	@Override
	public Medicalrecord updateMedicalRecord(@Valid Medicalrecord medicalRecord) {
		String firstName = medicalRecord.getFirstName();
		String lastName = medicalRecord.getLastName();
		List<Medicalrecord> medicalRecords = listObject.getMedicalrecords();
		for(Medicalrecord medicalRecord2 : medicalRecords) {
			if(medicalRecord2.getFirstName().equals(firstName)&&medicalRecord2.getLastName().contentEquals(lastName)) {
				medicalRecords.set(medicalRecords.indexOf(medicalRecord2), medicalRecord);
				break;
			}
		}
		return medicalRecord;
	}
	@Override
	public Medicalrecord deleteMedicalRecord(Medicalrecord medicalRecord) {
		String firstName = medicalRecord.getFirstName();
		String lastName = medicalRecord.getLastName();
		List<Medicalrecord> medicalRecords = listObject.getMedicalrecords();
		for(Medicalrecord medicalRecord2 : medicalRecords) {
			if(medicalRecord2.getFirstName().equals(firstName)&&medicalRecord2.getLastName().contentEquals(lastName)) {
				medicalRecords.remove(medicalRecords.indexOf(medicalRecord2));
				break;
			}
		}
		return medicalRecord;
	}
}
