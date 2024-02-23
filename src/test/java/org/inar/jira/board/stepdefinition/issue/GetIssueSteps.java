package org.inar.jira.board.stepdefinition.issue;

import org.inar.jira.board.stepdefinition.hook.BaseSteps;
import org.inar.jira.board.utils.APIUtils;
import org.inar.jira.board.utils.ConfigManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetIssueSteps extends BaseSteps {

    private static final Logger logger = LogManager.getLogger(GetIssueSteps.class);

    String getIssueEndpoint = ConfigManager.getProperty("get_issue_url");
    String issueKey;

    @When("the client sends a GET request to get an issue with {string} as issue key")
    public void theClientSendsAGETRequestToGetAnIssueWithAsIssueKey(String issueKey) {
        logger.info("Sending GET request to get issue with key: " + issueKey);
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
        logger.info("Response contains the issue data for key: " + issueKey);
    }

    @When("the client sends a GET request to get an issue")
    public void theClientSendsAGETRequestToGetAnIssue() {
        issueKey = APIUtils.getIssueKey();
        logger.info("Sending GET request to get issue with key: " + issueKey);
        response = request.contentType("application/json")
                .pathParam("issueIdOrKey", issueKey)
                .when()
                .get(getIssueEndpoint);
    }
}
