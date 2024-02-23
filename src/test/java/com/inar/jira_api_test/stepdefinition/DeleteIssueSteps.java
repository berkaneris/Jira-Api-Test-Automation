package com.inar.jira_api_test.stepdefinition;

import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class DeleteIssueSteps extends BaseSteps {

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
}
