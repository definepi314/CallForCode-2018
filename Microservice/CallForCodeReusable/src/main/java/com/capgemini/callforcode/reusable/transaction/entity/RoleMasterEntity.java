package com.capgemini.callforcode.reusable.transaction.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ROLE_MASTER")
public class RoleMasterEntity extends BasicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_ID")
	private long roleId;

	@Column(name = "ROLE_NAME")
	private String roleName;

	@OneToMany(mappedBy = "roleMasterEntity", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PersonRoleEntity> personRoleEntities;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<PersonRoleEntity> getPersonRoleEntities() {
		return personRoleEntities;
	}

	public void setPersonRoleEntities(List<PersonRoleEntity> personRoleEntities) {
		this.personRoleEntities = personRoleEntities;
	}

}
