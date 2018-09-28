package com.capgemini.callforcode.repositories;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.callforcode.reusable.transaction.entity.AddressDetailsEntity;
import com.capgemini.callforcode.reusable.transaction.entity.CheckListDetailsEntity;
import com.capgemini.callforcode.reusable.transaction.entity.CheckListEntity;
import com.capgemini.callforcode.reusable.transaction.entity.PersonChecklistDetailsEntity;
import com.capgemini.callforcode.reusable.transaction.entity.VolcanoMasterEntity;

public interface VolcanoMasterRepository extends JpaRepository<VolcanoMasterEntity, Long> {

	@Query("select v from VolcanoMasterEntity v where v.volcanoMasterId in (select d.volcanoMasterEntity.volcanoMasterId from VolcanoCityDetails d where d.cityMasterEntity.cityId = ?1)")
	List<VolcanoMasterEntity> getVolcanoMasterByCity(long cityId);

	@Query("select v.disasterMasterEntity.checkListEntities from VolcanoMasterEntity v where v.volcanoMasterId = ?1")
	List<CheckListEntity> findAllChecklistsByVolcano(long volcanoId);

	@Query("select count(p) from PersonChecklistDetailsEntity p where p.checkListValue='Yes' and p.checkListDetailsEntity.checklistDetailId = ?1 and p.personMasterEntity in (select a.personMasterEntity from AddressDetailsEntity a where a.cityMasterEntity in (select d.cityMasterEntity from VolcanoCityDetails d where d.volcanoMasterEntity.volcanoMasterId = ?2))")
	long getPopulationCheckCount(long subChecklistId, long volacnoId);

	@Query("select count(p) from PersonChecklistDetailsEntity p where p.checkListValue='Yes' and p.checkListDetailsEntity.checkListEntity.checkListId = ?1 and p.personMasterEntity in (select a.personMasterEntity from AddressDetailsEntity a where a.cityMasterEntity in (select d.cityMasterEntity from VolcanoCityDetails d where d.volcanoMasterEntity.volcanoMasterId = ?2))")
	long findCountByCheckList(long checkListId, long volcanoId);

	@Query("select v from VolcanoMasterEntity v where v.disasterMasterEntity.disasterId=?1")
	List<VolcanoMasterEntity> findByDisaster(long disasterId);

	
}
