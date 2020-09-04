package com.safetynet.alerts.services;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
			readPersonsJson(mainJson);
			readFirestationJson(mainJson);
			readMedicalrecordsJson(mainJson);
			associateDataToPerson();
		} catch (JsonProcessingException e) {
			logger.error("Cannot process the Json File", e);
		} catch (IOException e) {
			logger.error("Cannot read the Json File", e);
		}

		logger.info("Database has been configure");
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
		logger.info("Creation of the data person %b",listObjects.getPersons());
	}

	private void readFirestationJson(JsonNode mainJson) {
		JsonNode firestations = mainJson.at("/firestations");
		for (JsonNode node : firestations) {
			Firestation firestation = new Firestation();
			firestation.setAddress(node.get("address").asText());
			firestation.setStation(node.get("station").asInt());
			listObjects.getFirestations().add(firestation);
		}
		logger.info("Creation of the data firestation %d",listObjects.getFirestations());
	}

	private void readMedicalrecordsJson(JsonNode mainJson) {
		JsonNode medicalrecords = mainJson.at("/medicalrecords");
		for (JsonNode node : medicalrecords) {
			Medicalrecord medicalrecord = new Medicalrecord();
			medicalrecord.setFirstName(node.get("firstName").asText());
			medicalrecord.setLastName(node.get("lastName").asText());
			try {
				Date date = formatter.parse(node.get("birthdate").asText());
				LocalDate birthdate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				medicalrecord.setBirthdate(birthdate);
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
		logger.info("Creation of the data medicalrecord %d",listObjects.getMedicalrecords());
	}

	private void associateDataToPerson() {
		List<Person> persons = listObjects.getPersons();
		List<Firestation> firestations = listObjects.getFirestations();
		List<Medicalrecord> medicalrecords = listObjects.getMedicalrecords();
		for (Firestation firestation : firestations) {
			for (Person person : persons) {
				if (person.getAddress().equals(firestation.getAddress())) {
					person.setFirestation(firestation);
					persons.set(persons.indexOf(person), person);
					firestation.getPerson().add(person);
				}
				for (Medicalrecord medicalrecord : medicalrecords) {
					if ((person.getFirstName().equals(medicalrecord.getFirstName()))
							&& (person.getLastName().equals(medicalrecord.getLastName()))) {
						medicalrecord.setPerson(person);
						medicalrecords.set(medicalrecords.indexOf(medicalrecord), medicalrecord);
						person.setMedicalrecord(medicalrecord);
						persons.set(persons.indexOf(person), person);
						break;
					}
				}
			}
		}
		logger.info("Deep link created between:");
	}
}
