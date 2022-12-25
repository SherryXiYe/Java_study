package com.bean;

public class SystemAdmin {
	private String ID;
	private String name;
	private String password;
	
	public SystemAdmin(String iD, String name, String password) {
		super();
		ID = iD;
		this.name = name;
		this.password = password;
	}
	
	public SystemAdmin() {
		super();
	}
	
	public SystemAdmin(String iD) {
		super();
		ID = iD;
	}
	
	public SystemAdmin(String iD, String password) {
		super();
		ID = iD;
		this.password = password;
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
