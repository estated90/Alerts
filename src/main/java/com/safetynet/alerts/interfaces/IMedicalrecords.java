package com.safetynet.alerts.interfaces;

import java.util.List;

import javax.validation.Valid;

import com.safetynet.alerts.model.Medicalrecord;

public interface IMedicalrecords {

	public List<Medicalrecord> returnAllMedicalRecord();

	public Medicalrecord saveMedicalRecord(Medicalrecord medicalRecord);

	public Medicalrecord updateMedicalRecord(@Valid Medicalrecord medicalRecord);

	public Medicalrecord deleteMedicalRecord(Medicalrecord medicalRecord);

}