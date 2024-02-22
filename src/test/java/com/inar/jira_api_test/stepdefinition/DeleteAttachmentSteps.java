package com.inar.jira_api_test.stepdefinition;

import com.inar.jira_api.utils.ConfigManager;
import com.inar.jira_api.utils.TestDataReader;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;

public class DeleteAttachmentSteps extends BaseSteps {

    String deleteAttachmentEndpoint = ConfigManager.getProperty("delete_attachment_url");

    String attachmentId;

    @Given("user has an attachment to delete")
    public void userHasAnAttachmentToDelete() {
        String attachment = TestDataReader.readData2("attachment.json");
        if (attachment == null || attachment.isEmpty()) {
            throw new RuntimeException("There is no content");
        }
        JSONArray attachmentObject = new JSONArray(attachment);
        attachmentId = attachmentObject.optJSONObject(0).getString("id");
    }


    @When("user send a DELETE request to endpoint")
    public void userSendADELETERequestToEndpoint() {
        response = request
                .pathParam("id" , attachmentId)
                .when().delete(deleteAttachmentEndpoint);
    }
}
