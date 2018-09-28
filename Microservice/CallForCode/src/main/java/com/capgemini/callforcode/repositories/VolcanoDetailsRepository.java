package com.capgemini.callforcode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.callforcode.reusable.transaction.entity.VolcanoDetailsEntity;

public interface VolcanoDetailsRepository extends JpaRepository<VolcanoDetailsEntity, Long> {

}
