package com.capgemini.callforcode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.callforcode.reusable.transaction.entity.LoginMasterEntity;

public interface LoginMasterRepository extends JpaRepository<LoginMasterEntity, Long> {

	@Query("SELECT l FROM LoginMasterEntity l where l.userName = ?1 and l.password = ?2")
	public LoginMasterEntity findByUserNameAndPassword(String username, String password);
	
	@Query("SELECT l FROM LoginMasterEntity l where l.userName = ?1")
	public LoginMasterEntity findByUserName(String username);

}
