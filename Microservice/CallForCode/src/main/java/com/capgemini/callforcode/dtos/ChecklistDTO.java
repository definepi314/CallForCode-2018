package com.capgemini.callforcode.dtos;
import java.util.List;

public class ChecklistDTO {

	private long disasterId;
	private List<TutorialVideoDTO> checklists;

	public long getDisasterId() {
		return disasterId;
	}

	public void setDisasterId(long disasterId) {
		this.disasterId = disasterId;
	}

	public List<TutorialVideoDTO> getChecklists() {
		return checklists;
	}

	public void setChecklists(List<TutorialVideoDTO> checklists) {
		this.checklists = checklists;
	}

	@Override
	public String toString() {
		return "ChecklistDTO [disasterId=" + disasterId + ", checklists=" + checklists + "]";
	}

	
	
	

//	public List<String> getChecklists() {
//		return checklists;
//	}
//
//	public void setChecklists(List<String> checklists) {
//		this.checklists = checklists;
//	}

//	@Override
//	public String toString() {
//		return "ChecklistDTO [disasterId=" + disasterId + ", checklists=" + checklists + ", getDisasterId()="
//				+ getDisasterId() + ", getChecklists()=" + getChecklists() + "]";
//	}
//	
	

}
