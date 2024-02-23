package org.inar.jira.board.pojo.response.create_issue_response;

public class CreateIssueRes {
	private String self;
	private String id;
	private String key;

	public CreateIssueRes() {
	}

	public CreateIssueRes(String self, String id, String key) {
		this.self = self;
		this.id = id;
		this.key = key;
	}

	public void setSelf(String self){
		this.self = self;
	}

	public String getSelf(){
		return self;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setKey(String key){
		this.key = key;
	}

	public String getKey(){
		return key;
	}
}
