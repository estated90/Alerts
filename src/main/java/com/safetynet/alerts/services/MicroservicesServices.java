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
import com.safetynet.alerts.dto.CommunityEmail;
import com.safetynet.alerts.dto.FireAddress;
import com.safetynet.alerts.dto.FireAddressInhabitant;
import com.safetynet.alerts.dto.Flood;
import com.safetynet.alerts.dto.PersonDto;
import com.safetynet.alerts.dto.PersonInfo;
import com.safetynet.alerts.dto.CoverageDto;
import com.safetynet.alerts.dto.PhoneAlert;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.ListObjects;
import com.safetynet.alerts.model.Person;

@Service
public class MicroservicesServices {

	@Autowired
	private ListObjects listObjects;
	@Autowired
	private CalculateAge calculateAge;

	public CoverageDto firestationListPerson(int station) {
		CoverageDto personPerStationDto = new CoverageDto();
		int numberAdult = 0;
		int numberChild = 0;
		int age = 0;
		LocalDate birthdate = null;
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
				ChildList childList = new ChildList();
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

	public PhoneAlert phoneAlert(int station) {
		PhoneAlert phoneAlert = new PhoneAlert();
		List<Firestation> firestations = listObjects.getFirestations().stream()
				.filter(str -> str.getStation() == station).collect(Collectors.toList());
		for (Firestation firestation : firestations) {
			List<Person> persons = firestation.getPerson();
			for (Person person : persons) {
				phoneAlert.getPhoneNumber().add(person.getPhone());
			}

		}
		return phoneAlert;
	}

	public FireAddress fireAdress(String address) {
		FireAddress fireAddress = new FireAddress();
		List<Person> persons = listObjects.getPersons().stream().filter(str -> str.getAddress().equals(address))
				.collect(Collectors.toList());
		for (Person person : persons) {
			JMapper<FireAddressInhabitant, Person> userMapperPerson = new JMapper<>(FireAddressInhabitant.class,
					Person.class);
			FireAddressInhabitant resultAdult = userMapperPerson.getDestination(person);
			resultAdult.setMedications(person.getMedicalrecord().getMedications());
			LocalDate bitrthdate = person.getMedicalrecord().getBirthdate();
			resultAdult.setAge(calculateAge.ageCalculation(bitrthdate));
			fireAddress.setStation(person.getFirestation().getStation());
			fireAddress.getInhabitant().add(resultAdult);
		}
		return fireAddress;
	}

	public List<Flood> floodStation(List<Integer> stations) {
		List<Flood> floodList = new ArrayList<>();
		for (int station : stations) {
			List<Firestation> firestations = listObjects.getFirestations().stream()
					.filter(str -> str.getStation() == station).collect(Collectors.toList());
			for (Firestation firestation : firestations) {
				Flood flood = new Flood();
				flood.setAddress(firestation.getAddress());
				List<Person> persons = listObjects.getPersons().stream()
						.filter(str -> str.getAddress().equals(firestation.getAddress())).collect(Collectors.toList());
				for (Person person : persons) {
					JMapper<FireAddressInhabitant, Person> userMapperPerson = new JMapper<>(FireAddressInhabitant.class,
							Person.class);
					FireAddressInhabitant resultPerson = userMapperPerson.getDestination(person);
					resultPerson.setMedications(person.getMedicalrecord().getMedications());
					LocalDate bitrthdate = person.getMedicalrecord().getBirthdate();
					resultPerson.setAge(calculateAge.ageCalculation(bitrthdate));
					flood.getInhabitant().add(resultPerson);
				}
				floodList.add(flood);
			}
		}
		return floodList;
	}

	public List<PersonInfo> personInfoFirstName(String firstName) {
		List<PersonInfo> multipleFirstName = new ArrayList<>();
		List<Person> persons = listObjects.getPersons().stream().filter(str -> str.getFirstName().equals(firstName))
				.collect(Collectors.toList());
		for (Person person : persons) {
			JMapper<PersonInfo, Person> userMapperPerson = new JMapper<>(PersonInfo.class, Person.class);
			PersonInfo resultPerson = userMapperPerson.getDestination(person);
			resultPerson.setMedication(person.getMedicalrecord().getMedications());
			resultPerson.setAge(calculateAge.ageCalculation(person.getMedicalrecord().getBirthdate()));
			multipleFirstName.add(resultPerson);
		}

		return multipleFirstName;
	}

	public List<PersonInfo> personInfoLastName(String lastName) {
		List<PersonInfo> multipleLastName = new ArrayList<>();
		List<Person> persons = listObjects.getPersons().stream().filter(str -> str.getLastName().equals(lastName))
				.collect(Collectors.toList());
		for (Person person : persons) {
			JMapper<PersonInfo, Person> userMapperPerson = new JMapper<>(PersonInfo.class, Person.class);
			PersonInfo resultPerson = userMapperPerson.getDestination(person);
			resultPerson.setMedication(person.getMedicalrecord().getMedications());
			resultPerson.setAge(calculateAge.ageCalculation(person.getMedicalrecord().getBirthdate()));
			multipleLastName.add(resultPerson);
		}
		return multipleLastName;
	}

	public List<PersonInfo> personInfo(String firstName, String lastName) {
		List<PersonInfo> allInfo = new ArrayList<>();
		PersonInfo resultPerson;
		List<Person> persons = listObjects.getPersons().stream()
				.filter(str -> str.getFirstName().equals(firstName) && str.getLastName().equals(lastName))
				.collect(Collectors.toList());
		for (Person person : persons) {
			JMapper<PersonInfo, Person> userMapperPerson = new JMapper<>(PersonInfo.class, Person.class);
			resultPerson = userMapperPerson.getDestination(person);
			resultPerson.setMedication(person.getMedicalrecord().getMedications());
			resultPerson.setAge(calculateAge.ageCalculation(person.getMedicalrecord().getBirthdate()));
			allInfo.add(resultPerson);
		}
		return allInfo;
	}

	public CommunityEmail communityEmail(String city){
		CommunityEmail communityEmail = new CommunityEmail();
		List<Person> persons = listObjects.getPersons().stream()
				.filter(str -> str.getCity().equals(city))
				.collect(Collectors.toList());
		for (Person person : persons) {
			communityEmail.getEmail().add(person.getEmail());
		}
		return communityEmail;
		
	}
}
