package com.inar.jira_api.pojo.response.get_project;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Roles {
    private String atlassianAddonsProjectAccess;
    private String administrator;
    private String viewer;
    private String member;

    @JsonProperty("atlassian-addons-project-access")
    public String getAtlassianAddonsProjectAccess() {
        return atlassianAddonsProjectAccess;
    }

    public String getAdministrator() {
        return administrator;
    }

    public String getViewer() {
        return viewer;
    }

    public String getMember() {
        return member;
    }
}
