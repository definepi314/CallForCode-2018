package com.capgemini.callforcode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.callforcode.reusable.transaction.entity.CountryMasterEntity;

public interface CountryMasterRepository extends JpaRepository<CountryMasterEntity, Long>{

	@Query("select c from CountryMasterEntity c where c.countryName = ?1")
	CountryMasterEntity findByCountryName(String upperCase);

}
