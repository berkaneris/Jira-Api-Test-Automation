package org.inar.jira.board.stepdefinition.issueattachments;

import org.inar.jira.board.stepdefinition.hook.BaseSteps;
import org.inar.jira.board.utils.APIUtils;
import org.inar.jira.board.utils.ConfigManager;
import org.inar.jira.board.utils.TestDataWriter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class AddAttachmentSteps extends BaseSteps {
    private static final Logger logger = LogManager.getLogger(AddAttachmentSteps.class);

    String addAttachmentEndpoint = ConfigManager.getProperty("add_attachment_url");

    String fileName;

    @When("the client sends a POST request with {string} as issue key and {string}")
    public void theClientSendsAPOSTRequestWithAsIssueKeyAnd(String issueKey, String fileName) {
        logger.info("Sending a POST request with issue key '{}' and file '{}'", issueKey, fileName);
        response = request
                .pathParam("issueIdOrKey" , issueKey)
                .header("X-Atlassian-Token" , "no-check")
                .multiPart("file" , new File("src/test/resources/testdata/" + fileName))
                .when()
                .post(addAttachmentEndpoint);

        this.fileName = fileName;
    }

    @And("the response should contain attachment details")
    public void theResponseShouldContainAttachmentDetails() {
        logger.info("Verifying attachment details in the response");
        JSONArray object = new JSONArray(response.getBody().asString());
        Assertions.assertThat((String) object.getJSONObject(0).get("id")).isNotEmpty();
        Assertions.assertThat((String) object.getJSONObject(0).get("filename")).isEqualTo(fileName);

        TestDataWriter.writeData2(response.getBody().asString(), "Attachment.json");

    }

    @When("the client sends a POST request to upload file {string}")
    public void theClientSendsAPOSTRequestToUploadFile(String fileName) {
        logger.info("Sending a POST request to upload file '{}'", fileName);
        String issueKey = APIUtils.getIssueKey();
        response = request
                .pathParam("issueIdOrKey" , issueKey)
                .header("X-Atlassian-Token" , "no-check")
                .multiPart("file" , new File("src/test/resources/testdata/" + fileName))
                .when()
                .post(addAttachmentEndpoint);

        this.fileName = fileName;
    }
}
