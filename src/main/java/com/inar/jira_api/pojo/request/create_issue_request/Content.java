package com.inar.jira_api.pojo.request.create_issue_request;

import java.util.List;

public class Content {

    private String type;
    private List<Text> content;

    public Content() {
    }

    public Content(String type, List<Text> content) {
        this.type = type;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Text> getContent() {
        return content;
    }

    public void setContent(List<Text> content) {
        this.content = content;
    }
}
