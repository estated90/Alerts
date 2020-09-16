package com.safetynet.alerts.dao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.safetynet.alerts.interfaces.IFirestationDao;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.ListObjects;

@Component
public class FirestationDaoImpl implements IFirestationDao {

	@Autowired
	private ListObjects listObject;

	@Override
	public List<Firestation> returnAllFirestation() {
		return listObject.getFirestations();
	}

	@Override
	public Firestation saveFirestation(@Valid Firestation firestation) {
		listObject.getFirestations().add(firestation);
		return firestation;
	}

	@Override
	public Firestation updateFirestation(@Valid Firestation firestation) {
		String stationAddress = firestation.getAddress();
		List<Firestation> firestations = listObject.getFirestations();
		for (Firestation firestation2 : firestations) {
			if (firestation2.getAddress().equals(stationAddress)) {
				firestations.set(firestations.indexOf(firestation2), firestation);
				break;
			}
		}
		return firestation;
	}

	@Override
	public Firestation deleteFirestation(Firestation firestation) {
		List<Firestation> firestations = listObject.getFirestations();
		if (firestation.getAddress() != null) {
			String stationNumber = firestation.getAddress();
			for (Firestation firestation2 : firestations) {
				if (firestation2.getAddress().equals(stationNumber)) {
					firestations.remove(firestations.indexOf(firestation2));
					break;
				}
			}
		} else if (firestation.getStation() != 0) {
			int stationNumber = firestation.getStation();
			for (Firestation firestation2 : firestations) {
				if (firestation2.getStation() == stationNumber) {
					firestations.remove(firestations.indexOf(firestation2));
					break;
				}
			}
		}
		return firestation;
	}
}
