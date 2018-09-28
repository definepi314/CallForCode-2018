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
@Table(name = "PERSON_ROLE")
public class PersonRoleEntity extends BasicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERSON_ROLE_ID")
	private long personRoleId;

	@ManyToOne
	@JoinColumn(name = "PERSON_ID")
	@JsonIgnore
	private PersonMasterEntity personMasterEntity;

	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	@JsonIgnore
	private RoleMasterEntity roleMasterEntity;

	public long getPersonRoleId() {
		return personRoleId;
	}

	public void setPersonRoleId(long personRoleId) {
		this.personRoleId = personRoleId;
	}

	public PersonMasterEntity getPersonMasterEntity() {
		return personMasterEntity;
	}

	public void setPersonMasterEntity(PersonMasterEntity personMasterEntity) {
		this.personMasterEntity = personMasterEntity;
	}

	public RoleMasterEntity getRoleMasterEntity() {
		return roleMasterEntity;
	}

	public void setRoleMasterEntity(RoleMasterEntity roleMasterEntity) {
		this.roleMasterEntity = roleMasterEntity;
	}

}
