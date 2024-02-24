package org.inar.jira.board.stepdefinition.hook;

import io.cucumber.java.*;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.inar.jira.board.utils.APIUtils;
import org.inar.jira.board.utils.ConfigManager;
import org.inar.jira.board.utils.TestDataReader;
import org.inar.jira.board.utils.TestDataWriter;
import org.json.JSONObject;

import java.util.Locale;

public class Hooks extends BaseSteps {
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @BeforeAll
    public static void globalSetup() {
        request = APIUtils.makeAuthentication(request);
        request.baseUri(ConfigManager.getProperty("base.uri"));
        response = APIUtils.createIssueRequest(request);
        TestDataWriter.writeData2(response.getBody().asString() , "Issue.json");
        // This method will be executed once before all scenarios
        logger.info("Global setup - This will run once before all scenarios");
        // You can put any setup logic you want to execute once here
    }

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

    @AfterAll
    public static void globalTearDown() {
        request = APIUtils.makeAuthentication(request);
        response = APIUtils.createIssueRequest(request);
        JSONObject object = new JSONObject(TestDataReader.readData2("issue.json"));
         request.pathParam("issueIdOrKey", object.getString("key")).when().delete(ConfigManager.getProperty("delete_issue_url"));
        // This method will be executed once before all scenarios
        logger.info("Global tear down - This will run once after all scenarios");
        // You can put any setup logic you want to execute once here
    }

}
