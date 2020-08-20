package com.safetynet.alerts;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertsApplication {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		SpringApplication.run(AlertsApplication.class, args);

	}
}
