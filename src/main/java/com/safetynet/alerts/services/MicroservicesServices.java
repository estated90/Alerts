package com.safetynet.alerts.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.jmapper.JMapper;
import com.safetynet.alerts.dto.AdultList;
import com.safetynet.alerts.dto.ChildAlert;
import com.safetynet.alerts.dto.ChildList;
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

	public PersonPerStationDto firestationListPerson(int station) throws Exception {
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
					if (age < 18) {
						numberChild += 1;
					} else if (age > 18) {
						numberAdult += 1;
					} else if (age == -1) {
						throw new Exception("The age of a person was not accounted for");
					}
					personDto.add(resultPerson);
				}
			}
		}
		personPerStationDto.setNumberOfAdult(numberAdult);
		personPerStationDto.setNumberOfChildren(numberChild);
		return personPerStationDto;
	}

	public ChildAlert childrenAlerts(String address) {

		List<Person> persons = listObjects.getPersons().stream().filter(str -> str.getAddress().equals(address))
				.collect(Collectors.toList());
		ChildAlert childAlert = new ChildAlert(new ArrayList<>(), new ArrayList<>());
		for (Person person : persons) {
			int age = calculateAge.ageCalculation(person.getMedicalrecord().getBirthdate());
			if (age < 18) {
				ChildList childList = new ChildList();;
				childList.setFirstName(person.getFirstName());
				childList.setLastName(person.getLastName());
				childList.setAge(age);
				childAlert.getChild().add(childList);
			} else {
				JMapper<AdultList, Person> userMapperChild = new JMapper<>(AdultList.class, Person.class);
				AdultList resultAdult = userMapperChild.getDestination(person);
				childAlert.getFamilyMember().add(resultAdult);
			}
		}
		return childAlert;

	}
}
