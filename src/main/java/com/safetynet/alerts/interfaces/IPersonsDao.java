package com.safetynet.alerts.interfaces;

import java.util.List;

import javax.validation.Valid;

import com.safetynet.alerts.model.Person;

/**
 * @author Nico
 *
 */
public interface IPersonsDao {

	public List<@Valid Person> returnAllPerson();

	public Person savePerson(@Valid Person person);

	public Person updatePerson(@Valid Person person);

	public Person deletePerson(Person person);

}
