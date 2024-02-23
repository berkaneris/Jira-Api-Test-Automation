package com.inar.jira_api_test.stepdefinition;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inar.jira_api.pojo.request.editIssue.*;
import com.inar.jira_api.pojo.response.editIssueInfos.EditedIssueInfos;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import org.assertj.core.api.SoftAssertions;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

public class EditIssuePath extends BaseSteps {
    ObjectMapper objectMapper = new ObjectMapper();
    EditedIssueInfos editedList;
    HashMap<String, String> hashForDataTable;

    @When("I update the labels")
    public void ı_update_the_labels(DataTable dataTable) throws JsonProcessingException {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        hashForDataTable = new HashMap<>();
        for (int i = 0; i < dataTable.height(); i++) {
            hashForDataTable.put(dataTable.cell(i, 0), dataTable.cell(i, 1));
        }
        List<LabelsItem> labelsItemLabelsItem = new ArrayList<>();
        if (hashForDataTable.containsKey("add")) {
            List<String> add = List.of(hashForDataTable.get("add").split(";"));
            for (int i = 0; i < add.size(); i++) {
                LabelsItem labelsItems = new LabelsItem();
                labelsItems.setAdd(add.get(i));
                labelsItemLabelsItem.add(labelsItems);
            }
        }

        if (hashForDataTable.containsKey("remove")) {
            List<String> remove = List.of(hashForDataTable.get("remove").split(";"));
            for (int i = 0; i < remove.size(); i++) {
                LabelsItem labelsItems = new LabelsItem();
                labelsItems.setRemove(remove.get(i));
                // Ardından labelsItemLabelsItem listesine eklemelisiniz
                labelsItemLabelsItem.add(labelsItems);
            }
        }
        List<SummaryItem> summaryItemList = new ArrayList<>();
        if (hashForDataTable.containsKey("summary")) {
            SummaryItem summaryItem = new SummaryItem();
            summaryItem.setSet(hashForDataTable.get("summary"));
            summaryItemList.add(summaryItem);
        }

        Update update = new Update(labelsItemLabelsItem, summaryItemList);
        EditIssue editIssue = new EditIssue(update);

        String jsonFormatEditIssue = objectMapper.writeValueAsString(editIssue);
        response = request
                .body(jsonFormatEditIssue)
                .pathParam("issueIdOrKey", CreateIssueSteps.idOfCreatedIssue)
                .accept("application/json")
                .contentType("application/json")
                .put("/rest/api/3/issue/{issueIdOrKey}");


    }


    @Then("Edited issue paramaters should match with former issue")
    public void editedIssueParamatersSholudMatchWithFormerIssue(DataTable dataTable) {
        for (int i = 0; i < dataTable.height(); i++) {
            hashForDataTable.put(dataTable.cell(i, 0), dataTable.cell(i, 1));
        }
        then(response.jsonPath().getList("values")).isNotEmpty();
       then(response.jsonPath().getString("values.id")).isEqualTo((CreateIssueSteps.keyOfIssue));

        EditedIssueInfos editedIssueInfosList = response.as(new TypeRef<>() {
        });

        System.out.println(editedIssueInfosList.getValues().get(0).getItems().get(0).getFromString().trim());
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(editedIssueInfosList.getValues().get(0).getAuthor().getEmailAddress()).isEqualTo(CommonSteps.usernameForEditTest);
        softAssertions.assertThat(editedIssueInfosList.getValues().get(0).getItems().get(0).getFromString().trim()).isEqualTo(hashForDataTable.get("Issue labels before edit").replace(";", " ").trim());
        softAssertions.assertThat(editedIssueInfosList.getValues().get(0).getItems().get(0).getToString().trim()).isEqualTo(hashForDataTable.get("Issue labels for edit").replace(";", " ").trim());
        softAssertions.assertThat(editedIssueInfosList.getValues().get(0).getItems().get(1).getFromString()).isEqualTo(hashForDataTable.get("Issue summary before edit"));
        softAssertions.assertThat(editedIssueInfosList.getValues().get(0).getItems().get(1).getToString()).isEqualTo(hashForDataTable.get("Issue summary after edit"));
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedCurrentDate = currentDate.format(dateFormatter);
        softAssertions.assertThat(editedIssueInfosList.getValues().get(0).getCreated()).contains(formattedCurrentDate);
        softAssertions.assertAll();

    }

    @When("Get the current issue")
    public void getTheCurrentIssue() {
        response = request.header("Accept", "application/json")
                .pathParam("issueIdOrKey", CreateIssueSteps.idOfCreatedIssue)
                .get("/rest/api/3/issue/{issueIdOrKey}/changelog");
        editedList = response.as(new TypeRef<>() {
        });
        then(response.jsonPath().getList("values")).isEmpty();
    }

    @When("Get the edited issue")
    public void getTheEditedIssue() {
        response = request.header("Accept", "application/json")
                .pathParam("issueIdOrKey", CreateIssueSteps.idOfCreatedIssue)
                .get("/rest/api/3/issue/{issueIdOrKey}/changelog");
    }
}
