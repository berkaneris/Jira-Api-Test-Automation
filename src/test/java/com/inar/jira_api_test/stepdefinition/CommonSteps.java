package com.inar.jira_api_test.stepdefinition;

import com.inar.jira_api.utils.ConfigManager;
import com.inar.jira_api_test.stepdefinition.hook.BaseSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.sl.In;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;

public class CommonSteps extends BaseSteps {
    @Given("the API requests are authenticated with system properties for username and token")
    public static void theAPIRequestsAreAuthenticatedWithSystemPropertiesForUsernameAndToken() {
        String username = System.getenv("jirausername");
        String token = System.getenv("accesstoken");

        if(username == null || username.isEmpty() || token == null || token.isEmpty()){
            throw new IllegalArgumentException("Username  or token system properties are not set.");
        }
        request = RestAssured.given().auth().preemptive().basic(username,token);

//        request.baseUri(ConfigManager.getProperty("base.uri"));
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        Assertions.assertThat(response.statusCode()).isEqualTo(expectedStatusCode);
    }

    @Then("the response status code should be {string}")
    public void theResponseStatusCodeShouldBeStatusCode(String expectedStatusCode) {
        Assertions.assertThat(response.statusCode()).isEqualTo(Integer.parseInt(expectedStatusCode));

    }
}
