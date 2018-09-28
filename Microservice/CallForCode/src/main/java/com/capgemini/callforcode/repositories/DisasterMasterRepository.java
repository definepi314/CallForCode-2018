package com.capgemini.callforcode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.callforcode.reusable.transaction.entity.DisasterMasterEntity;

public interface DisasterMasterRepository extends JpaRepository<DisasterMasterEntity, Long> {

	@Query("select d from DisasterMasterEntity d where d.disasterName=?1")
	DisasterMasterEntity findByName(String volcano);

}
