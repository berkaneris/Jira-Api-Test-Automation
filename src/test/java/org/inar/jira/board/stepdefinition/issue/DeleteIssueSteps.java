package org.inar.jira.board.stepdefinition.issue;

import org.inar.jira.board.stepdefinition.CommonSteps;
import org.inar.jira.board.stepdefinition.hook.BaseSteps;
import org.inar.jira.board.utils.ConfigManager;
import org.inar.jira.board.utils.TestDataReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class DeleteIssueSteps extends BaseSteps {

    String deleteIssueEndpoint = ConfigManager.getProperty("delete_issue_url");
    String issueKey;


    @When("Get ID of cretead of issue and delete the issue")
    public void getIDOfCreteadOfIssueAndDeleteTheIssue() {
    response = request.pathParam("issueIdOrKey", CreateIssueSteps.idOfCreatedIssue).delete("/rest/api/3/issue/{issueIdOrKey}");
    }

    @When("I give an invalid issue ID and post DELETE method")
    public void ıGiveAnInvalidIssueIDAndPostDELETEMethod() {
        response = request.pathParam("issueIdOrKey", "rafta-98745").delete("/rest/api/3/issue/{issueIdOrKey}");
    }

    @And("I should take an error message")
    public void ıShouldTakeAnErrorMessage() {
        Assertions.assertThat(response.jsonPath().getString("errorMessages")).isNotEmpty();
    }

    @Given("user has an issue to delete")
    public void userHasAnIssueToDelete() {
        //Create an issue to delete
        response = request.contentType("application/json")
                .body(TestDataReader.readData2("CreatIssueRequestBody.json"))
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
