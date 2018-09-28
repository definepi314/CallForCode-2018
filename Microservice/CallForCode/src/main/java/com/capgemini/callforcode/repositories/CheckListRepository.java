package com.capgemini.callforcode.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.callforcode.reusable.transaction.entity.CheckListEntity;

public interface CheckListRepository extends JpaRepository<CheckListEntity, Long>{
	
	@Query("select c from CheckListEntity c where c.disasterMasterEntity.disasterId in "
			+ "(select v.volcanoMasterEntity.disasterMasterEntity.disasterId from VolcanoCityDetails v where v.cityMasterEntity.cityId in "
			+ "(select a.cityMasterEntity.cityId from AddressDetailsEntity a where a.personMasterEntity.personId = ?1) )")
	List<CheckListEntity> getChecklistByUser(long personId);
	

}
