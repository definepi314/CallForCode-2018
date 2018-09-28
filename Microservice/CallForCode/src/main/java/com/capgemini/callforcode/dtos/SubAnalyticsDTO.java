package com.capgemini.callforcode.dtos;

public class SubAnalyticsDTO {

	private long subCount;
	private long subchecklistId;
	private String subchecklistName;

	public long getSubCount() {
		return subCount;
	}

	public void setSubCount(long subCount) {
		this.subCount = subCount;
	}

	public long getSubchecklistId() {
		return subchecklistId;
	}

	public void setSubchecklistId(long subchecklistId) {
		this.subchecklistId = subchecklistId;
	}

	public String getSubchecklistName() {
		return subchecklistName;
	}

	public void setSubchecklistName(String subchecklistName) {
		this.subchecklistName = subchecklistName;
	}

}
