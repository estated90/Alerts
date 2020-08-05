package com.safetynet.alerts.readJson;

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
		Iterator<Map.Entry> itr2 = jsonArray.iterator();
		while (itr2.hasNext()) {
			Iterator<Map.Entry> itr1 = ((Map) itr2.next()).entrySet().iterator();
			while (itr1.hasNext()) {
				Map.Entry pair = itr1.next();
				System.out.println(pair.getKey() + " : " + pair.getValue());
			}
		}
	}
	
	public void readerSymptomsList(String filePath) {
		// parsing file "JSONExample.json"
		Object jsonFile = null;
		try {
			jsonFile = new JSONParser().parse(new FileReader("data.json"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// type casting jsonFile to JSONObject
		JSONObject jsonObject = (JSONObject) jsonFile;
		JSONArray jsonArray = (JSONArray) jsonObject.get("medicalrecords");
		Iterator<Map.Entry> itr2 = jsonArray.iterator();
		while (itr2.hasNext()) {
			Iterator<Map.Entry> itr1 = ((Map) itr2.next()).entrySet().iterator();
			while (itr1.hasNext()) {
				Map.Entry pair = itr1.next();
				System.out.println(pair.getKey() + " : " + pair.getValue());
			}
		}
	}
	
	public void readerFirestationList(String filePath) {
		// parsing file "JSONExample.json"
		Object jsonFile = null;
		try {
			jsonFile = new JSONParser().parse(new FileReader("data.json"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// type casting jsonFile to JSONObject
		JSONObject jsonObject = (JSONObject) jsonFile;
		JSONArray jsonArray = (JSONArray) jsonObject.get("firestations");
		Iterator<Map.Entry> itr2 = jsonArray.iterator();
		while (itr2.hasNext()) {
			Iterator<Map.Entry> itr1 = ((Map) itr2.next()).entrySet().iterator();
			while (itr1.hasNext()) {
				Map.Entry pair = itr1.next();
				System.out.println(pair.getKey() + " : " + pair.getValue());
			}
		}
	}
}
