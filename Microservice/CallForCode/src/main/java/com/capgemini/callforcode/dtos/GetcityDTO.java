package com.capgemini.callforcode.dtos;

public class GetcityDTO {

	private String lat;
	private String lng;
	private long radius;
	private long volcanoId;

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public long getRadius() {
		return radius;
	}

	public void setRadius(long radius) {
		this.radius = radius;
	}

	public long getVolcanoId() {
		return volcanoId;
	}

	public void setVolcanoId(long volcanoId) {
		this.volcanoId = volcanoId;
	}

}
