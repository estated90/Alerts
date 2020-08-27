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
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.ListObjects;
import com.safetynet.alerts.model.Medicalrecord;
import com.safetynet.alerts.model.Persons;

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
		associateFirestation();
		associateMedicalRecords();
		System.out.println(listObjects.getPersons());
		System.out.println("Database has been configure");
	}

	private void readPersonsJson(JsonNode mainJson) {
		JsonNode persons = mainJson.at("/persons");
		for (JsonNode node : persons) {
			Persons person = new Persons();
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

	private void associateFirestation() {
		List<Persons> persons = listObjects.getPersons();
		List<Firestation> firestations = listObjects.getFirestations();
		
		for (Persons person : persons) {
			for (Firestation firestation : firestations){
				if (person.getAddress() == firestation.getAddress()) {
					person.setStation(firestation.getStation());
				}
			}
		}
	}

	private void associateMedicalRecords() {
		List<Persons> persons = listObjects.getPersons();
		List<Medicalrecord> medicalrecords = listObjects.getMedicalrecords();

		for (Persons person : persons) {
			for (Medicalrecord medicalrecord : medicalrecords) {
				if ((person.getFirstName() == medicalrecord.getFirstName()) && (person.getLastName() == medicalrecord.getLastName())) {
					person.setMedications(medicalrecord.getMedications());
					person.setAllergies(medicalrecord.getAllergies());
				}
			}
		}
	}
}
