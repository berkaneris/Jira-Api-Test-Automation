package com.inar.jira_api.pojo.response.get_project;

public class Lead{
	private String accountId;
	private AvatarUrls avatarUrls;
	private String displayName;
	private String self;
	private boolean active;

	public String getAccountId(){
		return accountId;
	}

	public AvatarUrls getAvatarUrls(){
		return avatarUrls;
	}

	public String getDisplayName(){
		return displayName;
	}

	public String getSelf(){
		return self;
	}

	public boolean isActive(){
		return active;
	}
}
