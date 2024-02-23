package com.inar.jira_api_test.stepdefinition;

import com.inar.jira_api.utils.APIUtils;
import com.inar.jira_api.utils.ConfigManager;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;

import java.io.File;

public class DeleteAttachmentSteps extends BaseSteps {

    String deleteAttachmentEndpoint = ConfigManager.getProperty("delete_attachment_url");

    String attachmentId;
    String issueKey;

    @Given("user has an attachment to delete")
    public void userHasAnAttachmentToDelete() {
        //Add an attachment to delete
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
        new CommonSteps().theAPIRequestsAreAuthenticatedWithSystemPropertiesForUsernameAndToken();
        response = request
                .pathParam("id" , attachmentId)
                .when()
                .delete(deleteAttachmentEndpoint);
    }

    @When("user send a DELETE request to endpoint with {string} as attachment id")
    public void userSendADELETERequestToEndpointWithAsAttachmentId(String attachmentId) {
        response = request
                .pathParam("id" , attachmentId)
                .when().delete(deleteAttachmentEndpoint);
    }

    @And("check the attachment is deleted")
    public void checkTheAttachmentIsDeleted() {
        new CommonSteps().theAPIRequestsAreAuthenticatedWithSystemPropertiesForUsernameAndToken();
        response = request
                .pathParam("id" , attachmentId)
                .when()
                .get("/rest/api/3/attachment/content/{id}");
        Assertions.assertThat(response.statusCode()).isEqualTo(404);

    }
}
