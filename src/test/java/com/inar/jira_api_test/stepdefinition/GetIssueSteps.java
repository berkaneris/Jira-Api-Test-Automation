package com.inar.jira_api_test.stepdefinition;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inar.jira_api.utils.ConfigManager;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class GetIssueSteps extends BaseSteps {

    String getIssueEndpoint = ConfigManager.getProperty("get_issue_url");

    @When("the client sends a GET request to get an issue with {string} as issue key")
    public void theClientSendsAGETRequestToGetAnIssueWithAsIssueKey(String issueKey) {
        response = request.contentType("application/json")
                .pathParam("issueIdOrKey", issueKey)
                .when()
                .get(getIssueEndpoint);
    }


    @And("the response should contain the issue data")
    public void theResponseShouldContainTheIssueData() {
        String actualJsonResponse = response.getBody().asString();
        String expectedJsonResponse;
        File file = new File("src/test/resources/features/testdata/getIssueResponseBody.json");
        try {
            expectedJsonResponse = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JsonElement actualJson = JsonParser.parseString(actualJsonResponse);
        JsonElement expectedJson = JsonParser.parseString(expectedJsonResponse);

        Assertions.assertThat(actualJson).isEqualTo(expectedJson);


    }
}
