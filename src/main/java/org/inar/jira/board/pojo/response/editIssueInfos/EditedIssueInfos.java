package org.inar.jira.board.pojo.response.editIssueInfos;

import java.util.List;

public class EditedIssueInfos {
	private int total;
	private boolean isLast;
	private int maxResults;
	private List<ValuesItem> values;
	private String self;
	private int startAt;

	public int getTotal(){
		return total;
	}

	public boolean isIsLast(){
		return isLast;
	}

	public int getMaxResults(){
		return maxResults;
	}

	public List<ValuesItem> getValues(){
		return values;
	}

	public String getSelf(){
		return self;
	}

	public int getStartAt(){
		return startAt;
	}
}