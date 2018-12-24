package com.hcl.hackathon.fullstack.auth.privilege.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hcl.hackathon.fullstack.Privilege;
import com.hcl.hackathon.fullstack.auth.privilege.repositories.PrivilegeRepository;

@Component
public class BootStrapData implements CommandLineRunner{
	private final PrivilegeRepository privilegeRepository;

	public BootStrapData(PrivilegeRepository privilegeRepository) {
		super();
		this.privilegeRepository = privilegeRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Saving data");
		privilegeRepository.save(new Privilege("Add User", "Adds a new User"));
		privilegeRepository.save(new Privilege("Delete User", "Deletes a User"));
		privilegeRepository.save(new Privilege("Modify User", "Modifies a User"));
		privilegeRepository.save(new Privilege("View Users", "Shows the list of all users"));
		System.out.println("Finished");
	}
}
