package com.capgemini.callforcode.reusable.transaction.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CHECKLIST_DETAILS")
public class CheckListDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CHECKLIST_DETAIL_ID")
	private long checklistDetailId;

	@Column(name = "SUB_CHECKLIST")
	private String subChecklist;
	
	//changing
	
//	@Column(name = "FILLED")
//	private String filled;
	
	@OneToMany(mappedBy = "checkListDetailsEntity", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PersonChecklistDetailsEntity> personChecklistDetailsEntities;

	@ManyToOne
	@JoinColumn(name = "CHECKLIST_ID")
	@JsonIgnore
	private CheckListEntity checkListEntity;

	public long getChecklistDetailId() {
		return checklistDetailId;
	}

	public void setChecklistDetailId(long checklistDetailId) {
		this.checklistDetailId = checklistDetailId;
	}

	public String getSubChecklist() {
		return subChecklist;
	}

	public void setSubChecklist(String subChecklist) {
		this.subChecklist = subChecklist;
	}

	public CheckListEntity getCheckListEntity() {
		return checkListEntity;
	}

	public void setCheckListEntity(CheckListEntity checkListEntity) {
		this.checkListEntity = checkListEntity;
	}

//	public String getFilled() {
//		return filled;
//	}
//
//	public void setFilled(String filled) {
//		this.filled = filled;
//	}

	public List<PersonChecklistDetailsEntity> getPersonChecklistDetailsEntities() {
		return personChecklistDetailsEntities;
	}

	public void setPersonChecklistDetailsEntities(List<PersonChecklistDetailsEntity> personChecklistDetailsEntities) {
		this.personChecklistDetailsEntities = personChecklistDetailsEntities;
	}
	
}
