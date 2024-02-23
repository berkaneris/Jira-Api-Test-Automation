package com.inar.jira_api_test.stepdefinition;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.hu.Ha;
import org.assertj.core.api.SoftAssertions;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;


public class GetProjectSteps extends BaseSteps {

    @When("I get details of project whose ID is {string}")
    public void ıGetDetailsOfProjectWhoseIDIsIDOfProject(String projectID) {
        response = request.pathParam("projectIdOrKey", projectID).get("/rest/api/3/project/{projectIdOrKey}");
    }
    @And("Given values should match with response")
    public void valuesForUserCreatedProjectAndSituationActivenessAndProjectNameAnd(DataTable dataTable) {
        Map<String,String> parameters =dataTable.asMap(String.class, String.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response.jsonPath().getString("lead.displayName")).isEqualTo(parameters.get("Display Name"));
        softAssertions.assertThat(response.jsonPath().getString("lead.active")).isEqualTo(parameters.get("Activeness"));
        softAssertions.assertThat(response.jsonPath().getString("name")).isEqualTo(parameters.get("ProjectName"));
        softAssertions.assertThat(response.jsonPath().getString("projectTypeKey")).isEqualTo(parameters.get("Project type Key"));
        softAssertions.assertAll();
   }

    @And("Project ID shouyld matches with {string}")
    public void projectIDShouyldMatchesWithIDOfProject(String projectID) {
        then(projectID).isEqualTo(response.jsonPath().getString("key"));
    }
}
