package com.inar.jira_api_test.stepdefinition;

import com.google.gson.JsonObject;
import com.inar.jira_api.utils.APIUtils;
import com.inar.jira_api.utils.ConfigManager;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import java.util.Map;

public class UpdateCommentSteps extends BaseSteps {

    String updateCommentEndpoint = ConfigManager.getProperty("update_comment_url");

    JsonObject commentPayload;

    String commentText;
    String issueKey;
    String commentId;
    @When("the client sets the request body to update a issue comment")
    public void theClientSetsTheRequestBodyToUpdateAIssueComment(DataTable dataTable) {
        Map<String,String> commentDetails = dataTable.asMap(String.class, String.class);
        commentPayload = APIUtils.createCommentRequestBody(commentDetails);
        commentText = commentDetails.get("conContentText");
    }

    @And("the client sends a PUT request to update an issue comment")
    public void theClientSendsAPUTRequestToUpdateAnIssueComment() {
        issueKey = APIUtils.getIssueKey();
        commentId = APIUtils.getCommentId();
        response = request
                .pathParam("issueIdOrKey" , issueKey )
                .pathParam("id" , commentId)
                .contentType("application/json")
                .accept("application/json")
                .body(String.valueOf(commentPayload))
                .when()
                .put(updateCommentEndpoint);

    }
}
