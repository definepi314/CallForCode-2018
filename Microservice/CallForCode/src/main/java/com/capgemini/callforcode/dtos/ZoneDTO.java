package com.capgemini.callforcode.dtos;

public class ZoneDTO {

	private long volcanoId;
	private String latitude;
	private String longitude;
	private String volcanoName;
	private int zoneId;
	private String zone;
	private long radius;

	public long getVolcanoId() {
		return volcanoId;
	}

	public void setVolcanoId(long volcanoId) {
		this.volcanoId = volcanoId;
	}

	public int getZoneId() {
		return zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
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

	public String getVolcanoName() {
		return volcanoName;
	}

	public void setVolcanoName(String volcanoName) {
		this.volcanoName = volcanoName;
	}

	public long getRadius() {
		return radius;
	}

	public void setRadius(long radius) {
		this.radius = radius;
	}

}
