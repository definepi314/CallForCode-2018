package com.capgemini.callforcode.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.callforcode.dtos.StateDTO;
import com.capgemini.callforcode.exception.CallForCodeException;
import com.capgemini.callforcode.repositories.CityMasterRepository;
import com.capgemini.callforcode.repositories.CountryMasterRepository;
import com.capgemini.callforcode.repositories.DisasterMasterRepository;
import com.capgemini.callforcode.repositories.StateMasterRepository;
import com.capgemini.callforcode.repositories.VolcanoMasterRepository;
import com.capgemini.callforcode.reusable.transaction.entity.CityMasterEntity;
import com.capgemini.callforcode.reusable.transaction.entity.CountryMasterEntity;
import com.capgemini.callforcode.reusable.transaction.entity.DisasterMasterEntity;
import com.capgemini.callforcode.reusable.transaction.entity.StateMasterEntity;
import com.capgemini.callforcode.reusable.transaction.entity.VolcanoMasterEntity;

@Service
public class LookupServiceImpl implements LookupService {

	@Autowired
	CountryMasterRepository countryMasterRepository;
	
	@Autowired
	StateMasterRepository stateMasterRepository;
	
	@Autowired
	CityMasterRepository cityMasterRepository;
	
	@Autowired
	DisasterMasterRepository disasterMasterRepository;
	
	@Autowired
	VolcanoMasterRepository volcanoMasterRepository;
	
	@Override
	public List<StateDTO> getCountry() {
		List<CountryMasterEntity> countryMasterEntities = countryMasterRepository.findAll();
		List<StateDTO> stateDTOs = new ArrayList<>();
		StateDTO stateDTO = null; 
		for(CountryMasterEntity countryMasterEntity : countryMasterEntities){
			stateDTO = new StateDTO();
			stateDTO.setId(countryMasterEntity.getCountryId());
			stateDTO.setName(countryMasterEntity.getCountryName());
			stateDTOs.add(stateDTO);
		}
		return stateDTOs;
	}

	@Override
	public List<StateDTO> getState(long countryId) {
		List<StateMasterEntity> stateMasterEntities = stateMasterRepository.findByCountry(countryId);
		List<StateDTO> stateDTOs = new ArrayList<>();
		StateDTO stateDTO = null; 
		for(StateMasterEntity stateMasterEntity : stateMasterEntities){
			stateDTO = new StateDTO();
			stateDTO.setId(stateMasterEntity.getStateId());
			stateDTO.setName(stateMasterEntity.getStateName());
			stateDTOs.add(stateDTO);
		}
		return stateDTOs;
	}

	@Override
	public List<StateDTO> getCity(long stateId) {
		List<CityMasterEntity> cityMasterEntities = cityMasterRepository.findByState(stateId);
		List<StateDTO> stateDTOs = new ArrayList<>();
		StateDTO stateDTO = null; 
		for(CityMasterEntity cityMasterEntity : cityMasterEntities){
			stateDTO = new StateDTO();
			stateDTO.setId(cityMasterEntity.getCityId());
			stateDTO.setName(cityMasterEntity.getCityName());
			stateDTOs.add(stateDTO);
		}
		return stateDTOs;
	}

	@Override
	public List<StateDTO> getDisaster() {
		List<DisasterMasterEntity> disasterMasterEntities = disasterMasterRepository.findAll();
		List<StateDTO> stateDTOs = new ArrayList<>();
		StateDTO stateDTO = null; 
		for(DisasterMasterEntity disasterMasterEntity : disasterMasterEntities){
			stateDTO = new StateDTO();
			stateDTO.setId(disasterMasterEntity.getDisasterId());
			stateDTO.setName(disasterMasterEntity.getDisasterName());
			stateDTOs.add(stateDTO);
		}
		return stateDTOs;
	}

	@Override
	public List<StateDTO> getVolcano(long disasterId) {
		List<VolcanoMasterEntity> volcanoMasterEntities = volcanoMasterRepository.findByDisaster(disasterId);
		List<StateDTO> stateDTOs = new ArrayList<>();
		StateDTO stateDTO = null;
		
		for(VolcanoMasterEntity volcanoMasterEntity : volcanoMasterEntities){
			stateDTO = new StateDTO();
			stateDTO.setId(volcanoMasterEntity.getVolcanoMasterId());
			stateDTO.setName(volcanoMasterEntity.getVolcanoName());
			stateDTOs.add(stateDTO);
		}
		return stateDTOs;
	}
	

}
