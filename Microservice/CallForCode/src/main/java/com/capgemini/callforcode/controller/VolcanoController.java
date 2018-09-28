package com.capgemini.callforcode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.callforcode.dtos.AnalyticsDTO;
import com.capgemini.callforcode.dtos.AnalyticsReturnDTO;
import com.capgemini.callforcode.dtos.ChecklistDTO;
import com.capgemini.callforcode.dtos.CityDTO;
import com.capgemini.callforcode.dtos.GetcityDTO;
import com.capgemini.callforcode.dtos.ListDTO;
import com.capgemini.callforcode.dtos.LoginDTO;
import com.capgemini.callforcode.dtos.MultipleChecklistDTO;
import com.capgemini.callforcode.dtos.ResponseDto;
import com.capgemini.callforcode.dtos.SaveChecklistFormDTO;
import com.capgemini.callforcode.dtos.SubCheckListFillDto;
import com.capgemini.callforcode.dtos.VolcanoDTO;
import com.capgemini.callforcode.dtos.ZoneDTO;
import com.capgemini.callforcode.exception.CallForCodeException;
import com.capgemini.callforcode.services.CityService;
import com.capgemini.callforcode.services.FirebaseService;
import com.capgemini.callforcode.services.VolcanoService;

@RestController
@RequestMapping("volcano")
public class VolcanoController {
	
	@Autowired
	VolcanoService volcanoService;
	
	@Autowired
	FirebaseService firebaseService;
	
	@Autowired
	CityService cityService;
	
	@RequestMapping("/getVolcanoCentre")
	public ResponseDto getVolcanoCentre(){
		ResponseDto responseDto = new ResponseDto();
		List<VolcanoDTO> volcanoDtos = null;
		try {
			volcanoDtos = volcanoService.getVolcanoCentre();
			responseDto.setResponse(volcanoDtos);
		} catch (CallForCodeException e) {
			responseDto.setException(e.getMessage());
		}
		return responseDto;
	}
	
	@RequestMapping("addZone")
	public ResponseDto addZone(@RequestBody ZoneDTO zoneDto){
		ResponseDto responseDto = new ResponseDto();
		List<VolcanoDTO> volcanoDtos = null;
		try {
			volcanoDtos = volcanoService.addZone(zoneDto);
			responseDto.setResponse(volcanoDtos);
		} catch (CallForCodeException e) {
			responseDto.setException(e.getMessage());
		}
		return responseDto;
	}
	
	@RequestMapping("addCheckList")
	public ResponseDto addDisasterCheckList(@RequestBody MultipleChecklistDTO checklistDto){
		ResponseDto responseDto = new ResponseDto();
		Boolean addSuccess = null;
		try {
			addSuccess = volcanoService.addDisasterChecklist(checklistDto);
			responseDto.setResponse(addSuccess);
		} catch (CallForCodeException e) {
			responseDto.setException(e.getMessage());
		}
		return responseDto;
	}
	
	@RequestMapping("addTutorial")
	public ResponseDto addDisasterTutorial(@RequestBody ChecklistDTO checklistDto){
		System.out.println("checklistDto"+checklistDto);
		ResponseDto responseDto = new ResponseDto();
		Boolean addSuccess = null;
		try {
			addSuccess = volcanoService.addDisasterTutorial(checklistDto);
			responseDto.setResponse(addSuccess);
		} catch (CallForCodeException e) {
			responseDto.setException(e.getMessage());
		}
		return responseDto;
	}
	
	@RequestMapping("getCheckList")
	public ResponseDto getCheckListByUser(@RequestParam String username){
		ResponseDto responseDto = new ResponseDto();
		List<ListDTO> checklistDTOs = null;
		try {
			checklistDTOs = volcanoService.getCheckListByUser(username);
			responseDto.setResponse(checklistDTOs);
		} catch (CallForCodeException e) {
			responseDto.setException(e.getMessage());
		}
		return responseDto;
	}
	
	@RequestMapping("getTutorial")
	public ResponseDto getTutorialByUser(@RequestParam String username){
		ResponseDto responseDto = new ResponseDto();
		ChecklistDTO checklistDTO = null;
		try {
			checklistDTO = volcanoService.getTutorial(username);
			System.out.println(checklistDTO);
			responseDto.setResponse(checklistDTO);
		} catch (CallForCodeException e) {
			responseDto.setException(e.getMessage());
		}
		return responseDto;
	}
	
	@RequestMapping("getCities")
	public void getAllCities(@RequestBody GetcityDTO getcityDto){
		cityService.getAllCities(getcityDto);
	}
	
	@RequestMapping("savecheckListForm")
	public ResponseDto saveCheckListForm(@RequestBody SaveChecklistFormDTO saveChecklistFormDTO){
		ResponseDto responseDto = new ResponseDto();
		Boolean addSuccess = null;
		try {
			addSuccess = volcanoService.saveCheckListForm(saveChecklistFormDTO);
			responseDto.setResponse(addSuccess);
		} catch (CallForCodeException e) {
			responseDto.setException(e.getMessage());
		}
		return responseDto;
		
	}

	@RequestMapping("getAnalytics")
	public ResponseDto getAnalytics(@RequestBody AnalyticsDTO analyticsDTO){
		ResponseDto responseDto = new ResponseDto();
		List<AnalyticsReturnDTO> analyticsReturnDTOs = null;
		try {
			analyticsReturnDTOs = volcanoService.getAnalytics(analyticsDTO);
			responseDto.setResponse(analyticsReturnDTOs);
		} catch (CallForCodeException e) {
			responseDto.setException(e.getMessage());
		}
		return responseDto;
		
	}
	
//	public ResponseDto fillSubCheckList(@RequestBody SaveChecklistFormDTO saveChecklistFormDTO){
//		ResponseDto responseDto = new ResponseDto();
//		Boolean addSuccess = null;
//		responseDto.setMessage(volcanoService.saveCheckListForm(saveChecklistFormDTO));
//		return null;
//	}
	 
}
