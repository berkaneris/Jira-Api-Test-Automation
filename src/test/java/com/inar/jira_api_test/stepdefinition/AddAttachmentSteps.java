package com.inar.jira_api_test.stepdefinition;

import com.inar.jira_api.utils.ConfigManager;
import com.inar.jira_api.utils.TestDataWriter;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class AddAttachmentSteps extends BaseSteps {

    String addAttachmentEndpoint = ConfigManager.getProperty("add_attachment_url");

    String fileName;
    @When("the client sends a POST request with {string} as issue key and {string}")
    public void theClientSendsAPOSTRequestWithAsIssueKeyAnd(String issueKey, String filePath) {
        response = request
                .pathParam("issueIdOrKey" , issueKey)
                .header("X-Atlassian-Token" , "no-check")
                .multiPart("file" , new File(filePath))
                .when()
                .post(addAttachmentEndpoint);

        String[] pathParts = filePath.split("/");
        fileName = pathParts[pathParts.length - 1];
    }

    @And("the response should contain attachment details")
    public void theResponseShouldContainAttachmentDetails() {
        JSONArray object = new JSONArray(response.getBody().asString());
        Assertions.assertThat((String) object.getJSONObject(0).get("id")).isNotEmpty();
        Assertions.assertThat((String) object.getJSONObject(0).get("filename")).isEqualTo(fileName);


        TestDataWriter.writeData2(response.getBody().asString(), "attachment.json");

    }
}
