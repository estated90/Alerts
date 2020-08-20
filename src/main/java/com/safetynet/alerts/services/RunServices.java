package com.safetynet.alerts.services;

import com.safetynet.alerts.interfaces.IJsonReader;
import com.safetynet.alerts.interfaces.IRunServices;

public class RunServices implements IRunServices{
	
	//Instances declaration
	IJsonReader parseJsonFile = 	null;

	public RunServices (String filePath, IJsonReader parseFile) {
		this.parseJsonFile = parseFile;
	}

	public RunServices() {
		this.parseJsonFile = null;
	}
	
	public void jsonParserMicroservices() {
		
	}


	
}
