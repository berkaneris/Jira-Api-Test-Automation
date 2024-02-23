package com.inar.jira_api_test.stepdefinition;

import com.inar.jira_api.utils.ConfigManager;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONObject;

public class FindUserSteps extends BaseSteps {

    String findUserEndpoint = ConfigManager.getProperty("find_user_url");
    String parameter;
    @When("the client sets the request body to find user with {string}")
    public void theClientSetsTheRequestBodyToFindUserWith(String parameter) {
        response = request
                .queryParam("query" , parameter)
                .when()
                .get(findUserEndpoint);

        this.parameter = parameter;
    }

    @And("the response should contain users data")
    public void theResponseShouldContainUsersData() {
        JSONArray userRes = new JSONArray(response.getBody().asString());

        for (int i = 0; i < userRes.length(); i++) {
            JSONObject user = userRes.getJSONObject(i);
            Assertions.assertThat(((String)user.get("displayName")).contains(parameter)).isTrue();
            Assertions.assertThat(((String)user.get("accountId"))).isNotEmpty();

        }
    }
}
