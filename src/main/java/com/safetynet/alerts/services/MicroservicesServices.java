package com.safetynet.alerts.services;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.jmapper.JMapper;
import com.safetynet.alerts.dto.PersonDto;
import com.safetynet.alerts.dto.PersonPerStationDto;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.ListObjects;
import com.safetynet.alerts.model.Person;

@Service
public class MicroservicesServices {

	@Autowired
	private ListObjects listObjects;
	@Autowired
	private CalculateAge calculateAge;

	public PersonPerStationDto firestationListPerson(int station) {
		PersonPerStationDto personPerStationDto = new PersonPerStationDto();
		int numberAdult = 0;
		int numberChild = 0;
		int age = 0;
		LocalDate birthdate = null;
		personPerStationDto.setStation(station);
		List<Firestation> firestations = listObjects.getFirestations();
		for (Firestation firestation : firestations) {
			if (firestation.getStation() == station) {
				List<Person> persons = firestation.getPerson();
				JMapper<PersonDto, Person> userMapperPerson = new JMapper<>(PersonDto.class, Person.class);
				for (Person person : persons) {
					PersonDto resultPerson = userMapperPerson.getDestination(person);
					List<PersonDto> personDto = personPerStationDto.getPerson();
					birthdate = person.getMedicalrecord().getBirthdate();
					age = calculateAge.ageCalculation(birthdate);
					if (age<18) {
						numberChild+=1;
					} else {
						numberAdult+=1;
					}
					personDto.add(resultPerson);
				}
			}
		}
		personPerStationDto.setNumberOfAdult(numberAdult);
		personPerStationDto.setNumberOfChildren(numberChild);
		return personPerStationDto;
	}
}
