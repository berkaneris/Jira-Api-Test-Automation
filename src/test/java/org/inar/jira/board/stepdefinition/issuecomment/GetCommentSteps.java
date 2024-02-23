package org.inar.jira.board.stepdefinition.issuecomment;

import com.google.gson.*;
import org.inar.jira.board.stepdefinition.hook.BaseSteps;
import org.inar.jira.board.utils.APIUtils;
import org.inar.jira.board.utils.ConfigManager;
import org.inar.jira.board.utils.TestDataReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;

public class GetCommentSteps extends BaseSteps {
    String getCommentEndpoint = ConfigManager.getProperty("get_comment_url");
    String commentId;
    String issueKey;
    String expectedComment;
    String actualComment;

    @And("user has a comment information")
    public void userHasACommentInformationWithTheAsIssueKey() {
        expectedComment = TestDataReader.readData2("comment.json");
        JSONObject commentObject = new JSONObject(expectedComment);
        commentId = commentObject.getString("id");
    }

    @When("the client sends a GET request to get the comment that user have")
    public void theClientSendsAGETRequestToGetAComment() {
        issueKey = APIUtils.getIssueKey();
        response = request
                .pathParam("issueIdOrKey" , issueKey)
                .pathParam("id" , commentId)
                .when()
                .get(getCommentEndpoint);

        actualComment = response.getBody().asString();
    }

    @And("the response should match with the comment information that user have")
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
