package com.inar.jira_api.pojo.response.getAllUsers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvatarUrls{
	private String jsonMember48x48;
	private String jsonMember24x24;
	private String jsonMember16x16;
	private String jsonMember32x32;
@JsonProperty("48x48")
	public String getJsonMember48x48(){
		return jsonMember48x48;
	}
	@JsonProperty("24x24")

	public String getJsonMember24x24(){
		return jsonMember24x24;
	}
	@JsonProperty("16x16")

	public String getJsonMember16x16(){
		return jsonMember16x16;
	}
	@JsonProperty("32x32")

	public String getJsonMember32x32(){
		return jsonMember32x32;
	}
}
