package org.inar.jira.board.stepdefinition.issueattachments;

import org.inar.jira.board.stepdefinition.hook.BaseSteps;
import org.inar.jira.board.utils.APIUtils;
import org.inar.jira.board.utils.ConfigManager;
import org.inar.jira.board.stepdefinition.CommonSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class DeleteAttachmentSteps extends BaseSteps {
    private static final Logger logger = LogManager.getLogger(DeleteAttachmentSteps.class);

    String deleteAttachmentEndpoint = ConfigManager.getProperty("delete_attachment_url");

    String attachmentId;
    String issueKey;

    @Given("user has an attachment to delete")
    public void userHasAnAttachmentToDelete() {
        logger.info("Adding an attachment to delete");
        issueKey = APIUtils.getIssueKey();
        response = request
                .pathParam("issueIdOrKey" , issueKey)
                .header("X-Atlassian-Token" , "no-check")
                .multiPart("file" , new File("src/test/resources/testdata/AttachmentData.jpg"))
                .when()
                .post(ConfigManager.getProperty("add_attachment_url"));
        JSONArray attachment = new JSONArray(response.getBody().asString());
        attachmentId = attachment.getJSONObject(0).getString("id");
    }

    @When("user send a DELETE request to endpoint to delete attachment")
    public void userSendADELETERequestToEndpoint() {
        logger.info("Sending a DELETE request to delete attachment");
        new CommonSteps().theAPIRequestsAreAuthenticatedWithSystemPropertiesForUsernameAndToken();
        response = request
                .pathParam("id" , attachmentId)
                .when()
                .delete(deleteAttachmentEndpoint);
    }

    @When("user send a DELETE request to endpoint with {string} as attachment id")
    public void userSendADELETERequestToEndpointWithAsAttachmentId(String attachmentId) {
        logger.info("Sending a DELETE request to delete attachment with attachment id '{}'", attachmentId);
        response = request
                .pathParam("id" , attachmentId)
                .when().delete(deleteAttachmentEndpoint);
    }

    @And("check the attachment is deleted")
    public void checkTheAttachmentIsDeleted() {
        logger.info("Checking if the attachment is deleted");
        new CommonSteps().theAPIRequestsAreAuthenticatedWithSystemPropertiesForUsernameAndToken();
        response = request
                .pathParam("id" , attachmentId)
                .when()
                .get("/rest/api/3/attachment/content/{id}");
        Assertions.assertThat(response.statusCode()).isEqualTo(404);
    }
}
