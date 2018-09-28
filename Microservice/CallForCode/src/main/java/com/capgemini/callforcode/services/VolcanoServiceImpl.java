package com.capgemini.callforcode.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capgemini.callforcode.dtos.AnalyticsDTO;
import com.capgemini.callforcode.dtos.AnalyticsReturnDTO;
import com.capgemini.callforcode.dtos.ChecklistDTO;
import com.capgemini.callforcode.dtos.GetcityDTO;
import com.capgemini.callforcode.dtos.ListDTO;
import com.capgemini.callforcode.dtos.LoginDTO;
import com.capgemini.callforcode.dtos.MultipleChecklistDTO;
import com.capgemini.callforcode.dtos.SaveChecklistFormDTO;
import com.capgemini.callforcode.dtos.SiteDTO;
import com.capgemini.callforcode.dtos.SubAnalyticsDTO;
import com.capgemini.callforcode.dtos.SubChecklistFilledDTO;
import com.capgemini.callforcode.dtos.TutorialVideoDTO;
import com.capgemini.callforcode.dtos.VolcanoDTO;
import com.capgemini.callforcode.dtos.ZoneDTO;
import com.capgemini.callforcode.exception.CallForCodeException;
import com.capgemini.callforcode.repositories.CheckListDetailsRepository;
import com.capgemini.callforcode.repositories.CheckListRepository;
import com.capgemini.callforcode.repositories.DisasterMasterRepository;
import com.capgemini.callforcode.repositories.LoginMasterRepository;
import com.capgemini.callforcode.repositories.PersonChecklistDetailsRepository;
import com.capgemini.callforcode.repositories.PersonTutorialDetailsRepository;
import com.capgemini.callforcode.repositories.TutorialMasterRepository;
import com.capgemini.callforcode.repositories.VolcanoDetailsRepository;
import com.capgemini.callforcode.repositories.VolcanoMasterRepository;
import com.capgemini.callforcode.reusable.transaction.entity.AddressDetailsEntity;
import com.capgemini.callforcode.reusable.transaction.entity.CheckListDetailsEntity;
import com.capgemini.callforcode.reusable.transaction.entity.CheckListEntity;
import com.capgemini.callforcode.reusable.transaction.entity.DisasterMasterEntity;
import com.capgemini.callforcode.reusable.transaction.entity.LoginMasterEntity;
import com.capgemini.callforcode.reusable.transaction.entity.PersonChecklistDetailsEntity;
import com.capgemini.callforcode.reusable.transaction.entity.PersonMasterEntity;
import com.capgemini.callforcode.reusable.transaction.entity.TutorialMasterEntity;
import com.capgemini.callforcode.reusable.transaction.entity.VolcanoDetailsEntity;
import com.capgemini.callforcode.reusable.transaction.entity.VolcanoMasterEntity;


@Service
public class VolcanoServiceImpl implements VolcanoService {

	public static final String VOLCANO = "Volcano";

	@Autowired
	VolcanoMasterRepository volcanoMasterRepository;

	@Autowired
	VolcanoDetailsRepository volcanoDetailsRepository;

	@Autowired
	DisasterMasterRepository disasterMasterRepository;

	@Autowired
	TutorialMasterRepository tutorialMasterRepository;

	@Autowired
	CheckListRepository checkListRepository;

	@Autowired
	LoginMasterRepository loginMasterRepository;

	@Autowired
	PersonTutorialDetailsRepository personTutorialDetailsRepository;

	@Autowired
	PersonChecklistDetailsRepository personChecklistDetailsRepository;

	@Autowired
	CityService cityService;

	@Autowired
	CheckListDetailsRepository checklistDetailsRepository;

	@Override
	public List<VolcanoDTO> getVolcanoCentre() throws CallForCodeException {
		List<VolcanoDTO> volcanoDTOs = new ArrayList<>();
		VolcanoDTO volcanoDTO = null;
		List<VolcanoMasterEntity> volcanoMasterEntities = volcanoMasterRepository.findAll();
		for (VolcanoMasterEntity volcanoMasterEntity : volcanoMasterEntities) {
			volcanoDTO = new VolcanoDTO();
			volcanoDTO.setLatitude(volcanoMasterEntity.getVolcanoDetailsEntity().getLatitude());
			volcanoDTO.setLongitude(volcanoMasterEntity.getVolcanoDetailsEntity().getLongitude());
			volcanoDTO.setVolcanoName(volcanoMasterEntity.getVolcanoName());
			volcanoDTO.setVolcanoId(volcanoMasterEntity.getVolcanoMasterId());
			volcanoDTO.setBlueZone(volcanoMasterEntity.getVolcanoDetailsEntity().getBlueZone());
			volcanoDTO.setYellowZone(volcanoMasterEntity.getVolcanoDetailsEntity().getYellowZone());
			volcanoDTO.setRedZone(volcanoMasterEntity.getVolcanoDetailsEntity().getRedZone());
			volcanoDTOs.add(volcanoDTO);
		}
		return volcanoDTOs;
	}

