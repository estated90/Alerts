package com.safetynet.alerts.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.interfaces.IPersonsDao;
import com.safetynet.alerts.model.ListObjects;
import com.safetynet.alerts.model.Person;

@Service
public class PersonsDaoImpl implements IPersonsDao {

	@Autowired
	private ListObjects listObject;
	
	@Override
	public List<Person> returnAll() {
		return listObject.getPersons();
	}

}
