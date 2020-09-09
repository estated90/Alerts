package com.safetynet.alerts.dto;

import java.util.List;

public class ChildAlert {

	/**
	 * 
	 */
	private List<ChildList> child;
	private List<AdultList> familyMember;
	
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
