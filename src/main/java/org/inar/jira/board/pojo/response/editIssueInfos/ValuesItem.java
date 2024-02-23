package org.inar.jira.board.pojo.response.editIssueInfos;

import java.util.List;

public class ValuesItem{
	private Author author;
	private String created;
	private String id;
	private List<ItemsItem> items;

	public Author getAuthor(){
		return author;
	}

	public String getCreated(){
		return created;
	}

	public String getId(){
		return id;
	}

	public List<ItemsItem> getItems(){
		return items;
	}
}