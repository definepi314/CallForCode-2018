package com.capgemini.callforcode.dtos;

import java.util.List;

public class MultipleChecklistDTO {

	private long disasterId;
	private String checkList;
	private String description;
	private List<String> subchecklist;

	public long getDisasterId() {
		return disasterId;
	}

	public void setDisasterId(long disasterId) {
		this.disasterId = disasterId;
	}

	public String getCheckList() {
		return checkList;
	}

	public void setCheckList(String checkList) {
		this.checkList = checkList;
	}

	public List<String> getSubchecklist() {
		return subchecklist;
	}

	public void setSubchecklist(List<String> subchecklist) {
		this.subchecklist = subchecklist;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
