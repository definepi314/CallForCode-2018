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
@Table(name = "PERSON_TUTORIAL")
public class PersonTutorialDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERSON_TUTORIAL_ID")
	private long personTutorialId;

	@ManyToOne
	@JoinColumn(name = "PERSON_ID")
	@JsonIgnore
	private PersonMasterEntity personMasterEntity;

	@ManyToOne
	@JoinColumn(name = "TUTORIAL_ID")
	@JsonIgnore
	private TutorialMasterEntity tutorialMasterEntity;

	public long getPersonTutorialId() {
		return personTutorialId;
	}

	public void setPersonTutorialId(long personTutorialId) {
		this.personTutorialId = personTutorialId;
	}

	public PersonMasterEntity getPersonMasterEntity() {
		return personMasterEntity;
	}

	public void setPersonMasterEntity(PersonMasterEntity personMasterEntity) {
		this.personMasterEntity = personMasterEntity;
	}

	public TutorialMasterEntity getTutorialMasterEntity() {
		return tutorialMasterEntity;
	}

	public void setTutorialMasterEntity(TutorialMasterEntity tutorialMasterEntity) {
		this.tutorialMasterEntity = tutorialMasterEntity;
	}

}
