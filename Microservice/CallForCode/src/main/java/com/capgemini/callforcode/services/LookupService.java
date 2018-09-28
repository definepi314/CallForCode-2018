package com.capgemini.callforcode.services;

import java.util.List;

import com.capgemini.callforcode.dtos.StateDTO;
import com.capgemini.callforcode.exception.CallForCodeException;

public interface LookupService {

	List<StateDTO> getCountry();

	List<StateDTO> getState(long countryId);

	List<StateDTO> getCity(long stateId);

	List<StateDTO> getDisaster();

	List<StateDTO> getVolcano(long disasterId);

}
