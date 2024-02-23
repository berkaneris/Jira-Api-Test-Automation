package com.inar.jira_api.pojo.request.editIssue;

import lombok.Data;

@Data
public class EditIssue {
	private HistoryMetaData historyMetaData;
	private Update update;
	private Fields fields;



	public EditIssue(Update update) {
		this.update = update;
	}
}
