package com.capgemini.callforcode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.callforcode.reusable.transaction.entity.CheckListDetailsEntity;

public interface CheckListDetailsRepository extends JpaRepository<CheckListDetailsEntity, Long> {

}
