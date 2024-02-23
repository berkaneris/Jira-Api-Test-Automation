package com.inar.jira_api.pojo.response.editIssueInfos;

public class ItemsItem{
	private String field;
	private Object from;
	private String toString;
	private String fromString;
	private Object to;
	private String fieldtype;
	private String fieldId;

	public String getField(){
		return field;
	}

	public Object getFrom(){
		return from;
	}

	public String getToString(){
		return toString;
	}

	public String getFromString(){
		return fromString;
	}

	public Object getTo(){
		return to;
	}

	public String getFieldtype(){
		return fieldtype;
	}

	public String getFieldId(){
		return fieldId;
	}
}
