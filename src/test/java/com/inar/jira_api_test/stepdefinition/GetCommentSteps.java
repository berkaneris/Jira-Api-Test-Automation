package com.inar.jira_api_test.stepdefinition;

import com.google.gson.*;
import com.inar.jira_api.utils.ConfigManager;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class GetCommentSteps extends BaseSteps {
    String getCommentEndpoint = ConfigManager.getProperty("get_comment_url");
    String commentId;
    String issueKey;
    String expectedComment;
    String actualComment;

    @And("user has a comment information with the {string} as issue key")
    public void userHasACommentInformationWithTheAsIssueKey(String issueKey) {
        response = request
                .pathParam("issueIdOrKey" , issueKey)
                .when()
                .get("/rest/api/3/issue/{issueIdOrKey}/comment");


        // Parse the response body String into a JsonObject using Gson
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.getBody().asString(), JsonObject.class);

        JsonArray comments = jsonObject.getAsJsonArray("comments");
        JsonObject comment = comments.get(0).getAsJsonObject();

        this.issueKey = issueKey;
        commentId = comment.get("id").getAsString();
        expectedComment = comment.toString();
    }

    @When("the client sends a GET request to get a comment")
    public void theClientSendsAGETRequestToGetAComment() {
        response = request
                .pathParam("issueIdOrKey" , issueKey)
                .pathParam("id" , commentId)
                .when()
                .get(getCommentEndpoint);

        actualComment = response.getBody().asString();
    }

    @And("the response should contain comment information")
    public void theResponseShouldContainCommentInformation() {
        JsonElement actualJson = JsonParser.parseString(actualComment);
        JsonElement expectedJson = JsonParser.parseString(expectedComment);
        Assertions.assertThat(actualJson).isEqualTo(expectedJson);
    }

    @When("the client sends a GET request to get a comment with {string} as issue key and {string} as comment id")
    public void theClientSendsAGETRequestToGetACommentWithAsIssueKeyAndAsCommentId(String issueKey, String commentId) {
        response = request
                .pathParam("issueIdOrKey" , issueKey)
                .pathParam("id" , commentId)
                .when()
                .get(getCommentEndpoint);
    }


}
