package org.inar.jira.board.stepdefinition.issue;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.inar.jira.board.pojo.response.create_issue_response.CreateIssueRes;
import org.inar.jira.board.stepdefinition.hook.BaseSteps;
import org.inar.jira.board.utils.ConfigManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import java.util.Map;

public class CreateIssueSteps extends BaseSteps {

    private static final Logger logger = LogManager.getLogger(CreateIssueSteps.class);

    String createIssueEndpoint = ConfigManager.getProperty("create_issue_url");

    CreateIssueRes createIssueRes;

    String issueIdOrKey;

    JsonObject issuePayload = new JsonObject();
    protected static int idOfCreatedIssue;
    protected static String keyOfIssue;

    @When("the client sets the request body to create a new issue")
    public void theClientSetsTheRequestBodyToCreateANewIssue(DataTable dataTable) {

        logger.info("Setting request body to create a new issue...");

        Map<String, String> issueDetails = dataTable.asMap(String.class, String.class);

        JsonObject fields = new JsonObject();
        JsonObject description = new JsonObject();
        JsonObject innerContent = new JsonObject();
        JsonObject content = new JsonObject();
        JsonObject issueType = new JsonObject();
        JsonObject project = new JsonObject();

        innerContent.addProperty("text", issueDetails.get("contContentText"));
        innerContent.addProperty("type", issueDetails.get("contContentType"));

        JsonArray innerContentArray = new JsonArray();
        innerContentArray.add(innerContent);
        content.addProperty("type", issueDetails.get("contentType"));
        content.add("content", innerContentArray);

        JsonArray contentArray = new JsonArray();
        contentArray.add(content);
        description.addProperty("type", issueDetails.get("descriptionType"));
        description.addProperty("version", Integer.parseInt(issueDetails.get("descriptionVersion")));
        description.add("content", contentArray);

        project.addProperty("key", issueDetails.get("projectKey"));
        issueType.addProperty("name", issueDetails.get("issueTypeName"));

        String[] labels = issueDetails.get("labels").split(";");
        JsonArray labelArray = new JsonArray();
        for (String label : labels) {
            labelArray.add(label);
        }

        fields.add("project", project);
        fields.addProperty("summary", issueDetails.get("summary"));
        fields.add("labels", labelArray);
        fields.add("description", description);
        fields.add("issuetype", issueType);

        issuePayload.add("fields", fields);
    }

    @When("the client sends a POST request to create issue endpoint")
    public void theClientSendsAPOSTRequestToCreateIssueEndpoint() {
        logger.info("Sending POST request to create issue endpoint...");

        response = request.contentType("application/json").body(String.valueOf(issuePayload)).when().post(createIssueEndpoint);
        if (response.statusCode() == 201) {
            idOfCreatedIssue = Integer.parseInt(response.jsonPath().getString("id"));
            keyOfIssue = response.jsonPath().getString("key");
        }
    }

    @And("the response should contain created issue data")
    public void theResponseShouldContainCreateIssueData() {
        logger.info("Verifying response contains created issue data...");

        //Rest assured deserialization
        createIssueRes = response.as(CreateIssueRes.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(createIssueRes.getId()).isNotEmpty();
        softAssertions.assertThat(createIssueRes.getKey()).isNotEmpty();
        softAssertions.assertThat(createIssueRes.getSelf()).isNotEmpty();
        softAssertions.assertAll();
    }
}
