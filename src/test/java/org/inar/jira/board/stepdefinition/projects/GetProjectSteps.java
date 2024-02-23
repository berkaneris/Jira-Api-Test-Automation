package org.inar.jira.board.stepdefinition.projects;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.inar.jira.board.stepdefinition.hook.BaseSteps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;

public class GetProjectSteps extends BaseSteps {
    private static final Logger logger = LogManager.getLogger(GetProjectSteps.class);

    @When("I get details of project whose ID is {string}")
    public void ıGetDetailsOfProjectWhoseIDIsIDOfProject(String projectID) {
        logger.info("Getting details of project with ID: {}", projectID);
        response = request.pathParam("projectIdOrKey", projectID).get("/rest/api/3/project/{projectIdOrKey}");
        logger.info("Received response: {}", response.asString());
    }

    @And("Given values should match with response")
    public void valuesForUserCreatedProjectAndSituationActivenessAndProjectNameAnd(DataTable dataTable) {
        Map<String,String> parameters = dataTable.asMap(String.class, String.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response.jsonPath().getString("lead.displayName")).isEqualTo(parameters.get("Display Name"));
        softAssertions.assertThat(response.jsonPath().getString("lead.active")).isEqualTo(parameters.get("Activeness"));
        softAssertions.assertThat(response.jsonPath().getString("name")).isEqualTo(parameters.get("ProjectName"));
        softAssertions.assertThat(response.jsonPath().getString("projectTypeKey")).isEqualTo(parameters.get("Project type Key"));
        softAssertions.assertAll();
    }

    @And("Project ID should matches with {string}")
    public void projectIDShouldMatchesWithIDOfProject(String projectID) {
        logger.info("Verifying project ID matches with expected value: {}", projectID);
        then(projectID).isEqualTo(response.jsonPath().getString("key"));
    }
}
