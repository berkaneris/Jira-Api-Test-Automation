package com.inar.jira_api_test.stepdefinition;

import com.inar.jira_api.utils.APIUtils;
import com.inar.jira_api.utils.ConfigManager;
import com.inar.jira_api.utils.TestDataReader;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DeleteCommentSteps extends BaseSteps {

    String deleteCommentEndpoint = ConfigManager.getProperty("delete_comment_url");
    String issueKey;
    String commentId;
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

    @Given("User has an issue comment to delete")
    public void userHasAnIssueCommentToDelete() {
        //Add a comment to delete
        issueKey = APIUtils.getIssueKey();
        response = request
                .pathParam("issueIdOrKey" , issueKey)
                .contentType("application/json")
                .body(TestDataReader.readData2("AddCommentRequestBody.json"))
                .when()
                .post(ConfigManager.getProperty("add_comment_url"));

        commentId = response.jsonPath().getString("id");

    }

    @When("the client sends a DELETE request to delete an issue comment")
    public void theClientSendsADELETERequestToDeleteAnIssueComment() {
        new CommonSteps().theAPIRequestsAreAuthenticatedWithSystemPropertiesForUsernameAndToken();
        response = request
                .pathParam("issueIdOrKey" ,issueKey )
                .pathParam("id" ,commentId)
                .when()
                .delete(deleteCommentEndpoint);
    }
}
