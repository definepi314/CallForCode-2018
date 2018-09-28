package com.capgemini.callforcode.services;

import java.util.List;

import com.capgemini.callforcode.dtos.AnalyticsDTO;
import com.capgemini.callforcode.dtos.AnalyticsReturnDTO;
import com.capgemini.callforcode.dtos.ChecklistDTO;
import com.capgemini.callforcode.dtos.ListDTO;
import com.capgemini.callforcode.dtos.LoginDTO;
import com.capgemini.callforcode.dtos.MultipleChecklistDTO;
import com.capgemini.callforcode.dtos.SaveChecklistFormDTO;
import com.capgemini.callforcode.dtos.VolcanoDTO;
import com.capgemini.callforcode.dtos.ZoneDTO;
import com.capgemini.callforcode.exception.CallForCodeException;

public interface VolcanoService {

	public List<VolcanoDTO> getVolcanoCentre() throws CallForCodeException;

	public List<VolcanoDTO> addZone(ZoneDTO zoneDto) throws CallForCodeException;

	public Boolean addDisasterChecklist(MultipleChecklistDTO checklistDto) throws CallForCodeException;

	public List<ListDTO> getCheckListByUser(String username) throws CallForCodeException;

	public ChecklistDTO getTutorial(String username) throws CallForCodeException;

	public Boolean addDisasterTutorial(ChecklistDTO checklistDto) throws CallForCodeException;

	public Boolean saveCheckListForm(SaveChecklistFormDTO saveChecklistFormDTO) throws CallForCodeException;

	public List<AnalyticsReturnDTO> getAnalytics(AnalyticsDTO analyticsDTO) throws CallForCodeException;

}
