package com.inar.jira_api_test.stepdefinition.hook;


import com.inar.jira_api.utils.ConfigManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public abstract class BaseSteps {

    protected static Response response;

    protected static RequestSpecification request;

    protected static Map<String , List<String>> issueKeyAndCommentIdList = new HashMap<>();


    public BaseSteps() {
        baseURI = ConfigManager.getProperty("base.uri");

    }
}