	@Override
	public List<VolcanoDTO> addZone(ZoneDTO zoneDto) throws CallForCodeException {
		VolcanoMasterEntity volcanoMasterEntity = null;
		if (zoneDto.getVolcanoId() == 0) {
			DisasterMasterEntity disasterMasterEntity = disasterMasterRepository.findByName(VOLCANO);
			VolcanoMasterEntity volcanoMasterEntity1 = new VolcanoMasterEntity();
			volcanoMasterEntity1.setVolcanoName(zoneDto.getVolcanoName());
			volcanoMasterEntity1.setCreatedBy("Admin");
			volcanoMasterEntity1.setCreatedDate(new Date());
			volcanoMasterEntity1.setDisasterMasterEntity(disasterMasterEntity);
			volcanoMasterEntity = volcanoMasterRepository.save(volcanoMasterEntity1);
			VolcanoDetailsEntity volcanoDetailsEntity = new VolcanoDetailsEntity();
			volcanoDetailsEntity.setCreatedBy("Admin");
			volcanoDetailsEntity.setCreatedDate(new Date());
			volcanoDetailsEntity.setLatitude(zoneDto.getLatitude());
			volcanoDetailsEntity.setLongitude(zoneDto.getLongitude());
			volcanoDetailsEntity.setVolcanoMasterEntity(volcanoMasterEntity);
			volcanoDetailsEntity = volcanoDetailsRepository.save(volcanoDetailsEntity);
			volcanoMasterEntity.setVolcanoDetailsEntity(volcanoDetailsEntity);
			VolcanoMasterEntity volcanoMasterSaved = volcanoMasterRepository.save(volcanoMasterEntity);
			GetcityDTO getcityDTO = new GetcityDTO();
			getcityDTO.setLat(zoneDto.getLatitude());
			getcityDTO.setLng(zoneDto.getLongitude());
			getcityDTO.setRadius(zoneDto.getRadius());
			getcityDTO.setVolcanoId(volcanoMasterSaved.getVolcanoMasterId());
			cityService.getAllCities(getcityDTO);
		} else {
			volcanoMasterEntity = volcanoMasterRepository.findOne(zoneDto.getVolcanoId());
		}
		if (volcanoMasterEntity != null) {
			VolcanoDetailsEntity volcanoDetailsEntity = volcanoMasterEntity.getVolcanoDetailsEntity();
			switch (zoneDto.getZoneId()) {
			case 1:
				volcanoDetailsEntity.setRedZone(zoneDto.getZone());
				volcanoDetailsRepository.save(volcanoDetailsEntity);
				return getVolcanoCentre();

			case 2:
				volcanoDetailsEntity.setYellowZone(zoneDto.getZone());
				volcanoDetailsRepository.save(volcanoDetailsEntity);
				return getVolcanoCentre();

			case 3:
				volcanoDetailsEntity.setBlueZone(zoneDto.getZone());
				volcanoDetailsRepository.save(volcanoDetailsEntity);
				return getVolcanoCentre();

			default:
				return getVolcanoCentre();

			}

		} else {
			throw new CallForCodeException("VOLCANO SITE NOT REGISTERED");
		}
	}

	@Override
	public Boolean addDisasterChecklist(MultipleChecklistDTO checklistDto) throws CallForCodeException {
		DisasterMasterEntity disasterMasterEntity = disasterMasterRepository.findOne(checklistDto.getDisasterId());
		if (disasterMasterEntity != null) {
			CheckListEntity checkListEntity = new CheckListEntity();
			System.out.println(checklistDto.getCheckList());
			checkListEntity.setCheckListName(checklistDto.getCheckList());
			checkListEntity.setDescription(checklistDto.getDescription());
			checkListEntity.setDisasterMasterEntity(disasterMasterEntity);
			checkListEntity = checkListRepository.save(checkListEntity);
			List<CheckListDetailsEntity> checkListDetailsEntities = new ArrayList<>();
			CheckListDetailsEntity checkListDetailsEntity = null;

			for (String subcheck : checklistDto.getSubchecklist()) {
				checkListDetailsEntity = new CheckListDetailsEntity();
				checkListDetailsEntity.setSubChecklist(subcheck);
				checkListDetailsEntity.setCheckListEntity(checkListEntity);
				checkListDetailsEntities.add(checklistDetailsRepository.save(checkListDetailsEntity));

			}
			checkListEntity.setCheckListDetailsEntities(checkListDetailsEntities);
			checkListRepository.save(checkListEntity);

			return true;
		} else {
			throw new CallForCodeException("NO SUCH DISASTER REGISTERED");
		}
	}

