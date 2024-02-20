package com.inar.jira_api.pojo.request.find_users;

public class FindUsersItem{
	private String accountId;
	private AvatarUrls avatarUrls;
	private String displayName;
	private String accountType;
	private String name;
	private boolean active;
	private String self;
	private String key;

	public String getAccountId(){
		return accountId;
	}

	public AvatarUrls getAvatarUrls(){
		return avatarUrls;
	}

	public String getDisplayName(){
		return displayName;
	}

	public String getAccountType(){
		return accountType;
	}

	public String getName(){
		return name;
	}

	public boolean isActive(){
		return active;
	}

	public String getSelf(){
		return self;
	}

	public String getKey(){
		return key;
	}
}
