package org.inar.jira.board.stepdefinition.projects;

import org.inar.jira.board.stepdefinition.hook.BaseSteps;
import org.inar.jira.board.utils.ConfigManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class GetRecentProjectsSteps extends BaseSteps {
    private static final Logger logger = LogManager.getLogger(GetRecentProjectsSteps.class);

    String getRecentProjectsEndpoint = ConfigManager.getProperty("get_recent_projects_url");

    String expandInformation;

    @When("user send a GET request the recent projects")
    public void userSendAGETRequestTheRecentProjects() {
        logger.info("Sending a GET request to fetch recent projects");
        response = request
                .when()
                .get(getRecentProjectsEndpoint);
        logger.info("Received response: {}", response.asString());
    }

    @And("the response should include the following project names")
    public void theResponseShouldIncludeTheFollowingProjectNames(DataTable dataTable) {
        logger.info("Verifying response includes the following project names");
        List<Map<String, String>> projectsData = dataTable.asMaps(String.class, String.class);
        JSONArray projectsArray = new JSONArray(response.getBody().asString());
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < projectsArray.length(); i++) {
            softAssertions.assertThat(projectsArray.getJSONObject(i).getString("name")).isEqualTo(projectsData.get(i).get("Project Name"));
            softAssertions.assertThat(projectsArray.getJSONObject(i).getString("id")).isEqualTo(projectsData.get(i).get("Project id"));
            softAssertions.assertThat(projectsArray.getJSONObject(i).getString("key")).isEqualTo(projectsData.get(i).get("Project Key"));
        }
        softAssertions.assertAll();
    }

    @When("user send a GET request the recent projects with expanded information {string}")
    public void userSendAGETRequestTheRecentProjectsWithExpandedInformation(String expandList) {
        logger.info("Sending a GET request to fetch recent projects with expanded information: {}", expandList);
        response = request
                .queryParam("expand", expandList)
                .when()
                .get(getRecentProjectsEndpoint);
        logger.info("Received response: {}", response.asString());

        expandInformation = expandList;
    }

    @Then("the response should include project details with expanded information")
    public void theResponseShouldIncludeProjectDetailsWithExpandedInformation() {
        logger.info("Verifying response includes project details with expanded information");
        JSONArray projectsArray = new JSONArray(response.getBody().asString());
        SoftAssertions softAssertions = new SoftAssertions();
        String[] expandElements = expandInformation.split(",");
        for (int i = 0; i < projectsArray.length(); i++) {
            for (int j = 0; j < expandElements.length; j++) {
                softAssertions.assertThat(projectsArray.getJSONObject(i).getString("expand").contains(expandElements[j])).isTrue();
                softAssertions.assertThat(projectsArray.getJSONObject(i).has(expandElements[j])).isTrue();
            }
        }
        softAssertions.assertAll();
    }
}
