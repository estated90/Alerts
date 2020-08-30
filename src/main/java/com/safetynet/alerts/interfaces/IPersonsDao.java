package com.safetynet.alerts.interfaces;

import java.util.List;

import com.safetynet.alerts.model.Person;

/**
 * @author Nico
 *
 */
public interface IPersonsDao {

	public List<Person> returnAll();

}
