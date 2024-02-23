package org.inar.jira.board.stepdefinition.users;

import io.cucumber.java.en.When;
import org.inar.jira.board.stepdefinition.hook.BaseSteps;
import org.inar.jira.board.utils.ConfigManager;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetUserSteps extends BaseSteps {
    private static final Logger logger = LogManager.getLogger(GetUserSteps.class);

    String endpoint = ConfigManager.getProperty("get_user_url");

    @When("the client sends a Get request to get user information as accountId {string}")
    public void the_client_sends_a_Get_request_to_get_user_information(String str) {
        logger.info("Sending a Get request to get user information for accountId: {}", str);
        response = request.queryParam("accountId", str).when().get(endpoint);
        logger.info("Received response: {}", response.asString());
    }

    @Then("I should see the user account type as {string}")
    public void i_should_see_the_user_account_type_as(String expectedAccountType) {
        String actualAccountType = response.jsonPath().getString("accountType");
        logger.info("Verifying user account type. Expected: {}, Actual: {}", expectedAccountType, actualAccountType);
        Assertions.assertThat(actualAccountType).isEqualTo(expectedAccountType);
    }

    @Then("I should see the user email address as {string}")
    public void i_should_see_the_user_email_address_as(String expectedEmailAddress) {
        String actualEmailAddress = response.jsonPath().getString("emailAddress");
        logger.info("Verifying user email address. Expected: {}, Actual: {}", expectedEmailAddress, actualEmailAddress);
        Assertions.assertThat(actualEmailAddress).isEqualTo(expectedEmailAddress);
    }

    @Then("I should see the user is active")
    public void i_should_see_the_user_is_active() {
        boolean isActive = response.jsonPath().getBoolean("active");
        logger.info("Verifying user is active. IsActive: {}", isActive);
        Assertions.assertThat(isActive).isTrue();
    }

    @Then("I should see the user display name as {string}")
    public void i_should_see_the_user_display_name_as(String expectedDisplayName) {
        String actualDisplayName = response.jsonPath().getString("displayName");
        logger.info("Verifying user display name. Expected: {}, Actual: {}", expectedDisplayName, actualDisplayName);
        Assertions.assertThat(actualDisplayName).isEqualTo(expectedDisplayName);
    }
}
