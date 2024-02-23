package org.inar.jira.board.stepdefinition.issuecomment;

import com.google.gson.JsonObject;
import org.inar.jira.board.stepdefinition.hook.BaseSteps;
import org.inar.jira.board.utils.APIUtils;
import org.inar.jira.board.utils.ConfigManager;
import org.inar.jira.board.utils.TestDataWriter;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;

public class UpdateCommentSteps extends BaseSteps {
    private static final Logger logger = LogManager.getLogger(UpdateCommentSteps.class);

    String updateCommentEndpoint = ConfigManager.getProperty("update_comment_url");
    JsonObject commentPayload;
    String commentText;
    String issueKey;
    String commentId;

    @When("the client sets the request body to update a issue comment")
    public void theClientSetsTheRequestBodyToUpdateAIssueComment(DataTable dataTable) {
        Map<String,String> commentDetails = dataTable.asMap(String.class, String.class);
        commentPayload = APIUtils.createCommentRequestBody(commentDetails);
        commentText = commentDetails.get("conContentText");
        logger.info("Request body set to update an issue comment: {}", commentPayload);
    }

    @And("the client sends a PUT request to update an issue comment")
    public void theClientSendsAPUTRequestToUpdateAnIssueComment() {
        issueKey = APIUtils.getIssueKey();
        commentId = APIUtils.getCommentId();
        logger.info("Sending PUT request to update issue comment with issue key '{}' and comment id '{}'", issueKey, commentId);

        response = request
                .pathParam("issueIdOrKey", issueKey)
                .pathParam("id", commentId)
                .contentType("application/json")
                .accept("application/json")
                .body(String.valueOf(commentPayload))
                .when()
                .put(updateCommentEndpoint);
    }

    @And("the response should contain updated comment details")
    public void theResponseShouldContainUpdatedCommentDetails() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response.jsonPath().getString("body.content.content.text").contains(commentText)).isTrue();
        TestDataWriter.writeData2(response.getBody().asString(), "Comment.json");
        softAssertions.assertAll();
        logger.info("Response contains updated comment details: {}", response.getBody().asString());
    }
}
