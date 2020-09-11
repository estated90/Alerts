package com.safetynet.alerts.services;

import java.util.List;

import com.safetynet.alerts.dto.ChildAlert;
import com.safetynet.alerts.dto.CommunityEmail;
import com.safetynet.alerts.dto.CoverageDto;
import com.safetynet.alerts.dto.FireAddress;
import com.safetynet.alerts.dto.Flood;
import com.safetynet.alerts.dto.PersonInfo;
import com.safetynet.alerts.dto.PhoneAlert;

public interface IMicroservicesServices {

	public CoverageDto firestationListPerson(int station);

	public ChildAlert childrenAlerts(String address);

	public PhoneAlert phoneAlert(int station);

	public FireAddress fireAdress(String address);

	public List<Flood> floodStation(List<Integer> stations);

	public List<PersonInfo> personInfoFirstName(String firstName);

	public List<PersonInfo> personInfoLastName(String lastName);

	public List<PersonInfo> personInfo(String firstName, String lastName);

	public CommunityEmail communityEmail(String city);

}