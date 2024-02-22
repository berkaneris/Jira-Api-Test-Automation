package com.inar.jira_api_test.stepdefinition;

import com.inar.jira_api.utils.ConfigManager;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

public class DeleteCommentSteps extends BaseSteps {

    String deleteCommentEndpoint = ConfigManager.getProperty("delete_comment_url");
    @When("the client sends a DELETE request to delete an issue comment with {string} as issue id and {string} as comment id")
    public void theClientSendsADELETERequestToDeleteAnIssueCommentWithAsIssueIdAndAsCommentId(String issueId, String commentId) {
        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("issueIdOrKey" , issueId);
        pathParameters.put("id" , commentId);

        response = request
                .pathParams(pathParameters)
                .when()
                .delete(deleteCommentEndpoint);
    }
}
