package com.safetynet.alerts.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping(value = "firestation", params = "stationNumber")
	public CoverageDto coverageFirestation(@RequestParam("stationNumber") int station) {
		logger.info("get coverage for firestation {}", station);
		return microservicesServices.firestationListPerson(station);
	}

	@GetMapping(value = "childAlert", params = "address")
	public ChildAlert returnChildAlert(@RequestParam("address") String address){
		return microservicesServices.childrenAlerts(address);
	}

	@GetMapping(value = "phoneAlert", params = "firestation")
	public PhoneAlert phoneAlert(@RequestParam("firestation") int firestation) {
		return microservicesServices.phoneAlert(firestation);
	}

	@GetMapping(value = "fire", params = "address")
	public FireAddress fire(@RequestParam("address") String address){
		return microservicesServices.fireAdress(address);
	}

	@GetMapping(value = "flood/stations", params = "stations")
	public List<Flood> flood(@RequestParam("stations") List<Integer> stations) {
		return microservicesServices.floodStation(stations);
	}

	@GetMapping(value = "personInfo", params = "firstName")
	public List<PersonInfo> personInfoFirstName(@RequestParam("firstName") String firstName){
		return microservicesServices.personInfoFirstName(firstName);
	}

	@GetMapping(value = "personInfo", params = "lastName")
	public List<PersonInfo> personInfoLastName(@RequestParam("lastName") String lastName) {
		return microservicesServices.personInfoLastName(lastName);
	}

	@GetMapping(value = "personInfo", params = { "firstName", "lastName" })
	public List<PersonInfo> personInfoAll(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName){
		return microservicesServices.personInfo(firstName, lastName);
	}

	@GetMapping(value = "communityEmail", params = "city")
	public CommunityEmail communityEmail(@RequestParam("city") String city) {
		return microservicesServices.communityEmail(city);
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
