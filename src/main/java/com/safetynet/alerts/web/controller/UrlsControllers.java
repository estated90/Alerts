package com.safetynet.alerts.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.safetynet.alerts.dto.ChildAlert;
import com.safetynet.alerts.dto.CommunityEmail;
import com.safetynet.alerts.dto.FireAddress;
import com.safetynet.alerts.dto.Flood;
import com.safetynet.alerts.dto.PersonInfo;
import com.safetynet.alerts.dto.CoverageDto;
import com.safetynet.alerts.dto.PhoneAlert;
import com.safetynet.alerts.interfaces.IMicroservicesServices;

@RestController
public class UrlsControllers {

	private static final Logger logger = LogManager.getLogger("UrlsControllers");
	
	@Autowired
	private IMicroservicesServices microservicesServices;

	/**
	 * 
	 * @param station number
	 * @return ResponseEntity error or success
	 */
	@GetMapping(value = "firestation", params = "stationNumber", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CoverageDto> coverageFirestation(@RequestParam("stationNumber") int station) {
		logger.info("get coverage for firestation {}", station);
		CoverageDto coverage = microservicesServices.firestationListPerson(station);
		if (coverage == null) {
			logger.info("The station was not found");
			return ResponseEntity.noContent().build();
		}
		logger.info("Coverage of station {} is {}", station, coverage);
		return ResponseEntity.ok().body(coverage);
	}
	/**
	 * 
	 * @param address as string
	 * @return ResponseEntity error or success
	 */
	@GetMapping(value = "childAlert", params = "address")
	public ResponseEntity<ChildAlert> returnChildAlert(@RequestParam("address") String address){
		logger.info("get childAlert for address {}", address);
		ChildAlert childAlert = microservicesServices.childrenAlerts(address);
		logger.info("ChildAlert for address {} is : ", address, childAlert);
		return ResponseEntity.ok().body(childAlert);
	}
	/**
	 * 
	 * @param firestation number
	 * @return ResponseEntity error or success
	 */
	@GetMapping(value = "phoneAlert", params = "firestation")
	public ResponseEntity<Object> phoneAlert(@RequestParam("firestation") int firestation) {
		logger.info("get PhoneAlert for firestation {}", firestation);
		PhoneAlert phoneAlert = microservicesServices.phoneAlert(firestation);
		if (phoneAlert == null) {
			logger.info("The firestation was not found");
			return ResponseEntity.noContent().build();
		}
		logger.info("PhoneAlert of station {} is {}", firestation, phoneAlert);
		return ResponseEntity.ok().body(phoneAlert);
	}
	/**
	 * 
	 * @param address as string
	 * @return ResponseEntity error or success
	 */
	@GetMapping(value = "fire", params = "address")
	public ResponseEntity<Object> fire(@RequestParam("address") String address){
		logger.info("get FireAddress for address {}", address);
		FireAddress fireAddress = microservicesServices.fireAdress(address);
		if (fireAddress == null) {
			logger.info("The address was not found");
			return ResponseEntity.noContent().build();
		}
		logger.info("PhoneAlert of address {} is {}", address, fireAddress);
		return ResponseEntity.ok().body(fireAddress);
	}
	/**
	 * 
	 * @param stations as int or list of int
	 * @return ResponseEntity error or success
	 */
	@GetMapping(value = "flood/stations", params = "stations")
	public ResponseEntity<Object> flood(@RequestParam("stations") List<Integer> stations) {
		logger.info("get flood for stations {}", stations);
		List<Flood> flood = microservicesServices.floodStation(stations);
		if (flood == null) {
			logger.info("The station was not found");
			return ResponseEntity.noContent().build();
		}
		logger.info("Flood of stations {} is {}", stations, flood);
		return ResponseEntity.ok().body(flood);
	}
	/**
	 * 
	 * @param firstName to look for
	 * @param lastName to look for
	 * @return ResponseEntity error or success
	 */
	@GetMapping(value = "personInfo", params = { "firstName", "lastName" })
	public ResponseEntity<Object> personInfoAll(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName){
		logger.info("get PersonInfo for first name {} and last name {}", firstName, lastName);
		List<PersonInfo> personInfo = microservicesServices.personInfo(firstName, lastName);
		if (personInfo == null) {
			logger.info("The person was not found");
			return ResponseEntity.noContent().build();
		}
		logger.info("PersonInfo of firstName {} and last name {} is {}", firstName, lastName, personInfo);
		return ResponseEntity.ok().body(personInfo);
	}
	/**
	 * 
	 * @param city to look for
	 * @return ResponseEntity error or success
	 */
	@GetMapping(value = "communityEmail", params = "city")
	public ResponseEntity<Object> communityEmail(@RequestParam("city") String city) {
		logger.info("get communityEmail for city {}", city);
		CommunityEmail communityEmail = microservicesServices.communityEmail(city);
		if (communityEmail == null) {
			logger.info("The station was not found");
			return ResponseEntity.noContent().build();
		}
		logger.info("communityEmail of city {} is {}", city, communityEmail);
		return ResponseEntity.ok().body(communityEmail);
	}
	
	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
	    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
	    loggingFilter.setIncludeClientInfo(true);
	    loggingFilter.setIncludeQueryString(true);
	    loggingFilter.setIncludePayload(true);
	    loggingFilter.setIncludeHeaders(false);
	    return loggingFilter;
	}
}
