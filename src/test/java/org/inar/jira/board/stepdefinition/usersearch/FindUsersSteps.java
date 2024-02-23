package org.inar.jira.board.stepdefinition.usersearch;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.inar.jira.board.stepdefinition.hook.BaseSteps;
import org.inar.jira.board.utils.ConfigManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class FindUsersSteps extends BaseSteps {
    private static final Logger logger = LogManager.getLogger(FindUsersSteps.class);

    String findUserEndpoint = ConfigManager.getProperty("find_user_url");
    String parameter;

    // Method to set the request body to find user with a query
    @When("the client sets the request body to find user with query {string}")
    public void theClientSetsTheRequestBodyToFindUserWith(String parameter) {
        logger.info("Setting request body to find user with query: {}", parameter);
        response = request
                .queryParam("query", parameter)
                .when()
                .get(findUserEndpoint);

        this.parameter = parameter;
    }

    // Method to verify that the response contains user data
    @And("the response should contain users data")
    public void theResponseShouldContainUsersData() {
        JSONArray userRes = new JSONArray(response.getBody().asString());

        for (int i = 0; i < userRes.length(); i++) {
            JSONObject user = userRes.getJSONObject(i);
            logger.info("Verifying user data: {}", user.toString());
            Assertions.assertThat(((String) user.get("displayName")).contains(parameter)).isTrue();
            Assertions.assertThat(((String) user.get("accountId"))).isNotEmpty();
        }
    }

    // Method to verify that the response contains a specific number of users
    @And("the response should contain {string} users")
    public void theResponseShouldContainUsers(String maxResult) {
        JSONArray userRes = new JSONArray(response.getBody().asString());
        logger.info("Verifying response contains {} users", maxResult);
        Assertions.assertThat(userRes.length()).isEqualTo(Integer.parseInt(maxResult));
    }

    // Method to set the request body to find user with maxResult
    @When("the client sets the request body to find user with maxResult {string}")
    public void theClientSetsTheRequestBodyToFindUserWithMaxResult(String maxResult) {
        int max = Integer.parseInt(maxResult);
        logger.info("Setting request body to find user with maxResult: {}", max);

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("query", "");
        queryParams.put("maxResults", max);

        response = request
                .queryParams(queryParams)
                .when()
                .get(findUserEndpoint);
    }
}
