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

import java.util.Map;

public class UpdateCommentSteps extends BaseSteps {

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
    }

    @And("the client sends a PUT request to update an issue comment")
    public void theClientSendsAPUTRequestToUpdateAnIssueComment() {
        issueKey = APIUtils.getIssueKey();
        commentId = APIUtils.getCommentId();
        response = request
                .pathParam("issueIdOrKey" , issueKey )
                .pathParam("id" , commentId)
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
        TestDataWriter.writeData2(response.getBody().asString() , "comment.json");
        softAssertions.assertAll();
    }
}
