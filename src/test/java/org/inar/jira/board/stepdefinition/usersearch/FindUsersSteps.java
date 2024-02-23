package org.inar.jira.board.stepdefinition.usersearch;

import org.inar.jira.board.stepdefinition.hook.BaseSteps;
import org.inar.jira.board.utils.ConfigManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FindUsersSteps extends BaseSteps {
    String findUserEndpoint = ConfigManager.getProperty("find_user_url");
    String parameter;
    @When("the client sets the request body to find user with query {string}")
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

    @And("the response should contain {string} users")
    public void theResponseShouldContainUsers(String maxResult) {
        JSONArray userRes = new JSONArray(response.getBody().asString());
        Assertions.assertThat(userRes.length()).isEqualTo(Integer.parseInt(maxResult));
    }

    @When("the client sets the request body to find user with maxResult {string}")
    public void theClientSetsTheRequestBodyToFindUserWithMaxResult(String maxResult) {
        int max= Integer.parseInt(maxResult);
        System.out.println(max+"        **********************");

        Map<String, Object> queryParams=new HashMap<>();
        queryParams.put("query","");
        queryParams.put("maxResults",max);

        response = request
                .queryParams(queryParams)
                .when()
                .get(findUserEndpoint);
    }
}
