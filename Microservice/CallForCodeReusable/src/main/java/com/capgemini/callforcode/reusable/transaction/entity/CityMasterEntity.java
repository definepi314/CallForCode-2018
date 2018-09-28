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
@Table(name = "CITY_MASTER")
public class CityMasterEntity extends BasicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CITY_ID")
	private long cityId;

	@Column(name = "CITY_NAME")
	private String cityName;

	@ManyToOne
	@JoinColumn(name = "STATE_ID")
	@JsonIgnore
	private StateMasterEntity stateMasterEntity;

	@OneToMany(mappedBy = "cityMasterEntity", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<VolcanoCityDetails> volcanoCityDetails;

	@OneToMany(mappedBy = "cityMasterEntity", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<AddressDetailsEntity> addressDetailsEntities;

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public StateMasterEntity getStateMasterEntity() {
		return stateMasterEntity;
	}

	public void setStateMasterEntity(StateMasterEntity stateMasterEntity) {
		this.stateMasterEntity = stateMasterEntity;
	}

	public List<VolcanoCityDetails> getVolcanoCityDetails() {
		return volcanoCityDetails;
	}

	public void setVolcanoCityDetails(List<VolcanoCityDetails> volcanoCityDetails) {
		this.volcanoCityDetails = volcanoCityDetails;
	}

	public List<AddressDetailsEntity> getAddressDetailsEntities() {
		return addressDetailsEntities;
	}

	public void setAddressDetailsEntities(List<AddressDetailsEntity> addressDetailsEntities) {
		this.addressDetailsEntities = addressDetailsEntities;
	}

}
