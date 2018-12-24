package com.hcl.hackathon.fullstack.auth.privilege.services;

import java.util.List;

import com.hcl.hackathon.fullstack.Privilege;

public interface PrivilegeService {

	Privilege findPrivilegeById(Long id);
	List<Privilege> findAllPrivileges();
}
