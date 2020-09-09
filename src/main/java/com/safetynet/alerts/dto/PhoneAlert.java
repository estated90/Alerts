package com.safetynet.alerts.dto;

import java.util.ArrayList;
import java.util.List;

public class PhoneAlert {

	private List<String> phoneNumber = new ArrayList<>();

	/**
	 * 
	 */
	public PhoneAlert() {
		super();
	}

	/**
	 * @param phoneNumber
	 */
	public PhoneAlert(List<String> phoneNumber) {
		super();
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the phoneNumber
	 */
	public List<String> getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(List<String> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
