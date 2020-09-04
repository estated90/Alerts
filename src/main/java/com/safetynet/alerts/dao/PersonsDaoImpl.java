package com.safetynet.alerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.jmapper.JMapper;
import com.safetynet.alerts.dto.PersonDto;
import com.safetynet.alerts.interfaces.IPersonsDao;
import com.safetynet.alerts.model.ListObjects;
import com.safetynet.alerts.model.Person;

@Service
public class PersonsDaoImpl implements IPersonsDao {

	@Autowired
	private ListObjects listObject;
	
	@Override
	public List<PersonDto> returnAll() {
		JMapper<PersonDto, Person> userMapperPerson = new JMapper<>(PersonDto.class, Person.class);
		List<Person> persons = listObject.getPersons();
		List<PersonDto> personsDto = new ArrayList<>();
		for (Person person : persons) {
			PersonDto resultPerson = userMapperPerson.getDestination(person);
			personsDto.add(resultPerson);
		}
		return personsDto;
	}

}
