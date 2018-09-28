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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TUTORIAL_MASTER")
public class TutorialMasterEntity extends BasicEntity {

	@Id
	@SequenceGenerator(name="TUTORIAL_GEN",sequenceName="TUTORIAL_GEN_SEQ", initialValue=1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="TUTORIAL_GEN")
	@Column(name = "TUTORIAL_ID")
	private long tutorialId;
	
	@Column(name = "VIDEO_NAME")
	private String videoName;

	@Column(name = "VIDEO_LINK")
	private String videoLink;

	@ManyToOne
	@JoinColumn(name = "DISASTER_ID")
	@JsonIgnore
	private DisasterMasterEntity disasterMasterEntity;

	@OneToMany(mappedBy = "tutorialMasterEntity", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PersonTutorialDetailsEntity> personTutorialDetailsEntities;

	public long getTutorialId() {
		return tutorialId;
	}

	public void setTutorialId(long tutorialId) {
		this.tutorialId = tutorialId;
	}
	
	

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public DisasterMasterEntity getDisasterMasterEntity() {
		return disasterMasterEntity;
	}

	public void setDisasterMasterEntity(DisasterMasterEntity disasterMasterEntity) {
		this.disasterMasterEntity = disasterMasterEntity;
	}

	public List<PersonTutorialDetailsEntity> getPersonTutorialDetailsEntities() {
		return personTutorialDetailsEntities;
	}

	public void setPersonTutorialDetailsEntities(List<PersonTutorialDetailsEntity> personTutorialDetailsEntities) {
		this.personTutorialDetailsEntities = personTutorialDetailsEntities;
	}

}
