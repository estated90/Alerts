package com.safetynet.alerts.interfaces;

import java.util.List;

import com.safetynet.alerts.model.Persons;

/**
 * @author Nico
 *
 */
public interface IPersonsDao {

	public List<Persons> returnAll();

}
