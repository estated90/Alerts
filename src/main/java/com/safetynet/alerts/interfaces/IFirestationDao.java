package com.safetynet.alerts.interfaces;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;
import com.safetynet.alerts.model.Firestation;

@Component
public interface IFirestationDao {

	public List<Firestation> returnAllFirestation();

	public Firestation saveFirestation(@Valid Firestation firestation);

	public Firestation updateFirestation(@Valid Firestation firestation);

	public Firestation deleteFirestation(Firestation firestation);

}