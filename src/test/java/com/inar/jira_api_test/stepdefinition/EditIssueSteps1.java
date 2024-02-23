package com.inar.jira_api_test.stepdefinition;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.inar.jira_api.utils.ConfigManager;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import java.util.Map;

public class EditIssueSteps1 extends BaseSteps {
    String editIssueEndpoint = ConfigManager.getProperty("edit_issue_url");
    JsonObject editIssueReq = new JsonObject();
    @When("the client sets the request body to edit an issue")
    public void theClientSetsTheRequestBodyToEditAnIssue(DataTable dataTable) {
        Map<String,String> editDetails = dataTable.asMap(String.class , String.class);
        JsonObject fields = new JsonObject();
        JsonObject update = new JsonObject();
        JsonArray labels = new JsonArray();
        JsonArray summary = new JsonArray();
        JsonObject labelObject1 = new JsonObject();
        JsonObject labelObject2 = new JsonObject();
        JsonObject labelObject3 = new JsonObject();
        JsonObject summaryObject = new JsonObject();


        labelObject1.addProperty("add" , editDetails.get("add"));
        labelObject2.addProperty("remove" , editDetails.get("remove1"));
        labelObject3.addProperty("remove" , editDetails.get("remove2"));
        labels.add(labelObject1);
        labels.add(labelObject2);
        labels.add(labelObject3);

        summaryObject.addProperty("set" , editDetails.get("set"));
        summary.add(summaryObject);

        update.add("labels" , labels);
        update.add("summary" , summary);

        editIssueReq.add("fields" , fields);
        editIssueReq.add("update" , update);
    }

    @And("the client sends a PUT request to the api with {string} as issue key")
    public void theClientSendsAPUTRequestToTheApiWithAsIssueKey(String issueKey) {
        response = request
                .pathParam("issueIdOrKey" , issueKey)
                .contentType("application/json")
                .body(String.valueOf(editIssueReq))
                .when()
                .put(editIssueEndpoint);
    }


}
