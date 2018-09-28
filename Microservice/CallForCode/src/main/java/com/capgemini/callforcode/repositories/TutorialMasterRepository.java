package com.capgemini.callforcode.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.callforcode.reusable.transaction.entity.TutorialMasterEntity;

public interface TutorialMasterRepository extends JpaRepository<TutorialMasterEntity, Long> {
	
	@Query("select t from TutorialMasterEntity t where t.disasterMasterEntity.disasterId = ?2 and ?1 in "
			+ "(select p from PersonMasterEntity p where p in "
			+ "(select a.personMasterEntity from AddressDetailsEntity a where a.cityMasterEntity in "
			+ "(select v.cityMasterEntity from VolcanoCityDetails v where v.volcanoMasterEntity.disasterMasterEntity.disasterId = ?2)))")
	List<TutorialMasterEntity> getTutorialByUser(long personId, long disasterId);

}
