package com.capgemini.callforcode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.callforcode.reusable.transaction.entity.RegistrationMasterEntity;

public interface RegistrationMasterRepository extends JpaRepository<RegistrationMasterEntity, Long> {

}
