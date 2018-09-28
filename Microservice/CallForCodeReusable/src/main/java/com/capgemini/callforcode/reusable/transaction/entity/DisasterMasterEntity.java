package com.capgemini.callforcode.reusable.transaction.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "DISASTER_MASTER")
public class DisasterMasterEntity extends BasicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DISASTER_ID")
	private long disasterId;

	@Column(name = "DISASTER_NAME")
	private String disasterName;

	@OneToMany(mappedBy = "disasterMasterEntity", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<VolcanoMasterEntity> volcanoMasterEntities;

	@OneToMany(mappedBy = "disasterMasterEntity", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<TutorialMasterEntity> tutorialMasterEntities;

	@OneToMany(mappedBy = "disasterMasterEntity", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CheckListEntity> checkListEntities;

	public long getDisasterId() {
		return disasterId;
	}

	public void setDisasterId(long disasterId) {
		this.disasterId = disasterId;
	}

	public String getDisasterName() {
		return disasterName;
	}

	public void setDisasterName(String disasterName) {
		this.disasterName = disasterName;
	}

	public List<VolcanoMasterEntity> getVolcanoMasterEntities() {
		return volcanoMasterEntities;
	}

	public void setVolcanoMasterEntities(List<VolcanoMasterEntity> volcanoMasterEntities) {
		this.volcanoMasterEntities = volcanoMasterEntities;
	}

	public List<TutorialMasterEntity> getTutorialMasterEntities() {
		return tutorialMasterEntities;
	}

	public void setTutorialMasterEntities(List<TutorialMasterEntity> tutorialMasterEntities) {
		this.tutorialMasterEntities = tutorialMasterEntities;
	}

	public List<CheckListEntity> getCheckListEntities() {
		return checkListEntities;
	}

	public void setCheckListEntities(List<CheckListEntity> checkListEntities) {
		this.checkListEntities = checkListEntities;
	}

}