	@Override
	public List<ListDTO> getCheckListByUser(String username) throws CallForCodeException {
		LoginMasterEntity loginMasterEntity = loginMasterRepository.findByUserName(username);
		if (loginMasterEntity != null) {
			List<ListDTO> listDTOs = new ArrayList<>();
			ListDTO listDTO = null;
			List<SubChecklistFilledDTO> subChecklistFilledDTOs = null;
			List<CheckListEntity> checkListEntities = checkListRepository
					.getChecklistByUser(loginMasterEntity.getPersonMasterEntity().getPersonId());
			for (CheckListEntity checkListEntity : checkListEntities) {
				listDTO = new ListDTO();
				listDTO.setChecklist(checkListEntity.getCheckListName());
				listDTO.setDescription(checkListEntity.getDescription());
				SubChecklistFilledDTO subChecklistFilledDTO = null;
				subChecklistFilledDTOs = new ArrayList<>();
				for (CheckListDetailsEntity checkListDetailsEntity : checkListEntity.getCheckListDetailsEntities()) {
					subChecklistFilledDTO  = new SubChecklistFilledDTO();
					subChecklistFilledDTO.setSubCheckListId(checkListDetailsEntity.getChecklistDetailId());
					subChecklistFilledDTO.setName(checkListDetailsEntity.getSubChecklist());
					subChecklistFilledDTO.setValue(
							personChecklistDetailsRepository.getSubcheckListValue(
									loginMasterEntity.getPersonMasterEntity().getPersonId(), checkListDetailsEntity.getChecklistDetailId()));
					subChecklistFilledDTOs.add(subChecklistFilledDTO);
				}
				listDTO.setFilled(subChecklistFilledDTOs);
				listDTOs.add(listDTO);
			}
			return listDTOs;
		} else {
			throw new CallForCodeException("USER NOT REGISTERED");
		}
	}

	@Override
	public ChecklistDTO getTutorial(String username) throws CallForCodeException {
		LoginMasterEntity loginMasterEntity = loginMasterRepository.findByUserName(username);
		if (loginMasterEntity != null) {
			List<TutorialMasterEntity> tutorialMasterEntities = tutorialMasterRepository.getTutorialByUser(loginMasterEntity.getPersonMasterEntity().getPersonId(),
					disasterMasterRepository.findByName(VOLCANO).getDisasterId());
			ChecklistDTO checklistDTO = new ChecklistDTO();
			List<TutorialVideoDTO>  tutorialVideoDTOs= new ArrayList<>();
			tutorialMasterEntities.forEach(tutorialMasterEntity ->{
				TutorialVideoDTO tutorialVideoDTO = new TutorialVideoDTO();
				tutorialVideoDTO.setVideoName(tutorialMasterEntity.getVideoName());
				tutorialVideoDTO.setVideoLink(tutorialMasterEntity.getVideoLink());
				tutorialVideoDTOs.add(tutorialVideoDTO);
			});
			checklistDTO.setChecklists(tutorialVideoDTOs);
			return checklistDTO;

		} else {
			throw new CallForCodeException("USER NOT REGISTERED");
		}
	}

	@Override
	public Boolean addDisasterTutorial(ChecklistDTO checklistDto) throws CallForCodeException {
		DisasterMasterEntity disasterMasterEntity = disasterMasterRepository.findOne(checklistDto.getDisasterId());
		if (disasterMasterEntity != null) {
			TutorialMasterEntity tutorialMasterEntity = null;

			for (TutorialVideoDTO video : checklistDto.getChecklists()) {
				tutorialMasterEntity = new TutorialMasterEntity();
				tutorialMasterEntity.setVideoName(video.getVideoName());
				tutorialMasterEntity.setVideoLink(video.getVideoLink());
				tutorialMasterEntity.setDisasterMasterEntity(disasterMasterEntity);
				tutorialMasterRepository.save(tutorialMasterEntity);
			}
			return true;
		} else {
			throw new CallForCodeException("NO SUCH DISASTER REGISTERED");
		}
	}

