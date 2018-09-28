package com.capgemini.callforcode.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.protocol.ResponseDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.callforcode.dtos.LoginDTO;
import com.capgemini.callforcode.dtos.RegisterDTO;
import com.capgemini.callforcode.dtos.ResponseDto;
import com.capgemini.callforcode.dtos.RoleDTO;
import com.capgemini.callforcode.exception.CallForCodeException;
import com.capgemini.callforcode.services.LoginService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	LoginService loginService;

	@RequestMapping("login")
	public ResponseDto verifyLogin(@RequestBody LoginDTO loginDto) {
		ResponseDto responseDto = new ResponseDto();

		RoleDTO roleDTO;
		try {
			roleDTO = loginService.verifyRole(loginDto);
			responseDto.setResponse(roleDTO);
		} catch (CallForCodeException e) {
			responseDto.setException(e.getMessage());
		}
		return responseDto;
	}
	
	@RequestMapping("/register")
	public ResponseDto registerUser(@RequestBody RegisterDTO registerDTO){
		ResponseDto responseDto = new ResponseDto();

		String username;
		try {
			username = loginService.register(registerDTO);
			responseDto.setResponse(username);
		} catch (CallForCodeException e) {
			responseDto.setException(e.getMessage());
		}
		return responseDto;
	}

}
