package com.bean;

public class DormAdmin {
	private String dormAdmID;
	private String name;
	private String password;
	private String buildingName;
	private String sex;
	
	public DormAdmin(String dormAdmID, String name, String password,
			String buildingName, String sex) {
		super();
		this.dormAdmID = dormAdmID;
		this.name = name;
		this.password = password;
		this.buildingName = buildingName;
		this.sex = sex;
	}

	public DormAdmin() {
		super();
	}

	public String getDormAdmID() {
		return dormAdmID;
	}

	public void setDormAdmID(String dormAdmID) {
		this.dormAdmID = dormAdmID;
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

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
