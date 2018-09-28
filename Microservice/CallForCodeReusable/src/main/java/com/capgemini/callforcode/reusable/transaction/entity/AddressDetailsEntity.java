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
@Table(name = "ADDRESS_DETAILS")
public class AddressDetailsEntity extends BasicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ADDRESS_ID")
	private long addressDetailsId;

	@Column(name = "LANDMARK")
	private String landmark;

	@Column(name = "LATITUDE")
	private String latitude;

	@Column(name = "LONGITUDE")
	private String longitude;

	@ManyToOne
	@JoinColumn(name = "CITY_ID")
	@JsonIgnore
	private CityMasterEntity cityMasterEntity;

	@ManyToOne
	@JoinColumn(name = "PERSON_ID")
	@JsonIgnore
	private PersonMasterEntity personMasterEntity;

	public long getAddressDetailsId() {
		return addressDetailsId;
	}

	public void setAddressDetailsId(long addressDetailsId) {
		this.addressDetailsId = addressDetailsId;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public PersonMasterEntity getPersonMasterEntity() {
		return personMasterEntity;
	}

	public void setPersonMasterEntity(PersonMasterEntity personMasterEntity) {
		this.personMasterEntity = personMasterEntity;
	}

	public CityMasterEntity getCityMasterEntity() {
		return cityMasterEntity;
	}

	public void setCityMasterEntity(CityMasterEntity cityMasterEntity) {
		this.cityMasterEntity = cityMasterEntity;
	}

}
