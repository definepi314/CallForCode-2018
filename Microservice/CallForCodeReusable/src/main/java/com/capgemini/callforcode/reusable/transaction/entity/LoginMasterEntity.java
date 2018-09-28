package com.capgemini.callforcode.reusable.transaction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LOGIN_MASTER")
public class LoginMasterEntity extends BasicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOGIN_ID")
	private long loginId;

	@Column(name = "USERNAME" , unique = true)
	private String userName;

	@Column(name = "PASSWORD")
	private String password;

	@OneToOne(mappedBy = "loginMasterEntity")
	private RegistrationMasterEntity registrationMasterEntity;

	@OneToOne(mappedBy = "loginMasterEntity")
	private PersonMasterEntity personMasterEntity;

	public long getLoginId() {
		return loginId;
	}

	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RegistrationMasterEntity getRegistrationMasterEntity() {
		return registrationMasterEntity;
	}

	public void setRegistrationMasterEntity(RegistrationMasterEntity registrationMasterEntity) {
		this.registrationMasterEntity = registrationMasterEntity;
	}

	public PersonMasterEntity getPersonMasterEntity() {
		return personMasterEntity;
	}

	public void setPersonMasterEntity(PersonMasterEntity personMasterEntity) {
		this.personMasterEntity = personMasterEntity;
	}
}
