package com.capgemini.callforcode.dtos;

import java.util.List;

public class ListDTO {

	private String checklist;
	private String description;
	//private List<Long> subCheckListid;
	//private List<String> subchecklist;
	private List<SubChecklistFilledDTO> filled;

	public String getChecklist() {
		return checklist;
	}

	public void setChecklist(String checklist) {
		this.checklist = checklist;
	}

//	public List<String> getSubchecklist() {
//		return subchecklist;
//	}
//
//	public void setSubchecklist(List<String> subchecklist) {
//		this.subchecklist = subchecklist;
//	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public List<SubChecklistFilledDTO> getFilled() {
		return filled;
	}

	public void setFilled(List<SubChecklistFilledDTO> filled) {
		this.filled = filled;
	}

//	public List<Long> getSubCheckListid() {
//		return subCheckListid;
//	}
//
//	public void setSubCheckListid(List<Long> subCheckListid) {
//		this.subCheckListid = subCheckListid;
//	}
//	
	

}
