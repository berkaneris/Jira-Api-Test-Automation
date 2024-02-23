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

public class AddCommentSteps extends BaseSteps {

    String addCommentEndpoint = ConfigManager.getProperty("add_comment_url");
    JsonObject commentPayload;
    String commentText;

    @When("the client sets the request body to add an issue comment")
    public void theClientSetsTheRequestBodyToAddAnIssueComment(DataTable dataTable) {
        Map<String,String> commentDetails = dataTable.asMap(String.class, String.class);
        commentPayload = APIUtils.createCommentRequestBody(commentDetails);
        commentText = commentDetails.get("conContentText");
    }

    @When("the client sends a POST request to add an issue comment with {string} as issue id")
    public void theClientSendsAPOSTRequestToAddAnIssueCommentWithAsIssueId(String issueId) {
        response = request
                .pathParam("issueIdOrKey" , issueId)
                .contentType("application/json")
                .body(String.valueOf(commentPayload))
                .when()
                .post(addCommentEndpoint);
    }

    @When("the client sends a POST request to add an issue comment")
    public void theClientSendsAPOSTRequestToAddAnIssueComment() {
        String issueKey = APIUtils.getIssueKey();
        response = request
                .pathParam("issueIdOrKey" , issueKey)
                .contentType("application/json")
                .body(String.valueOf(commentPayload))
                .when()
                .post(addCommentEndpoint);

    }

    @And("the response should contain comment details")
    public void theResponseShouldContainCommentDetails() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response.jsonPath().getString("id")).isNotEmpty();
        softAssertions.assertThat(response.jsonPath().getString("body.content.content.text").contains(commentText)).isTrue();
        TestDataWriter.writeData2(response.getBody().asString() , "Comment.json");
        softAssertions.assertAll();

    }
}
