package com.capgemini.callforcode.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.callforcode.dtos.AddressDTO;
import com.capgemini.callforcode.dtos.LoginDTO;
import com.capgemini.callforcode.dtos.PersonDTO;
import com.capgemini.callforcode.dtos.RegisterDTO;
import com.capgemini.callforcode.dtos.RoleDTO;
import com.capgemini.callforcode.exception.CallForCodeException;
import com.capgemini.callforcode.repositories.AddressDetailsRepository;
import com.capgemini.callforcode.repositories.CityMasterRepository;
import com.capgemini.callforcode.repositories.LoginMasterRepository;
import com.capgemini.callforcode.repositories.PersonChecklistDetailsRepository;
import com.capgemini.callforcode.repositories.PersonMasterRepository;
import com.capgemini.callforcode.repositories.PersonTutorialDetailsRepository;
import com.capgemini.callforcode.repositories.RegistrationMasterRepository;
import com.capgemini.callforcode.repositories.VolcanoMasterRepository;
import com.capgemini.callforcode.reusable.transaction.entity.AddressDetailsEntity;
import com.capgemini.callforcode.reusable.transaction.entity.CheckListEntity;
import com.capgemini.callforcode.reusable.transaction.entity.CityMasterEntity;
import com.capgemini.callforcode.reusable.transaction.entity.LoginMasterEntity;
import com.capgemini.callforcode.reusable.transaction.entity.PersonChecklistDetailsEntity;
import com.capgemini.callforcode.reusable.transaction.entity.PersonMasterEntity;
import com.capgemini.callforcode.reusable.transaction.entity.PersonRoleEntity;
import com.capgemini.callforcode.reusable.transaction.entity.PersonTutorialDetailsEntity;
import com.capgemini.callforcode.reusable.transaction.entity.RegistrationMasterEntity;
import com.capgemini.callforcode.reusable.transaction.entity.TutorialMasterEntity;
import com.capgemini.callforcode.reusable.transaction.entity.VolcanoMasterEntity;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginMasterRepository loginMasterRepository;
	
	@Autowired
	RegistrationMasterRepository registrationMasterRepository;
	
	@Autowired
	PersonMasterRepository personMasterRepository;
	
	@Autowired
	CityMasterRepository cityMasterRepository;
	
	@Autowired
	AddressDetailsRepository addressDetailsRepository;
	
	@Autowired
	VolcanoMasterRepository volcanoMasterRepository;
	
	@Autowired
	PersonTutorialDetailsRepository personTutorialDetailsRepository;
	
	@Autowired
	PersonChecklistDetailsRepository personChecklistDetailsRepository;
	
	@Override
	public RoleDTO verifyRole(LoginDTO loginDto) throws CallForCodeException {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setLoginStatus(false);
		LoginMasterEntity loginMasterEntity = loginMasterRepository.findByUserNameAndPassword(loginDto.getUsername(),loginDto.getPassword());
		if(loginMasterEntity != null){
			roleDTO.setLoginStatus(true);
			List<PersonRoleEntity> personRoleEntities = loginMasterEntity.getPersonMasterEntity().getPersonRoleEntities();
			List<String> roles = new ArrayList<>();
			for(PersonRoleEntity personRoleEntity : personRoleEntities){
				roles.add(personRoleEntity.getRoleMasterEntity().getRoleName());
			}
			roleDTO.setRoleName(roles);
			PersonDTO personDTO = new PersonDTO();
			personDTO.setFirstName(loginMasterEntity.getPersonMasterEntity().getFirstName());
			personDTO.setMiddleName(loginMasterEntity.getPersonMasterEntity().getMiddleName());
			personDTO.setLastName(loginMasterEntity.getPersonMasterEntity().getLastName());
			personDTO.setEmailId(loginMasterEntity.getPersonMasterEntity().getEmail());
			personDTO.setIsPhysicallyChallenged(loginMasterEntity.getPersonMasterEntity().isPhysicallyChallenged());
			personDTO.setMobile((loginMasterEntity.getRegistrationMasterEntity().getMobile() != 0)?loginMasterEntity.getRegistrationMasterEntity().getMobile():0);
			List<AddressDetailsEntity> addressDetailsEntities = loginMasterEntity.getPersonMasterEntity().getAddressDetailsEntities();
			List<AddressDTO> addressDTOs = new ArrayList<>();
			AddressDTO addressDTO = null;
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
			roleDTO.setPersonDto(personDTO);
			return roleDTO;
		} else{
			throw new CallForCodeException("LOGIN NOT VERIFIED");
		}
	}

	@Override
	public String register(RegisterDTO registerDTO) throws CallForCodeException {
		PersonMasterEntity personMasterEntityCheck = null;
		if(registerDTO.getEmail() != null)
			personMasterEntityCheck = personMasterRepository.findByEmail(registerDTO.getEmail());
		if(personMasterEntityCheck == null){
			LoginMasterEntity loginMasterEntity = new LoginMasterEntity();
			String userName = registerDTO.getFirstName() + ((registerDTO.getLastName() != null && registerDTO.getLastName().length()>3)?registerDTO.getLastName().substring(0,2):"");
			if(userName != null && loginMasterRepository.findByUserName(userName) == null){
				loginMasterEntity.setUserName(userName);
				loginMasterEntity.setPassword(registerDTO.getPassword());
				loginMasterRepository.save(loginMasterEntity);
				RegistrationMasterEntity registrationMasterEntity = new RegistrationMasterEntity();
				registrationMasterEntity.setMobile(registerDTO.getMobile());
				registrationMasterEntity.setOtp(0L);
				registrationMasterEntity.setLoginMasterEntity(loginMasterEntity);
				registrationMasterRepository.save(registrationMasterEntity);
				PersonMasterEntity personMasterEntity = new PersonMasterEntity();
				personMasterEntity.setFirstName(registerDTO.getFirstName());
				personMasterEntity.setMiddleName(registerDTO.getMiddleName());
				personMasterEntity.setLastName(registerDTO.getLastName());
				personMasterEntity.setEmail(registerDTO.getEmail());
				personMasterEntity.setPhysicallyChallenged(registerDTO.isPhysicallyChallenged());
				personMasterEntity.setLoginMasterEntity(loginMasterEntity);
				PersonMasterEntity personMasterEntitySaved = personMasterRepository.save(personMasterEntity);
				CityMasterEntity cityMasterEntity = cityMasterRepository.findOne(registerDTO.getCityId());
				List<AddressDetailsEntity> addressDetailsEntities = new ArrayList<>();
				AddressDetailsEntity addressDetailsEntity = new AddressDetailsEntity();
				addressDetailsEntity.setLatitude(registerDTO.getLatitude());
				addressDetailsEntity.setLongitude(registerDTO.getLongitude());
				addressDetailsEntity.setLandmark(registerDTO.getLandmark());
				addressDetailsEntity.setCityMasterEntity(cityMasterEntity);
				addressDetailsEntity.setPersonMasterEntity(personMasterEntitySaved);
				addressDetailsRepository.save(addressDetailsEntity);
//				List<VolcanoMasterEntity> volcanoMasterEntities = volcanoMasterRepository.getVolcanoMasterByCity(registerDTO.getCityId());
//				for(VolcanoMasterEntity volcanoMasterEntity : volcanoMasterEntities){
//					List<TutorialMasterEntity> tutorialMasterEntities = volcanoMasterEntity.getDisasterMasterEntity().getTutorialMasterEntities();
//					PersonTutorialDetailsEntity personTutorialDetailsEntity = null;
//					for(TutorialMasterEntity tutorialMasterEntity : tutorialMasterEntities){
//						personTutorialDetailsEntity = new PersonTutorialDetailsEntity();
//						personTutorialDetailsEntity.setPersonMasterEntity(personMasterEntitySaved);
//						personTutorialDetailsEntity.setTutorialMasterEntity(tutorialMasterEntity);
//						personTutorialDetailsRepository.save(personTutorialDetailsEntity);
//					}
//					List<CheckListEntity> checkListEntities = volcanoMasterEntity.getDisasterMasterEntity().getCheckListEntities();
//					PersonChecklistDetailsEntity personChecklistDetailsEntity = null;
//					for(CheckListEntity checkListEntity : checkListEntities){
//						personChecklistDetailsEntity = new PersonChecklistDetailsEntity();
//						personChecklistDetailsEntity.setPersonMasterEntity(personMasterEntitySaved);
//						personChecklistDetailsEntity.setCheckListEntity(checkListEntity);
//						personChecklistDetailsRepository.save(personChecklistDetailsEntity);
//					}
//					
//				}
				return userName;
			} else{
				throw new CallForCodeException("USER REGISTRATION FAILED");
			}
			
		}else{
			throw new CallForCodeException("USER ALREADY EXISTS");
		}
		
		
	}

}
