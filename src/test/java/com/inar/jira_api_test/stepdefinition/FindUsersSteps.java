package com.inar.jira_api_test.stepdefinition;

import com.inar.jira_api.utils.ConfigManager;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;

import java.util.HashMap;
import java.util.Map;

public class FindUsersSteps extends BaseSteps {

    String findUsersEndpoint = ConfigManager.getProperty("find_user_url");

    @When("the client sets the request body to find user with {string}")
    public void theClientSetsTheRequestBodyToFindUserWith(String query) {
        Map<String,String> queryParam=new HashMap<>();
        queryParam.put("query",query);
        request = request.queryParams(queryParam);
    }

    @When("the client sends a POST request to find user")
    public void theClientSendsAPOSTRequestToFindUser() {
response=request.contentType("application/json").when().get(findUsersEndpoint);
    }

    @And("the response should contain users data")
    public void theResponseShouldContainUsersData() {

    }


}
