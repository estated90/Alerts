package com.safetynet.alerts.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.googlecode.jmapper.annotations.JGlobalMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JGlobalMap
public class ChildAlert {

	/**
	 * 
	 */
	public List<ChildList> child;
	public List<AdultList> familyMember;
	
	public ChildAlert() {
	}

	/**
	 * @param child
	 * @param familyMember
	 */
	public ChildAlert(List<ChildList> child, List<AdultList> familyMember) {
		super();
		this.child = child;
		this.familyMember = familyMember;
	}

	/**
	 * @return the child
	 */
	public List<ChildList> getChild() {
		return child;
	}

	/**
	 * @param child the child to set
	 */
	public void setChild(List<ChildList> child) {
		this.child = child;
	}

	/**
	 * @return the familyMember
	 */
	public List<AdultList> getFamilyMember() {
		return familyMember;
	}

	/**
	 * @param familyMember the familyMember to set
	 */
	public void setFamilyMember(List<AdultList> familyMember) {
		this.familyMember = familyMember;
	}

	
	
}
