package com.inar.jira_api.pojo.response.editIssueInfos;

public class Author{
	private String accountId;
	private String emailAddress;
	private AvatarUrls avatarUrls;
	private String displayName;
	private String accountType;
	private String self;
	private boolean active;
	private String timeZone;

	public String getAccountId(){
		return accountId;
	}

	public String getEmailAddress(){
		return emailAddress;
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

	public String getSelf(){
		return self;
	}

	public boolean isActive(){
		return active;
	}

	public String getTimeZone(){
		return timeZone;
	}
}
