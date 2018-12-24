package com.hcl.hackathon.fullstack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Privilege {
	
	@Id
	@GeneratedValue
	private Long privilegeId;
	private String privilegeName;
	private String privilegeDescription;

	public Privilege() {//JPA requirement
		super();
	}

	public Privilege(String privilegeName, String privilegeDescription) {
		super();
		this.privilegeName = privilegeName;
		this.privilegeDescription = privilegeDescription;
	}
	
	public String getPrivilegeName() {
		return privilegeName;
	}
	
	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}
	
	public String getPrivilegeDescription() {
		return privilegeDescription;
	}
	
	public void setPrivilegeDescription(String privilegeDescription) {
		this.privilegeDescription = privilegeDescription;
	}
	
	public Long getPrivilegeId() {
		return privilegeId;
	}

	@Override
	public String toString() {
		return "Privilege [privilegeId=" + privilegeId + ", privilegeName=" + privilegeName + ", privilegeDescription="
				+ privilegeDescription + "]";
	}
}

