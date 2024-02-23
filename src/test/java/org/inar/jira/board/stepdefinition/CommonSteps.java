package org.inar.jira.board.stepdefinition;


import org.inar.jira.board.stepdefinition.hook.BaseSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonSteps extends BaseSteps {
    private static final Logger logger = LogManager.getLogger(CommonSteps.class);
    public static String usernameForEditTest;

    @Given("the API requests are authenticated with system properties for username and token")
    public void theAPIRequestsAreAuthenticatedWithSystemPropertiesForUsernameAndToken() {
        String username = System.getenv("jirausername");
        String token = System.getenv("accesstoken");
        usernameForEditTest = System.getenv("jirausername");
        if (username == null || username.isEmpty() || token == null || token.isEmpty()) {
            logger.error("Username or token system properties are not set.");
            throw new IllegalArgumentException("Username or token system properties are not set.");
        }
        request = RestAssured.given().auth().preemptive().basic(username, token);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        logger.info("Response status code: {}", response.statusCode());
        Assertions.assertThat(response.statusCode()).isEqualTo(expectedStatusCode);
    }

    @Then("the response status code should be {string}")
    public void theResponseStatusCodeShouldBeStatusCode(String expectedStatusCode) {
        logger.info("Response status code: {}", response.statusCode());
        Assertions.assertThat(response.statusCode()).isEqualTo(Integer.parseInt(expectedStatusCode));
        logger.info(response.prettyPrint());
        System.out.println(response.prettyPrint());
    }
}


