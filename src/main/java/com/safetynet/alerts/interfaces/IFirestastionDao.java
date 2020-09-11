package com.safetynet.alerts.interfaces;

import java.util.List;

import javax.validation.Valid;

import com.safetynet.alerts.model.Firestation;

public interface IFirestastionDao {

	public List<Firestation> returnAllFirestation();

	public Firestation saveFirestation(@Valid Firestation firestation);

	public Firestation updateFirestation(@Valid Firestation firestation);

	public Firestation deleteFirestation(Firestation firestation);

}