	//new
	@Override
	public Boolean saveCheckListForm(SaveChecklistFormDTO saveChecklistFormDTO) throws CallForCodeException {
		LoginMasterEntity loginMasterEntity = loginMasterRepository.findByUserName(saveChecklistFormDTO.getUserName());
		if (loginMasterEntity != null) {
			PersonChecklistDetailsEntity personChecklistDetailsEntity = personChecklistDetailsRepository.getPersonChecklistByCheckList(loginMasterEntity.getPersonMasterEntity().getPersonId(), saveChecklistFormDTO.getSubCheckLists().get(0).getSubCheckListId());
			PersonChecklistDetailsEntity personCheckListDetailsSaved = null;
			if(personChecklistDetailsEntity != null){
				for(SubChecklistFilledDTO subChecklistFilledDTO: saveChecklistFormDTO.getSubCheckLists()){
					personCheckListDetailsSaved = personChecklistDetailsRepository.getPersonChecklistByCheckList(loginMasterEntity.getPersonMasterEntity().getPersonId(), subChecklistFilledDTO.getSubCheckListId());
					personCheckListDetailsSaved.setCheckListValue(subChecklistFilledDTO.getValue());
					personChecklistDetailsRepository.save(personCheckListDetailsSaved);
				}
				
			}else{
				PersonChecklistDetailsEntity personChecklistDetailsNew = null;
				for (SubChecklistFilledDTO subChecklistFilledDTO : saveChecklistFormDTO.getSubCheckLists()) {
					personChecklistDetailsNew = new PersonChecklistDetailsEntity();
					personChecklistDetailsNew.setPersonMasterEntity(loginMasterEntity.getPersonMasterEntity());
					personChecklistDetailsNew.setCheckListDetailsEntity(checklistDetailsRepository.findOne(subChecklistFilledDTO.getSubCheckListId()));
					personChecklistDetailsNew.setCheckListValue(subChecklistFilledDTO.getValue());
					personChecklistDetailsRepository.save(personChecklistDetailsNew);
			}
			}
			return true;
		} else {
			throw new CallForCodeException("USER NOT FOUND");
		}
	}
	@Override
	public List<AnalyticsReturnDTO> getAnalytics(AnalyticsDTO analyticsDTO) {
		List<AnalyticsReturnDTO> analyticsReturnDTOs = new ArrayList<>();
		List<CheckListEntity> checkListEntities = volcanoMasterRepository.findAllChecklistsByVolcano(analyticsDTO.getVolcanoId());
		AnalyticsReturnDTO analyticsReturnDTO = null;
		List<SubAnalyticsDTO> subAnalyticsDTOs = null;
		SubAnalyticsDTO subAnalyticsDTO = null;
		for(CheckListEntity checkListEntity : checkListEntities){
			analyticsReturnDTO = new AnalyticsReturnDTO();
			analyticsReturnDTO.setChecklistId(checkListEntity.getCheckListId());
			analyticsReturnDTO.setChecklistName(checkListEntity.getCheckListName());
			long count = checkListEntity.getCheckListDetailsEntities().size();
			analyticsReturnDTO.setCount(((volcanoMasterRepository.findCountByCheckList(checkListEntity.getCheckListId(), analyticsDTO.getVolcanoId())*100)/count));
			subAnalyticsDTOs = new ArrayList<>();
			for(CheckListDetailsEntity checkListDetails : checkListEntity.getCheckListDetailsEntities()){
				subAnalyticsDTO = new SubAnalyticsDTO();
				subAnalyticsDTO.setSubchecklistId(checkListDetails.getChecklistDetailId());
				subAnalyticsDTO.setSubchecklistName(checkListDetails.getSubChecklist());
				subAnalyticsDTO.setSubCount(((volcanoMasterRepository.getPopulationCheckCount(checkListDetails.getChecklistDetailId(), analyticsDTO.getVolcanoId())*100)/count));
				subAnalyticsDTOs.add(subAnalyticsDTO);
			}
			analyticsReturnDTO.setSubAnalyticsDTO(subAnalyticsDTOs);
			analyticsReturnDTOs.add(analyticsReturnDTO);
		}


		return analyticsReturnDTOs;

	}

}
