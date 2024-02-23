package org.inar.jira.board.stepdefinition.issuecomment;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.inar.jira.board.stepdefinition.hook.BaseSteps;
import org.inar.jira.board.utils.APIUtils;
import org.inar.jira.board.utils.ConfigManager;
import org.inar.jira.board.utils.TestDataReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class GetCommentSteps extends BaseSteps {
    private static final Logger logger = LogManager.getLogger(GetCommentSteps.class);

    String getCommentEndpoint = ConfigManager.getProperty("get_comment_url");
    String commentId;
    String issueKey;
    String expectedComment;
    String actualComment;

    @And("user has a comment information")
    public void userHasACommentInformationWithTheAsIssueKey() {
        expectedComment = TestDataReader.readData2("Comment.json");
        JSONObject commentObject = new JSONObject(expectedComment);
        commentId = commentObject.getString("id");
        logger.info("Retrieved comment information with ID '{}'", commentId);
    }

    @When("the client sends a GET request to get the comment that user have")
    public void theClientSendsAGETRequestToGetAComment() {
        issueKey = APIUtils.getIssueKey();
        logger.info("Sending GET request to get the comment for issue key '{}' and comment ID '{}'", issueKey, commentId);
        response = request
                .pathParam("issueIdOrKey", issueKey)
                .pathParam("id", commentId)
                .when()
                .get(getCommentEndpoint);

        actualComment = response.getBody().asString();
        logger.info("Received response for comment retrieval: {}", actualComment);
    }

    @And("the response should match with the comment information that user have")
    public void theResponseShouldContainCommentInformation() {
        logger.info("Verifying response against expected comment information");
        JsonElement actualJson = JsonParser.parseString(actualComment);
        JsonElement expectedJson = JsonParser.parseString(expectedComment);
        Assertions.assertThat(actualJson).isEqualTo(expectedJson);
        logger.info("Response matches with expected comment information");
    }

    @When("the client sends a GET request to get a comment with {string} as issue key and {string} as comment id")
    public void theClientSendsAGETRequestToGetACommentWithAsIssueKeyAndAsCommentId(String issueKey, String commentId) {
        logger.info("Sending GET request to get the comment for issue key '{}' and comment ID '{}'", issueKey, commentId);
        response = request
                .pathParam("issueIdOrKey", issueKey)
                .pathParam("id", commentId)
                .when()
                .get(getCommentEndpoint);
    }
}
