package com.safetynet.alerts.readJson;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jmapper.JMapper;
import com.safetynet.alerts.dto.FirestationDto;
import com.safetynet.alerts.dto.MedicalrecordDto;
import com.safetynet.alerts.dto.PersonDto;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.ListObjects;
import com.safetynet.alerts.model.Medicalrecord;
import com.safetynet.alerts.model.Person;

@Component
public class JsonReaderImpl {

	private static final Logger logger = LogManager.getLogger("JsonReader");

	@Autowired
	private ListObjects listObjects;
	public final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

	@PostConstruct
	public void readerList() {
		JsonNode mainJson = null;
		ObjectMapper mapper = new ObjectMapper();
		File jsonFile = new File("data.json");
		try {
			mainJson = mapper.readTree(jsonFile);
		} catch (JsonProcessingException e) {
			logger.error("Cannot process the Json File", e);
		} catch (IOException e) {
			logger.error("Cannot read the Json File", e);
		}
		readPersonsJson(mainJson);
		readFirestationJson(mainJson);
		readMedicalrecordsJson(mainJson);
		associateDataToPerson();

		System.out.println("Database has been configure");
	}

	private void readPersonsJson(JsonNode mainJson) {
		JsonNode persons = mainJson.at("/persons");
		for (JsonNode node : persons) {
			Person person = new Person();
			person.setFirstName(node.get("firstName").asText());
			person.setLastName(node.get("lastName").asText());
			person.setAddress(node.get("address").asText());
			person.setCity(node.get("city").asText());
			person.setZip(node.get("zip").asText());
			person.setPhone(node.get("phone").asText());
			person.setEmail(node.get("email").asText());
			listObjects.getPersons().add(person);
		}
	}

	private void readFirestationJson(JsonNode mainJson) {
		JsonNode firestations = mainJson.at("/firestations");
		for (JsonNode node : firestations) {
			Firestation firestation = new Firestation();
			firestation.setAddress(node.get("address").asText());
			firestation.setStation(node.get("station").asText());
			listObjects.getFirestations().add(firestation);
		}
	}

	private void readMedicalrecordsJson(JsonNode mainJson) {
		JsonNode medicalrecords = mainJson.at("/medicalrecords");
		for (JsonNode node : medicalrecords) {
			Medicalrecord medicalrecord = new Medicalrecord();
			medicalrecord.setFirstName(node.get("firstName").asText());
			medicalrecord.setLastName(node.get("lastName").asText());
			// TODO check the date format to change to localdate
			try {
				medicalrecord.setBirthdate(formatter.parse(node.get("birthdate").asText()));
			} catch (ParseException e) {
				logger.error("Enable to read the date format", e);
			}
			JsonNode medications = node.at("/medications");
			for (JsonNode nodem : medications) {
				medicalrecord.getMedications().add(nodem.textValue());
			}
			JsonNode allerg = node.at("/allergies");
			for (JsonNode nodea : allerg) {
				medicalrecord.getAllergies().add(nodea.textValue());
			}
			listObjects.getMedicalrecords().add(medicalrecord);
		}
	}

	private void associateDataToPerson() {
		List<Person> persons = listObjects.getPersons();
		List<Firestation> firestations = listObjects.getFirestations();
		List<Medicalrecord> medicalrecords = listObjects.getMedicalrecords();
		// TODO Error from here, check why it appears
		JMapper<PersonDto, Person> UserMapperPerson = new JMapper<>(PersonDto.class, Person.class);
		JMapper<MedicalrecordDto, Medicalrecord> userMapperMedicalrecord = new JMapper<>(MedicalrecordDto.class,
				Medicalrecord.class);
		JMapper<FirestationDto, Firestation> userMapperFirestation = new JMapper<>(FirestationDto.class, Firestation.class);
		for (Firestation firestation : firestations) {
			FirestationDto resultFirestation = userMapperFirestation.getDestination(firestation);
			for (Person person : persons) {
				PersonDto resultPerson = UserMapperPerson.getDestination(person);
				if (person.getAddress().equals(firestation.getAddress())) {
					person.setFirestation(resultFirestation);
					persons.set(persons.indexOf(person), person);
					firestation.getPerson().add(resultPerson);
				}
				for (Medicalrecord medicalrecord : medicalrecords) {
					MedicalrecordDto resultMedicalrecord = userMapperMedicalrecord.getDestination(medicalrecord);
					if ((person.getFirstName().equals(medicalrecord.getFirstName()))
							&& (person.getLastName().equals(medicalrecord.getLastName()))) {
						medicalrecord.setPerson(resultPerson);
						medicalrecords.set(medicalrecords.indexOf(medicalrecord), medicalrecord);
						person.setMedicalrecord(resultMedicalrecord);
						persons.set(persons.indexOf(person), person);
						break;
					}
				}
			}
		}
	}
}
