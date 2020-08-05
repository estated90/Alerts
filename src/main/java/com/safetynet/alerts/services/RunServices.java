package com.safetynet.alerts.services;

import com.safetynet.alerts.readJson.IJsonReader;

public class RunServices implements IRunServices{
	
	// Stock the path of the file
	private String 					filePath;
	
	//Instances declaration
	IJsonReader parseJsonFile = 	null;

	public RunServices (String filePath, IJsonReader parseFile) {
		this.filePath = filePath;
		this.parseJsonFile = parseFile;
	}

	public RunServices() {
		this.filePath = null;
		this.parseJsonFile = null;
	}
	
	public void jsonParserMicroservices() {
		parseJsonFile.readerPersonList(filePath);
		parseJsonFile.readerFirestationList(filePath);
		parseJsonFile.readerSymptomsList(filePath);
	}


	
}
