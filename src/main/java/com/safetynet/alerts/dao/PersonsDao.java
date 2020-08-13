package com.safetynet.alerts.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.interfaces.IPersonsDao;
import com.safetynet.alerts.model.ListObjects;
import com.safetynet.alerts.model.Persons;

@Repository
public class PersonsDao implements IPersonsDao{
	
	public static ListObjects listobjects = new ListObjects();
	public static List<Persons> person = listobjects.getPersons();
	
	@Override
	public List<Persons> findAll() {
		return person;	
	}
	
    @Override
    public Persons findById(String firstName, String lastName) {
        for (Persons person : person) {
            if((person.getFirstName() == firstName) && (person.getLastName() == lastName)){
                return person;
            }
        }
        return null;
    }
    
    @Override
    public Persons save(Persons persons) {
    	person.add(persons);
        return persons;
    }
}
