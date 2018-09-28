package com.capgemini.callforcode.reusable.transaction.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PERSON_MASTER")
public class PersonMasterEntity extends BasicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERSON_ID")
	private long personId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "IS_PHYSICALLY_CHALLENGED")
	private boolean isPhysicallyChallenged;

	@OneToOne
	@JoinColumn(name = "LOGIN_ID")
	private LoginMasterEntity loginMasterEntity;

	@OneToMany(mappedBy = "personMasterEntity", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<AddressDetailsEntity> addressDetailsEntities;

	@OneToMany(mappedBy = "personMasterEntity", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PersonRoleEntity> personRoleEntities;

	@OneToMany(mappedBy = "personMasterEntity", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PersonTutorialDetailsEntity> personTutorialDetailsEntity;

	@OneToMany(mappedBy = "personMasterEntity", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PersonChecklistDetailsEntity> personChecklistDetailsEntities;

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isPhysicallyChallenged() {
		return isPhysicallyChallenged;
	}

	public void setPhysicallyChallenged(boolean isPhysicallyChallenged) {
		this.isPhysicallyChallenged = isPhysicallyChallenged;
	}

	public LoginMasterEntity getLoginMasterEntity() {
		return loginMasterEntity;
	}

	public void setLoginMasterEntity(LoginMasterEntity loginMasterEntity) {
		this.loginMasterEntity = loginMasterEntity;
	}

	public List<AddressDetailsEntity> getAddressDetailsEntities() {
		return addressDetailsEntities;
	}

	public void setAddressDetailsEntities(List<AddressDetailsEntity> addressDetailsEntities) {
		this.addressDetailsEntities = addressDetailsEntities;
	}

	public List<PersonRoleEntity> getPersonRoleEntities() {
		return personRoleEntities;
	}

	public void setPersonRoleEntities(List<PersonRoleEntity> personRoleEntities) {
		this.personRoleEntities = personRoleEntities;
	}

	public List<PersonTutorialDetailsEntity> getPersonTutorialDetailsEntity() {
		return personTutorialDetailsEntity;
	}

	public void setPersonTutorialDetailsEntity(List<PersonTutorialDetailsEntity> personTutorialDetailsEntity) {
		this.personTutorialDetailsEntity = personTutorialDetailsEntity;
	}

	public List<PersonChecklistDetailsEntity> getPersonChecklistDetailsEntities() {
		return personChecklistDetailsEntities;
	}

	public void setPersonChecklistDetailsEntities(List<PersonChecklistDetailsEntity> personChecklistDetailsEntities) {
		this.personChecklistDetailsEntities = personChecklistDetailsEntities;
	}

}
