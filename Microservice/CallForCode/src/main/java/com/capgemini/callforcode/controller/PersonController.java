package com.capgemini.callforcode.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.callforcode.dtos.PersonDTO;
import com.capgemini.callforcode.dtos.ResponseDto;
import com.capgemini.callforcode.exception.CallForCodeException;
import com.capgemini.callforcode.services.PersonService;

@RestController
@RequestMapping("person")
public class PersonController {
	
	@Autowired
	PersonService personService;

	@RequestMapping("populationView")
	public ResponseDto populationView(){
		ResponseDto responseDto = new ResponseDto();
		try {
			List<PersonDTO> personDTOs = personService.getPopulationView();
			responseDto.setResponse(personDTOs);
		} catch (CallForCodeException e) {
			responseDto.setException(e.getMessage());
		}
		return responseDto;
	}
}
