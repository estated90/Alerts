package com.safetynet.alerts.interfaces;

import java.util.List;

import com.safetynet.alerts.dto.PersonDto;

/**
 * @author Nico
 *
 */
public interface IPersonsDao {

	public List<PersonDto> returnAll();

}
