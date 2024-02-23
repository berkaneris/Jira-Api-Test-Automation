package com.inar.jira_api.pojo.response.get_project;

import java.util.List;

public class GetProject {
	private List<Object> components;
	private AvatarUrls avatarUrls;
	private Roles roles;
	private String description;
	private String entityId;
	private boolean isPrivate;
	private String uuid;
	private Lead lead;
	private List<IssueTypesItem> issueTypes;
	private String expand;
	private boolean simplified;
	private List<Object> versions;
	private String name;
	private String self;
	private String style;
	private String id;
	private String assigneeType;
	private String projectTypeKey;
	private String key;
	private Properties properties;

	public List<Object> getComponents(){
		return components;
	}

	public AvatarUrls getAvatarUrls(){
		return avatarUrls;
	}

	public Roles getRoles(){
		return roles;
	}

	public String getDescription(){
		return description;
	}

	public String getEntityId(){
		return entityId;
	}

	public boolean isIsPrivate(){
		return isPrivate;
	}

	public String getUuid(){
		return uuid;
	}

	public Lead getLead(){
		return lead;
	}

	public List<IssueTypesItem> getIssueTypes(){
		return issueTypes;
	}

	public String getExpand(){
		return expand;
	}

	public boolean isSimplified(){
		return simplified;
	}

	public List<Object> getVersions(){
		return versions;
	}

	public String getName(){
		return name;
	}

	public String getSelf(){
		return self;
	}

	public String getStyle(){
		return style;
	}

	public String getId(){
		return id;
	}

	public String getAssigneeType(){
		return assigneeType;
	}

	public String getProjectTypeKey(){
		return projectTypeKey;
	}

	public String getKey(){
		return key;
	}

	public Properties getProperties(){
		return properties;
	}
}