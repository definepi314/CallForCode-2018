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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "VOLCANO_MASTER")
public class VolcanoMasterEntity extends BasicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "VOLCANO_ID")
	private long volcanoMasterId;

	@Column(name = "VOLCANO_NAME")
	private String volcanoName;

	@OneToOne(mappedBy = "volcanoMasterEntity")
	private VolcanoDetailsEntity volcanoDetailsEntity;

	@ManyToOne
	@JoinColumn(name = "DISASTER_ID")
	@JsonIgnore
	private DisasterMasterEntity disasterMasterEntity;

	@OneToMany(mappedBy = "volcanoMasterEntity", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<VolcanoCityDetails> volcanoCityDetails;

	public long getVolcanoMasterId() {
		return volcanoMasterId;
	}

	public void setVolcanoMasterId(long volcanoMasterId) {
		this.volcanoMasterId = volcanoMasterId;
	}

	public String getVolcanoName() {
		return volcanoName;
	}

	public void setVolcanoName(String volcanoName) {
		this.volcanoName = volcanoName;
	}

	public VolcanoDetailsEntity getVolcanoDetailsEntity() {
		return volcanoDetailsEntity;
	}

	public void setVolcanoDetailsEntity(VolcanoDetailsEntity volcanoDetailsEntity) {
		this.volcanoDetailsEntity = volcanoDetailsEntity;
	}

	public DisasterMasterEntity getDisasterMasterEntity() {
		return disasterMasterEntity;
	}

	public void setDisasterMasterEntity(DisasterMasterEntity disasterMasterEntity) {
		this.disasterMasterEntity = disasterMasterEntity;
	}

	public List<VolcanoCityDetails> getVolcanoCityDetails() {
		return volcanoCityDetails;
	}

	public void setVolcanoCityDetails(List<VolcanoCityDetails> volcanoCityDetails) {
		this.volcanoCityDetails = volcanoCityDetails;
	}

}
