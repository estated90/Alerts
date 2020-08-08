package com.safetynet.alerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Persons;

@Repository
public class PersonsDao implements IPersonsDao{
	
	public static List<Persons> person = new ArrayList<>();
	
	@Override
	public List<Persons> findAll() {
		return person;	
	}
	
    @Override
    public Persons findById(String name, String lastName) {
        for (Persons person : person) {
            if((person.getFirstName() == name) && (person.getLastName() == lastName)){
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
