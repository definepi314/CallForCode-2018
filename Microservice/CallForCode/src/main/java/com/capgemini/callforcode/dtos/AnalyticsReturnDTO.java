package com.capgemini.callforcode.dtos;

import java.util.List;

public class AnalyticsReturnDTO {
	private long checklistId;
	private String checklistName;
	private long count;
	private List<SubAnalyticsDTO> subAnalyticsDTO;

	public long getChecklistId() {
		return checklistId;
	}

	public void setChecklistId(long checklistId) {
		this.checklistId = checklistId;
	}

	public String getChecklistName() {
		return checklistName;
	}

	public void setChecklistName(String checklistName) {
		this.checklistName = checklistName;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<SubAnalyticsDTO> getSubAnalyticsDTO() {
		return subAnalyticsDTO;
	}

	public void setSubAnalyticsDTO(List<SubAnalyticsDTO> subAnalyticsDTO) {
		this.subAnalyticsDTO = subAnalyticsDTO;
	}

}
