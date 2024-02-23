package com.inar.jira_api_test.stepdefinition;

import com.inar.jira_api.utils.ConfigManager;
import com.inar.jira_api.utils.TestDataReader;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class DeleteIssueSteps1 extends BaseSteps {
    String deleteIssueEndpoint = ConfigManager.getProperty("delete_issue_url");
    String issueKey;

    @Given("user has an issue to delete")
    public void userHasAnIssueToDelete() {
        //Create an issue to delete
        response = request.contentType("application/json")
                .body(TestDataReader.readData2("creatIssueRequestBody.json"))
                .when()
                .post(ConfigManager.getProperty("create_issue_url"));
        issueKey = response.jsonPath().getString("key");
    }

    @When("user send a DELETE request to endpoint to delete issue")
    public void userSendADELETERequestToEndpointToDeleteIssue() {
        new CommonSteps().theAPIRequestsAreAuthenticatedWithSystemPropertiesForUsernameAndToken();
        response = request
                .pathParam("issueIdOrKey", issueKey)
                .when()
                .delete(deleteIssueEndpoint);
    }

    @And("check the issue is deleted")
    public void checkTheIssueIsDeleted() {
        new CommonSteps().theAPIRequestsAreAuthenticatedWithSystemPropertiesForUsernameAndToken();
        response = request.contentType("application/json")
                .pathParam("issueIdOrKey", issueKey)
                .when()
                .get(ConfigManager.getProperty("get_issue_url"));
        Assertions.assertThat(response.statusCode()).isEqualTo(404);
    }
}
