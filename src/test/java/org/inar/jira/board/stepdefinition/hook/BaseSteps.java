package org.inar.jira.board.stepdefinition.hook;


import org.inar.jira.board.utils.ConfigManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



import static io.restassured.RestAssured.baseURI;

public abstract class BaseSteps {

    protected static Response response;

    protected static RequestSpecification request;



    public BaseSteps() {
        baseURI = ConfigManager.getProperty("base.uri");

    }
}
