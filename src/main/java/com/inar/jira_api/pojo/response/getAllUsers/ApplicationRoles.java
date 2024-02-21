package com.inar.jira_api.pojo.response.getAllUsers;

import java.util.List;

public class ApplicationRoles{
	private int size;
	private List<Object> items;

	public int getSize(){
		return size;
	}

	public List<Object> getItems(){
		return items;
	}
}