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
@Table(name = "VOLCANO_DETAILS")
public class VolcanoDetailsEntity extends BasicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "VOLCANO_DETAIL_ID")
	private long volcanoDetailId;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;

	@Column(name = "REDZONE")
	private String redZone;

	@Column(name = "YELLOWZONE")
	private String yellowZone;

	@Column(name = "BLUEZONE")
	private String blueZone;

	@OneToOne
	@JoinColumn(name = "VOLCANO_ID")
	private VolcanoMasterEntity volcanoMasterEntity;

	public long getVolcanoDetailId() {
		return volcanoDetailId;
	}

	public void setVolcanoDetailId(long volcanoDetailId) {
		this.volcanoDetailId = volcanoDetailId;
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

	public VolcanoMasterEntity getVolcanoMasterEntity() {
		return volcanoMasterEntity;
	}

	public void setVolcanoMasterEntity(VolcanoMasterEntity volcanoMasterEntity) {
		this.volcanoMasterEntity = volcanoMasterEntity;
	}

	public String getRedZone() {
		return redZone;
	}

	public void setRedZone(String redZone) {
		this.redZone = redZone;
	}

	public String getYellowZone() {
		return yellowZone;
	}

	public void setYellowZone(String yellowZone) {
		this.yellowZone = yellowZone;
	}

	public String getBlueZone() {
		return blueZone;
	}

	public void setBlueZone(String blueZone) {
		this.blueZone = blueZone;
	}

}
