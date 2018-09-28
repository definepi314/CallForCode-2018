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
@Table(name = "CHECKLIST")
public class CheckListEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CHECKLIST_ID")
	private long checkListId;

	@Column(name = "CHECKLIST_NAME")
	private String checkListName;

	@Column(name = "DESCRIPTION")
	private String description;

	@ManyToOne
	@JoinColumn(name = "DISASTER_ID")
	@JsonIgnore
	private DisasterMasterEntity disasterMasterEntity;

	@OneToMany(mappedBy = "checkListEntity", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CheckListDetailsEntity> checkListDetailsEntities;

	

	public long getCheckListId() {
		return checkListId;
	}

	public void setCheckListId(long checkListId) {
		this.checkListId = checkListId;
	}

	public String getCheckListName() {
		return checkListName;
	}

	public void setCheckListName(String checkListName) {
		this.checkListName = checkListName;
	}

	public DisasterMasterEntity getDisasterMasterEntity() {
		return disasterMasterEntity;
	}

	public void setDisasterMasterEntity(DisasterMasterEntity disasterMasterEntity) {
		this.disasterMasterEntity = disasterMasterEntity;
	}

	

	public List<CheckListDetailsEntity> getCheckListDetailsEntities() {
		return checkListDetailsEntities;
	}

	public void setCheckListDetailsEntities(List<CheckListDetailsEntity> checkListDetailsEntities) {
		this.checkListDetailsEntities = checkListDetailsEntities;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
