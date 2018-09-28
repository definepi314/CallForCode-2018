package com.capgemini.callforcode.reusable.transaction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PERSON_CHECKLIST")
public class PersonChecklistDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERSON_CHECKLIST_ID")
	private long personChecklistId;

	@ManyToOne
	@JoinColumn(name = "PERSON_ID")
	@JsonIgnore
	private PersonMasterEntity personMasterEntity;

	@ManyToOne
	@JoinColumn(name = "CHECKLIST_DETAIL_ID")
	@JsonIgnore
	private CheckListDetailsEntity checkListDetailsEntity;
	
	@Column(name = "CHECKLIST_VALUE")
	private String checkListValue;


	public long getPersonChecklistId() {
		return personChecklistId;
	}

	public void setPersonChecklistId(long personChecklistId) {
		this.personChecklistId = personChecklistId;
	}

	public PersonMasterEntity getPersonMasterEntity() {
		return personMasterEntity;
	}

	public void setPersonMasterEntity(PersonMasterEntity personMasterEntity) {
		this.personMasterEntity = personMasterEntity;
	}

	public CheckListDetailsEntity getCheckListDetailsEntity() {
		return checkListDetailsEntity;
	}

	public void setCheckListDetailsEntity(CheckListDetailsEntity checkListDetailsEntity) {
		this.checkListDetailsEntity = checkListDetailsEntity;
	}

	
	public String getCheckListValue() {
		return checkListValue;
	}

	public void setCheckListValue(String checkListValue) {
		this.checkListValue = checkListValue;
	}

	@Override
	public String toString() {
		return "PersonChecklistDetailsEntity [personChecklistId=" + personChecklistId + ", personMasterEntity="
				+ personMasterEntity + ", checkListDetailsEntity=" + checkListDetailsEntity + ", checkListValue="
				+ checkListValue + "]";
	}

	
	

}
