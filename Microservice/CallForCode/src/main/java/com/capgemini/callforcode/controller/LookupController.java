package com.capgemini.callforcode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.callforcode.dtos.StateDTO;
import com.capgemini.callforcode.services.LookupService;

@RestController
@RequestMapping("lookup")
public class LookupController {
	
	@Autowired
	LookupService lookupService;
	
	@RequestMapping("country")
	public List<StateDTO> getCountry(){
		return lookupService.getCountry();
	}
	
	@RequestMapping("state")
	public List<StateDTO> getState(@RequestParam long countryId){
			return lookupService.getState(countryId);
	}
	
	@RequestMapping("city")
	public List<StateDTO> getCity(@RequestParam long stateId){
			return lookupService.getCity(stateId);
	}
	
	@RequestMapping("disaster")
	public List<StateDTO> getDisaster(){
			return lookupService.getDisaster();
	}
	
	@RequestMapping("volcano")
	public List<StateDTO> getVolcano(@RequestParam long disasterId){
		return lookupService.getVolcano(disasterId);
	}

}
