package com.inar.jira_api_test.stepdefinition;

import com.google.gson.Gson;
import com.inar.jira_api.pojo.request.create_issue_request.*;
import com.inar.jira_api.pojo.response.create_issue_response.CreateIssueRes;
import com.inar.jira_api.utils.ConfigManager;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CreateIssueSteps extends BaseSteps {

    String createIssueEndpoint = ConfigManager.getProperty("create_issue_url");

    Issue issue = new Issue();
    Content content = new Content();
    Text text = new Text();
    Description description = new Description();
    Fields fields = new Fields();
    IssueType issuetype = new IssueType();

    Project project = new Project();
    CreateIssueRes createIssueRes;



    @When("the client sets the request body to create a new issue")
    public void theClientSetsTheRequestBodyToCreateANewIssue(DataTable dataTable) {
        Map<String,String> issueDetails = dataTable.asMap(String.class, String.class);
        text.setText(issueDetails.get("contContentText"));
        text.setType(issueDetails.get("contContentType"));

        content.setType(issueDetails.get("contentType"));
        content.setContent(List.of(text));

        description.setVersion(Integer.parseInt(issueDetails.get("descriptionVersion")));
        description.setType(issueDetails.get("descriptionType"));
        description.setContent(List.of(content));

        fields.setProject(project);
        fields.setSummary(issueDetails.get("summary"));
        fields.setDescription(description);
        fields.setIssuetype(issuetype);
        fields.setLabels(Arrays.asList(issueDetails.get("labels").split(";")));

        project.setKey(issueDetails.get("projectKey"));
        issuetype.setName(issueDetails.get("issueTypeName"));

        issue.setFields(fields);
    }

    @When("the client sends a POST request to create issue endpoint")
    public void theClientSendsAPOSTRequestToCreateIssueEndpoint() {
        String requestBody = new Gson().toJson(issue);
        response = request.contentType("application/json").body(requestBody).when().post(createIssueEndpoint);

    }

    @And("the response should contain create issue data")
    public void theResponseShouldContainCreateIssueData() {
        createIssueRes = response.as(CreateIssueRes.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(createIssueRes.getId()).isNotEmpty();
        softAssertions.assertThat(createIssueRes.getKey()).isNotEmpty();
        softAssertions.assertThat(createIssueRes.getSelf()).isNotEmpty();
        softAssertions.assertAll();


    }


}
