package com.capgemini.callforcode.dtos;

public class SubChecklistFilledDTO {
	
	private Long subCheckListId;
	private String name;
	private String value;
	public Long getSubCheckListId() {
		return subCheckListId;
	}
	public void setSubCheckListId(Long subCheckListId) {
		this.subCheckListId = subCheckListId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
