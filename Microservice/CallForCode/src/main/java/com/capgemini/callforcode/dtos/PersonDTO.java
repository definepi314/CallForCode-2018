package com.capgemini.callforcode.dtos;

import java.util.List;

public class PersonDTO {

	private String firstName;
	private String lastName;
	private String middleName;
	private String emailId;
	private Boolean isPhysicallyChallenged;
	private long mobile;
	private List<AddressDTO> addressDTOs;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Boolean getIsPhysicallyChallenged() {
		return isPhysicallyChallenged;
	}

	public void setIsPhysicallyChallenged(Boolean isPhysicallyChallenged) {
		this.isPhysicallyChallenged = isPhysicallyChallenged;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public List<AddressDTO> getAddressDTOs() {
		return addressDTOs;
	}

	public void setAddressDTOs(List<AddressDTO> addressDTOs) {
		this.addressDTOs = addressDTOs;
	}

}
