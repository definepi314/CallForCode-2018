package com.capgemini.callforcode.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.callforcode.reusable.transaction.entity.PersonTutorialDetailsEntity;

public interface PersonTutorialDetailsRepository extends JpaRepository<PersonTutorialDetailsEntity, Long> {

	@Query("select p.tutorialMasterEntity.videoLink from PersonTutorialDetailsEntity p where p.personMasterEntity.personId = ?1")
	List<String> getTutorialByUser(long personId);

}
