package com.inar.jira_api_test.stepdefinition;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

//    Issue issue = new Issue();
//    Content content = new Content();
//    Text text = new Text();
//    Description description = new Description();
//    Fields fields = new Fields();
//    IssueType issuetype = new IssueType();
//
//    Project project = new Project();
    CreateIssueRes createIssueRes;

    JsonObject issuePayload = new JsonObject();



    @When("the client sets the request body to create a new issue")
    public void theClientSetsTheRequestBodyToCreateANewIssue(DataTable dataTable) {
        Map<String,String> issueDetails = dataTable.asMap(String.class, String.class);
//        text.setText(issueDetails.get("contContentText"));
//        text.setType(issueDetails.get("contContentType"));
//
//        content.setType(issueDetails.get("contentType"));
//        content.setContent(List.of(text));
//
//        description.setVersion(Integer.parseInt(issueDetails.get("descriptionVersion")));
//        description.setType(issueDetails.get("descriptionType"));
//        description.setContent(List.of(content));
//
//        fields.setProject(project);
//        fields.setSummary(issueDetails.get("summary"));
//        fields.setDescription(description);
//        fields.setIssuetype(issuetype);
//        fields.setLabels(Arrays.asList(issueDetails.get("labels").split(";")));
//
//        project.setKey(issueDetails.get("projectKey"));
//        issuetype.setName(issueDetails.get("issueTypeName"));
//
//        issue.setFields(fields);


        JsonObject fields = new JsonObject();
        JsonObject description = new JsonObject();
        JsonObject innerContent = new JsonObject();
        JsonObject content = new JsonObject();
        JsonObject issueType = new JsonObject();
        JsonObject project = new JsonObject();

        innerContent.addProperty("text" ,issueDetails.get("contContentText"));
        innerContent.addProperty("type" ,issueDetails.get("contContentType"));

        JsonArray innerContentArray = new JsonArray();
        innerContentArray.add(innerContent);
        content.addProperty("type" ,issueDetails.get("contentType"));
        content.add("content" , innerContentArray);

        JsonArray contentArray = new JsonArray();
        contentArray.add(content);
        description.addProperty("type" ,issueDetails.get("descriptionType"));
        description.addProperty("version" ,Integer.parseInt(issueDetails.get("descriptionVersion")));
        description.add("content" ,contentArray);

        project.addProperty("key" ,issueDetails.get("projectKey"));
        issueType.addProperty("name" , issueDetails.get("issueTypeName"));

        String[] labels = issueDetails.get("labels").split(";");
        JsonArray labelArray = new JsonArray();
        for(String label: labels) {
            labelArray.add(label);
        }
        fields.add("project" , project);
        fields.addProperty("summary" , issueDetails.get("summary"));
        fields.add("labels" , labelArray);
        fields.add("description" , description);
        fields.add("issuetype" , issueType);

        issuePayload.add("fields" , fields);

    }

    @When("the client sends a POST request to create issue endpoint")
    public void theClientSendsAPOSTRequestToCreateIssueEndpoint() {
//        String requestBody = new Gson().toJson(issue);

        response = request.contentType("application/json").body(String.valueOf(issuePayload)).when().post(createIssueEndpoint);

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
