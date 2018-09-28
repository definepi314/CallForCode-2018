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
@Table(name = "VOLCANO_CITY")
public class VolcanoCityDetails extends BasicEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "VOLCANO_CITY_ID")
	private long volcanoCityId;
	
	@ManyToOne
	@JoinColumn(name = "VOLCANO_ID")
	@JsonIgnore
	private VolcanoMasterEntity volcanoMasterEntity;
	
	@ManyToOne
	@JoinColumn(name = "CITY_ID")
	@JsonIgnore
	private CityMasterEntity cityMasterEntity;

	public long getVolcanoCityId() {
		return volcanoCityId;
	}

	public void setVolcanoCityId(long volcanoCityId) {
		this.volcanoCityId = volcanoCityId;
	}

	public VolcanoMasterEntity getVolcanoMasterEntity() {
		return volcanoMasterEntity;
	}

	public void setVolcanoMasterEntity(VolcanoMasterEntity volcanoMasterEntity) {
		this.volcanoMasterEntity = volcanoMasterEntity;
	}

	public CityMasterEntity getCityMasterEntity() {
		return cityMasterEntity;
	}

	public void setCityMasterEntity(CityMasterEntity cityMasterEntity) {
		this.cityMasterEntity = cityMasterEntity;
	}
	
	
}
