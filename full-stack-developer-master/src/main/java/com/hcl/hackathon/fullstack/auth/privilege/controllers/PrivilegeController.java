package com.hcl.hackathon.fullstack.auth.privilege.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hackathon.fullstack.Privilege;
import com.hcl.hackathon.fullstack.auth.privilege.services.PrivilegeService;

@RestController
@RequestMapping(PrivilegeController.BASE_URL)
public class PrivilegeController {
	public static final String BASE_URL = "/api/v1/privileges";

	private final PrivilegeService privilegeService;

	public PrivilegeController(PrivilegeService privilegeService) {//Question - At present only one impl exists so no
		super();													//confusion which impl to pick. If i have 2 impl?
		this.privilegeService = privilegeService;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping//alternatively can use @RequestMapping but get mapping is more convenient
	List<Privilege> getAllPrivileges(){
		return privilegeService.findAllPrivileges();
	}
}
