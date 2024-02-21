package com.inar.jira_api_test.stepdefinition;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.inar.jira_api.utils.ConfigManager;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;

import java.util.Map;

public class AddCommentSteps extends BaseSteps {

    String addCommentEndpoint = ConfigManager.getProperty("add_comment_url");
    JsonObject commentPayload = new JsonObject();

    @When("the client sets the request body to add an issue comment")
    public void theClientSetsTheRequestBodyToAddAnIssueComment(DataTable dataTable) {
        Map<String,String> commentDetails = dataTable.asMap(String.class, String.class);
        JsonObject body = new JsonObject();
        JsonObject content = new JsonObject();
        JsonObject innerContent = new JsonObject();

        innerContent.addProperty("text" , commentDetails.get("conContentText"));
        innerContent.addProperty("type" , commentDetails.get("conContentType"));

        JsonArray array = new JsonArray();
        array.add(innerContent);
        content.add("content" , array);
        content.addProperty("type" , commentDetails.get("contentType"));

        array = new JsonArray();
        array.add(content);
        body.add("content" , array);
        body.addProperty("type" , commentDetails.get("type"));
        body.addProperty("version" , Integer.parseInt(commentDetails.get("version")));

        commentPayload.add("body" ,body);
    }

    @When("the client sends a POST request to add an issue comment with {string} as issue id")
    public void theClientSendsAPOSTRequestToAddAnIssueCommentWithAsIssueId(String issueId) {
        response = request
                .pathParam("issueIdOrKey" , issueId)
                .contentType("application/json")
                .body(String.valueOf(commentPayload))
                .when()
                .post(addCommentEndpoint);
    }
}
