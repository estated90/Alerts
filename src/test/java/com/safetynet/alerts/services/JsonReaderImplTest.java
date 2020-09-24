package com.safetynet.alerts.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.AlertsApplication;
import com.safetynet.alerts.model.ListObjects;

@RunWith(SpringRunner.class)
@SpringBatchTest
@EnableAutoConfiguration
@ContextConfiguration(classes = { AlertsApplication.class })
class JsonReaderImplTest {

	@Autowired
	private ListObjects listObjects;
	private static JsonReaderImpl jsonReaderImpl;

	@AfterEach
	void tearDown() throws Exception {
	}

	@BeforeAll
	static void startUp() throws JSONException, IOException {
		
		BufferedWriter writer = Files.newBufferedWriter(Paths.get("test.json"));

		Map<String, Object> persons = new HashMap<>();
		persons.put("firstName", "Allison");
		persons.put("lastName", "Boyd");
		persons.put("address", "Testing address");
		persons.put("city", "Somewhere");
		persons.put("zip", "69122");
		persons.put("phone", "0684949494");
		persons.put("email", "julientest@somewhere.com");
		
		Map<String, Object> firestations = new HashMap<>();
		firestations.put("address", "Testing address");
		firestations.put("station", "7");
		
		Map<String, Object> medicalrecords = new HashMap<>();
		medicalrecords.put("firstName", "Allison");
		medicalrecords.put("lastName", "Boyd");
		medicalrecords.put("birthdate", "30/09/1990");
		medicalrecords.put("medications", Arrays.asList("aznol:350mg", "hydrapermazol:100mg"));
		medicalrecords.put("allergies", Arrays.asList("aznol:350mg", "nillacilan"));
		
		Map<String, Object> toWrite = new HashMap<>();
		toWrite.put("persons", Arrays.asList(persons));
		toWrite.put("firestations", Arrays.asList(firestations));
		toWrite.put("medicalrecords", Arrays.asList(medicalrecords));
		
		ObjectMapper mapper = new ObjectMapper();
		writer.write(mapper.writeValueAsString(toWrite));
		
		writer.close();
	}

	@BeforeEach
	void setUp() {
		jsonReaderImpl = new JsonReaderImpl();
		this.listObjects = new ListObjects();
	}

	@Test
	void provide_bad_Json() {
		File jsonFile = new File("test.json");
		jsonReaderImpl.setJsonFile(jsonFile);
		jsonReaderImpl.readerList();
		assertEquals(LocalDate.of(1990, 9, 30), listObjects.getPersons().get(0).getMedicalrecord().getBirthdate());
	}
}
