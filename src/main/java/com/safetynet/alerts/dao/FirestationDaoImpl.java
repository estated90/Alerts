package com.safetynet.alerts.dao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.ListObjects;

@Component
public class FirestationDaoImpl {

	@Autowired
	private ListObjects listObject;

	public List<Firestation> returnAllFirestation() {
		return listObject.getFirestations();
	}

	public Firestation saveFirestation(@Valid Firestation firestation) {
		listObject.getFirestations().add(firestation);
		return listObject.getFirestations().stream().filter(str -> str.getAddress().equals(firestation.getAddress()))
				.findAny().orElse(null);
	}

	public Firestation updateFirestation(@Valid Firestation firestation) {
		String stationAddress = firestation.getAddress();
		List<Firestation> firestations = listObject.getFirestations();
		Firestation firestationFiltered = firestations.stream().filter(str -> str.getAddress().equals(stationAddress))
				.findAny().orElse(null);
		if (firestationFiltered == null) {
			return null;
		} else {
			firestations.set(firestations.indexOf(firestationFiltered), firestation);
			return firestation;
		}
	}

	public Firestation deleteFirestation(Firestation firestation) {
		List<Firestation> firestations = listObject.getFirestations();
		Firestation firestationFiltered = null;
		if (firestation.getAddress() != null) {
			String stationAddress = firestation.getAddress();
			firestationFiltered = firestations.stream()
					.filter(str -> str.getAddress().contentEquals(stationAddress)).findAny().orElse(null);
		} else if (firestation.getStation() != 0) {
			int stationNumber = firestation.getStation();
			firestationFiltered = firestations.stream().filter(str -> str.getStation() == stationNumber)
					.findAny().orElse(null);
		}
		if (firestationFiltered == null) {
			return null;
		} else {
			firestations.remove(firestations.indexOf(firestationFiltered));
			return firestation;
		}
	}
}
