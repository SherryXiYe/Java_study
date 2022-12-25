package com.bean;

public enum UserType {
	SYSTEMADMIN("系统管理员",0),
	DORMADMIN("宿舍管理员",1),
	STUDENT("学生",2);
	
	private String type;
	private int index;
	
	private UserType(String type,int index){
		this.index=index;
		this.type=type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public String toString(){
		return type;
	}
	
}
