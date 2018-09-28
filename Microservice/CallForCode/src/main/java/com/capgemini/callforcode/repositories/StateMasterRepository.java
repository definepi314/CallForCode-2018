package com.capgemini.callforcode.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.callforcode.reusable.transaction.entity.StateMasterEntity;

public interface StateMasterRepository extends JpaRepository<StateMasterEntity, Long>{

	@Query("select s from StateMasterEntity s where s.countryMasterEntity.countryId = ?1")
	List<StateMasterEntity> findByCountry(long countryId);

	@Query("select s from StateMasterEntity s where s.stateName = ?1")
	StateMasterEntity findByStateName(String upperCase);

}
