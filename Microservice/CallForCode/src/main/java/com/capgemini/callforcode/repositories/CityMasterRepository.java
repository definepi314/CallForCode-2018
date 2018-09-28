package com.capgemini.callforcode.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.callforcode.reusable.transaction.entity.CityMasterEntity;

public interface CityMasterRepository extends JpaRepository<CityMasterEntity, Long>{

	@Query("select c from CityMasterEntity c where c.stateMasterEntity.stateId = ?1")
	List<CityMasterEntity> findByState(long stateId);

	@Query("select c from CityMasterEntity c where c.cityName = ?1")
	CityMasterEntity findBYCityName(String upperCase);

}
