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
@Table(name = "COUNTRY_MASTER")
public class CountryMasterEntity extends BasicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COUNTRY_ID")
	private long countryId;
	
	@Column(name = "COUNTRY_NAME")
	private String countryName;
	
	@OneToMany(mappedBy = "countryMasterEntity", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StateMasterEntity> stateMasterEntities;

	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<StateMasterEntity> getStateMasterEntities() {
		return stateMasterEntities;
	}

	public void setStateMasterEntities(List<StateMasterEntity> stateMasterEntities) {
		this.stateMasterEntities = stateMasterEntities;
	}

}
