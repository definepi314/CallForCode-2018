package com.capgemini.callforcode.reusable.transaction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "REGISTRATION_MASTER")
public class RegistrationMasterEntity extends BasicEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "REGISTRATION_ID")
	private long registrationId;

	@Column(name = "MOBILE")
	private long mobile;

	@Column(name = "OTP")
	private Long otp;

	@OneToOne
	@JoinColumn(name = "LOGIN_ID")
	private LoginMasterEntity loginMasterEntity;

	public long getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(long registrationId) {
		this.registrationId = registrationId;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public Long getOtp() {
		return otp;
	}

	public void setOtp(Long otp) {
		this.otp = otp;
	}

	public LoginMasterEntity getLoginMasterEntity() {
		return loginMasterEntity;
	}

	public void setLoginMasterEntity(LoginMasterEntity loginMasterEntity) {
		this.loginMasterEntity = loginMasterEntity;
	}

}
