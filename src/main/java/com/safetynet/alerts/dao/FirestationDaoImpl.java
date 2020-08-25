package com.safetynet.alerts.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.interfaces.IFirestastionDao;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.ListObjects;

@Service
public class FirestationDaoImpl implements IFirestastionDao {

	@Autowired
	private ListObjects listObject;

	@Override
	public List<Firestation> returnAll() {
		List<Firestation> firestation = listObject.getFirestations();
		return firestation;
	}
}
