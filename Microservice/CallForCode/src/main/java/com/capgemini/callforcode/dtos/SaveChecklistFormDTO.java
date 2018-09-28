package com.capgemini.callforcode.dtos;
import java.util.List;

public class SaveChecklistFormDTO {
	private long checkListId;
	private String userName;
	private List<SubChecklistFilledDTO> subCheckLists;
	public long getCheckListId() {
		return checkListId;
	}
	public void setCheckListId(long checkListId) {
		this.checkListId = checkListId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<SubChecklistFilledDTO> getSubCheckLists() {
		return subCheckLists;
	}
	public void setSubCheckLists(List<SubChecklistFilledDTO> subCheckLists) {
		this.subCheckLists = subCheckLists;
	}
	
	
	
	
//	private List<Long> subchecklistId;
//	private List<String> subchecklistIValues;
//
//	public long getCheckListId() {
//		return checkListId;
//	}
//
//	public void setCheckListId(long checkListId) {
//		this.checkListId = checkListId;
//	}
//
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public List<Long> getSubchecklistId() {
//		return subchecklistId;
//	}
//
//	public void setSubchecklistId(List<Long> subchecklistId) {
//		this.subchecklistId = subchecklistId;
//	}
//
//	public List<String> getSubchecklistIValues() {
//		return subchecklistIValues;
//	}
//
//	public void setSubchecklistIValues(List<String> subchecklistIValues) {
//		this.subchecklistIValues = subchecklistIValues;
//	}
//	
	

}
