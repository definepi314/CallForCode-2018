package com.capgemini.callforcode.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.callforcode.dtos.AddressDTO;
import com.capgemini.callforcode.dtos.PersonDTO;
import com.capgemini.callforcode.exception.CallForCodeException;
import com.capgemini.callforcode.repositories.PersonMasterRepository;
import com.capgemini.callforcode.reusable.transaction.entity.AddressDetailsEntity;
import com.capgemini.callforcode.reusable.transaction.entity.PersonMasterEntity;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	PersonMasterRepository personMasterRepository;

	@Override
	public List<PersonDTO> getPopulationView() throws CallForCodeException {
		List<PersonDTO> personDTOs = new ArrayList<>();
		PersonDTO personDTO = null;
		List<PersonMasterEntity> personMasterEntities = personMasterRepository.findAll();
		for(PersonMasterEntity personMasterEntity : personMasterEntities){
			personDTO = new PersonDTO();
			personDTO.setFirstName(personMasterEntity.getFirstName());
			personDTO.setMiddleName(personMasterEntity.getMiddleName());
			personDTO.setLastName(personMasterEntity.getLastName());
			personDTO.setEmailId(personMasterEntity.getEmail());
			personDTO.setIsPhysicallyChallenged(personMasterEntity.isPhysicallyChallenged());
			System.out.println(personMasterEntity.getLoginMasterEntity());
			System.out.println("lkjd"+personMasterEntity.getLoginMasterEntity().getLoginId());
			personDTO.setMobile(personMasterEntity.getLoginMasterEntity().getRegistrationMasterEntity().getMobile());
			List<AddressDTO> addressDTOs = new ArrayList<>();
			AddressDTO addressDTO = null;
			List<AddressDetailsEntity> addressDetailsEntities = personMasterEntity.getAddressDetailsEntities();
			for(AddressDetailsEntity addressDetailsEntity : addressDetailsEntities){
				addressDTO = new AddressDTO();
				addressDTO.setLandmark(addressDetailsEntity.getLandmark());
				addressDTO.setLatitude(addressDetailsEntity.getLatitude());
				addressDTO.setLongitude(addressDetailsEntity.getLongitude());
				addressDTO.setCity(addressDetailsEntity.getCityMasterEntity().getCityName());
				addressDTO.setState(addressDetailsEntity.getCityMasterEntity().getStateMasterEntity().getStateName());
				addressDTO.setCountry(addressDetailsEntity.getCityMasterEntity().getStateMasterEntity().getCountryMasterEntity().getCountryName());
				addressDTOs.add(addressDTO);
			}
			personDTO.setAddressDTOs(addressDTOs);
			personDTOs.add(personDTO);
		}
		return personDTOs;
	}

}
