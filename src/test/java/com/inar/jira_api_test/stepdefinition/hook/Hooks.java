package com.inar.jira_api_test.stepdefinition.hook;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

public class Hooks extends BaseSteps {
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        logger.info("::::::::::::::: TEST INFORMARION :::::::::::::::");
        logger.info("Executing scenario: " + scenario.getName());
        Locale.setDefault(Locale.US);

    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            if (response != null) {
                logger.error("Scenario failed! Logging response body for failed scenario: " + scenario.getName());
                logger.error(response.getBody().prettyPrint());
            }
            else {
                logger.error("Scenario failed! But no response was set in the TestContext.");
            }
        }
        logger.info("Finished scenario: " + scenario.getName());
        logger.info("::::::::::::::::::::::::::::::::::::::::::::::::");
    }

}
