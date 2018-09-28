package com.capgemini.callforcode.services;

import java.util.List;

import com.capgemini.callforcode.dtos.PersonDTO;
import com.capgemini.callforcode.exception.CallForCodeException;

public interface PersonService {

	List<PersonDTO> getPopulationView() throws CallForCodeException;

}
