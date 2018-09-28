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
@Table(name = "STATE_MASTER")
public class StateMasterEntity extends BasicEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STATE_ID")
	private long stateId;
	
	@Column(name = "STATE_NAME")
	private String stateName;
	
	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID")
	@JsonIgnore
	private CountryMasterEntity countryMasterEntity;
	
	@OneToMany(mappedBy = "stateMasterEntity", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CityMasterEntity> cityMasterEntities;

	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public CountryMasterEntity getCountryMasterEntity() {
		return countryMasterEntity;
	}

	public void setCountryMasterEntity(CountryMasterEntity countryMasterEntity) {
		this.countryMasterEntity = countryMasterEntity;
	}

	public List<CityMasterEntity> getCityMasterEntities() {
		return cityMasterEntities;
	}

	public void setCityMasterEntities(List<CityMasterEntity> cityMasterEntities) {
		this.cityMasterEntities = cityMasterEntities;
	}

}
