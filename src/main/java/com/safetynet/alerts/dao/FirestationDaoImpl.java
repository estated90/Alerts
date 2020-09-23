package com.safetynet.alerts.dao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.ListObjects;

/**
 * @author nicol
 *
 */
@Component
public class FirestationDaoImpl {

	@Autowired
	private ListObjects listObject;

	/**
	 * 
	 * @return Firestation
	 */
	public List<Firestation> returnAllFirestation() {
		return listObject.getFirestations();
	}

	/**
	 * 
	 * @param firestation object from API
	 * @return firestation filtered
	 */
	public Firestation saveFirestation(@Valid Firestation firestation) {
		listObject.getFirestations().add(firestation);
		return listObject.getFirestations().stream().filter(str -> str.getAddress().equals(firestation.getAddress()))
				.findAny().orElse(null);
	}

	/**
	 * 
	 * @param firestation object from API
	 * @return firestation if exist or null
	 */
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

	/**
	 * 
	 * @param firestation object from API
	 * @return firestation if exist or null
	 */
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
