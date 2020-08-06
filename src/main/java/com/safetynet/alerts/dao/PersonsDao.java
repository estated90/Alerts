package com.safetynet.alerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

@Repository
public class PersonsDao implements IPersonsDao{
	
	public static List<Person> person = new ArrayList<>();
	
	@Override
	public List<Person> findAll() {
		return person;	
	}
	
    @Override
    public Person findById(String name, String lastName) {
        for (Person person : person) {
            if((person.getFirstName() == name) && (person.getLastName() == lastName)){
                return person;
            }
        }
        return null;
    }
    
    @Override
    public Person save(Person persons) {
    	person.add(persons);
        return persons;
    }
}
