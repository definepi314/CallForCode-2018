package com.capgemini.callforcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.callforcode.services.IBMWatsonService;

@RestController
@RequestMapping("ibmwatson")
public class WatsonController {
	
	@Autowired
	IBMWatsonService ibmWatsonService;
	
	@RequestMapping("languageTranslator")
	public void languageTranslator(){
		ibmWatsonService.languageTranslator();
	}
	
	@RequestMapping("chatResponse")
	public String chatbotResponse(@RequestParam String inputText){
		return ibmWatsonService.chatbotResponse(inputText);
	}

}
