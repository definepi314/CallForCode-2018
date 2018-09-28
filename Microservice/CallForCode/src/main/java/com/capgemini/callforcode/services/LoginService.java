package com.capgemini.callforcode.services;

import com.capgemini.callforcode.dtos.LoginDTO;
import com.capgemini.callforcode.dtos.RegisterDTO;
import com.capgemini.callforcode.dtos.RoleDTO;
import com.capgemini.callforcode.exception.CallForCodeException;

public interface LoginService {

	RoleDTO verifyRole(LoginDTO loginDto) throws CallForCodeException;

	String register(RegisterDTO registerDTO) throws CallForCodeException;

}
