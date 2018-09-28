package com.capgemini.callforcode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.callforcode.reusable.transaction.entity.VolcanoCityDetails;

public interface VolcanoCityDetailsRepository extends JpaRepository<VolcanoCityDetails, Long>{

	@Query("select v from VolcanoCityDetails v where v.volcanoMasterEntity.volcanoMasterId = ?1 and v.cityMasterEntity.cityId = ?2")
	VolcanoCityDetails findByVolcanoAndCity(long volcanoMasterId, long cityId);

}
