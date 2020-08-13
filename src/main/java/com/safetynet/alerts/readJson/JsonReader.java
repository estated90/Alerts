package com.safetynet.alerts.readJson;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.interfaces.IJsonReader;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.ListObjects;
import com.safetynet.alerts.model.Medicalrecord;
import com.safetynet.alerts.model.Persons;

@Component
public class JsonReader implements IJsonReader {

	private static final Logger logger = LogManager.getLogger("JsonReader");

	@Autowired
	private ListObjects listObjects = new ListObjects();
	public final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

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
			firestation.setAddress(node.get("station").asText());
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
}
