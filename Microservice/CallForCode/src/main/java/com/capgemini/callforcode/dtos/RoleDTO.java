package com.capgemini.callforcode.dtos;

import java.util.List;

public class RoleDTO {

	private List<String> roleName;
	private Boolean loginStatus;
	private PersonDTO personDto;

	public List<String> getRoleName() {
		return roleName;
	}

	public void setRoleName(List<String> roleName) {
		this.roleName = roleName;
	}

	public Boolean getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	public PersonDTO getPersonDto() {
		return personDto;
	}

	public void setPersonDto(PersonDTO personDto) {
		this.personDto = personDto;
	}

}
