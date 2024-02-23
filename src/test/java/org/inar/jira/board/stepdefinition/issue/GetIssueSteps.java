package org.inar.jira.board.stepdefinition.issue;

import org.inar.jira.board.stepdefinition.hook.BaseSteps;
import org.inar.jira.board.utils.APIUtils;
import org.inar.jira.board.utils.ConfigManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;


public class GetIssueSteps extends BaseSteps {

    String getIssueEndpoint = ConfigManager.getProperty("get_issue_url");
    String issueKey;
    @When("the client sends a GET request to get an issue with {string} as issue key")
    public void theClientSendsAGETRequestToGetAnIssueWithAsIssueKey(String issueKey) {
        response = request.contentType("application/json")
                .pathParam("issueIdOrKey", issueKey)
                .when()
                .get(getIssueEndpoint);
    }


    @And("the response should contain the issue data")
    public void theResponseShouldContainTheIssueData() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response.jsonPath().getString("key")).isEqualTo(issueKey);
        softAssertions.assertAll();
    }

    @When("the client sends a GET request to get an issue")
    public void theClientSendsAGETRequestToGetAnIssue() {
        issueKey = APIUtils.getIssueKey();
        response = request.contentType("application/json")
                .pathParam("issueIdOrKey", issueKey)
                .when()
                .get(getIssueEndpoint);

    }
}
