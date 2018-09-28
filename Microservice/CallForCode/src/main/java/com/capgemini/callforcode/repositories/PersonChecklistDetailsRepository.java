package com.capgemini.callforcode.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.callforcode.reusable.transaction.entity.PersonChecklistDetailsEntity;

public interface PersonChecklistDetailsRepository extends JpaRepository<PersonChecklistDetailsEntity, Long> {
	
	@Query("select p from PersonChecklistDetailsEntity p where p.personMasterEntity.personId = ?1")
	List<PersonChecklistDetailsEntity> getPersonCheckListDetails(long personId);
	
	@Query("select p.checkListValue from PersonChecklistDetailsEntity p where p.personMasterEntity.personId = ?1 and p.checkListDetailsEntity.checklistDetailId  =?2 ")
	String getSubcheckListValue(long personId, long subCheckListId);

	@Query("select p from PersonChecklistDetailsEntity p where p.personMasterEntity.personId = ?1 and p.checkListDetailsEntity.checklistDetailId=?2")
	PersonChecklistDetailsEntity getPersonChecklistByCheckList(long personId, Long subCheckListId);

}
