package com.safetynet.alerts.dao;

import java.util.List;

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
	public List<Medicalrecord> returnAll() {
		List<Medicalrecord> medicalrecord = listObject.getMedicalrecords();
		return medicalrecord;
	}
}
