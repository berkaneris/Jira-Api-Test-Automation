package com.inar.jira_api_test.stepdefinition;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.inar.jira_api.utils.APIUtils;
import com.inar.jira_api.utils.ConfigManager;
import com.inar.jira_api.utils.TestDataReader;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;


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
