package com.safetynet.alerts.readJson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader implements IJsonReader {

	public void readerPersonList(String filePath) {
		// parsing file "JSONExample.json"
		Object jsonFile = null;
		try {
			jsonFile = new JSONParser().parse(new FileReader(filePath));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// type casting jsonFile to JSONObject
		JSONObject jsonObject = (JSONObject) jsonFile;
		JSONArray jsonArray = (JSONArray) jsonObject.get("persons");
		Iterator<?> itr2 = jsonArray.iterator();
		while (itr2.hasNext()) {
			Iterator<Map.Entry> itr1 = ((Map) itr2.next()).entrySet().iterator();
			while (itr1.hasNext()) {
				Map.Entry pair = itr1.next();
				System.out.println(pair.getKey() + " : " + pair.getValue());
			}
		}
	}
	
	public void readerSymptomsListe() throws FileNotFoundException, IOException, ParseException {
		// parsing file "JSONExample.json"
		Object jsonFile = new JSONParser().parse(new FileReader("data.json"));

		// type casting jsonFile to JSONObject
		JSONObject jsonObject = (JSONObject) jsonFile;
		JSONArray jsonArray = (JSONArray) jsonObject.get("firestations");
		Iterator<?> itr2 = jsonArray.iterator();
		while (itr2.hasNext()) {
			Iterator<Map.Entry> itr1 = ((Map) itr2.next()).entrySet().iterator();
			while (itr1.hasNext()) {
				Map.Entry pair = itr1.next();
				System.out.println(pair.getKey() + " : " + pair.getValue());
			}
		}
	}
	
	public void readerFirestationListe() throws FileNotFoundException, IOException, ParseException {
		// parsing file "JSONExample.json"
		Object jsonFile = new JSONParser().parse(new FileReader("data.json"));

		// type casting jsonFile to JSONObject
		JSONObject jsonObject = (JSONObject) jsonFile;
		JSONArray jsonArray = (JSONArray) jsonObject.get("medicalrecords");
		Iterator<?> itr2 = jsonArray.iterator();
		while (itr2.hasNext()) {
			Iterator<Map.Entry> itr1 = ((Map) itr2.next()).entrySet().iterator();
			while (itr1.hasNext()) {
				Map.Entry pair = itr1.next();
				System.out.println(pair.getKey() + " : " + pair.getValue());
			}
		}
	}
}
