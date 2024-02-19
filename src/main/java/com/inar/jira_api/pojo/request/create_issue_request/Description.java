package com.inar.jira_api.pojo.request.create_issue_request;

import java.util.List;

public class Description{
	private String type;
	private int version;
	private List<Content> content;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<Content> getContent() {
		return content;
	}

	public void setContent(List<Content> content) {
		this.content = content;
	}
}