package com.inar.jira_api.pojo.response.get_project;

public class IssueTypesItem{
	private int avatarId;
	private int hierarchyLevel;
	private String name;
	private String self;
	private String description;
	private String id;
	private String iconUrl;
	private boolean subtask;

	public int getAvatarId(){
		return avatarId;
	}

	public int getHierarchyLevel(){
		return hierarchyLevel;
	}

	public String getName(){
		return name;
	}

	public String getSelf(){
		return self;
	}

	public String getDescription(){
		return description;
	}

	public String getId(){
		return id;
	}

	public String getIconUrl(){
		return iconUrl;
	}

	public boolean isSubtask(){
		return subtask;
	}
}
