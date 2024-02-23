package org.inar.jira.board.pojo.response.getAllUsers;

public class GetAllUsers {
	private String accountId;
	private String emailAddress;
	private String expand;
	private AvatarUrls avatarUrls;
	private String displayName;
	private String accountType;
	private String self;
	private boolean active;
	private String timeZone;
	private Groups groups;
	private String locale;
	private ApplicationRoles applicationRoles;

	public String getAccountId(){
		return accountId;
	}

	public String getEmailAddress(){
		return emailAddress;
	}

	public String getExpand(){
		return expand;
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

	public Groups getGroups(){
		return groups;
	}

	public String getLocale(){
		return locale;
	}

	public ApplicationRoles getApplicationRoles(){
		return applicationRoles;
	}
}
