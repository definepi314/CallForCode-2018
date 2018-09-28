package com.capgemini.callforcode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.callforcode.reusable.transaction.entity.PersonMasterEntity;

public interface PersonMasterRepository extends JpaRepository<PersonMasterEntity, Long>{

	@Query("select p from PersonMasterEntity p where p.email = ?1")
	PersonMasterEntity findByEmail(String email);

}
