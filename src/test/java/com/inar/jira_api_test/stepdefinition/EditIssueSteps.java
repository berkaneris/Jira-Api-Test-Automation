package com.inar.jira_api_test.stepdefinition;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.inar.jira_api.utils.ConfigManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;

import java.util.Map;

public class EditIssueSteps {
    String editIssueEndpoint = ConfigManager.getProperty("edit_issue_url");
    JsonObject editIssueReq = new JsonObject();
    @When("the client sets the request body to edit an issue")
    public void theClientSetsTheRequestBodyToEditAnIssue(DataTable dataTable) {
        Map<String,String> editDetails = dataTable.asMap(String.class , String.class);
        JsonObject fields = new JsonObject();
        JsonObject update = new JsonObject();
        JsonArray labels = new JsonArray();
        JsonArray summary = new JsonArray();


        labels.add(new JsonObject().addProperty("add" , editDetails.get("add")));
    }
}
