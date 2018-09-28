package com.capgemini.callforcode.dtos;

public class VolcanoDTO {

	private long volcanoId;
	private String latitude;
	private String longitude;
	private String redZone;
	private String yellowZone;
	private String blueZone;
	private String volcanoName;

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

	public String getVolcanoName() {
		return volcanoName;
	}

	public void setVolcanoName(String volcanoName) {
		this.volcanoName = volcanoName;
	}

	public long getVolcanoId() {
		return volcanoId;
	}

	public void setVolcanoId(long volcanoId) {
		this.volcanoId = volcanoId;
	}

}
