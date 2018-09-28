package com.capgemini.callforcode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.callforcode.reusable.transaction.entity.AddressDetailsEntity;

public interface AddressDetailsRepository extends JpaRepository<AddressDetailsEntity, Long>{

}
