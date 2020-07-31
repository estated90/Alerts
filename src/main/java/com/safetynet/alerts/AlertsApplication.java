package com.safetynet.alerts;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.alerts.readJson.JsonReader;

@SpringBootApplication
public class AlertsApplication {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		SpringApplication.run(AlertsApplication.class, args);
		JsonReader jsonReader = new JsonReader();
		jsonReader.readerPersonList();
	}
}
