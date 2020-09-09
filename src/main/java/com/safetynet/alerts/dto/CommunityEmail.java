package com.safetynet.alerts.dto;

import java.util.ArrayList;
import java.util.List;

public class CommunityEmail {
	
	
	private List<String> email = new ArrayList<>();

	/**
	 * 
	 */
	public CommunityEmail() {
	}

	/**
	 * @param email
	 */
	public CommunityEmail(List<String> email) {
		super();
		this.email = email;
	}

	/**
	 * @return the email
	 */
	public List<String> getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(List<String> email) {
		this.email = email;
	}
	
	
}
