package org.inar.jira.board.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.Map;

public class APIUtils {

    public static JsonObject createCommentRequestBody(Map<String ,String> commentDetails){
        JsonObject commentPayload = new JsonObject();
        JsonObject body = new JsonObject();
        JsonObject content = new JsonObject();
        JsonObject innerContent = new JsonObject();

        innerContent.addProperty("text" , commentDetails.get("conContentText"));
        innerContent.addProperty("type" , commentDetails.get("conContentType"));

        JsonArray array = new JsonArray();
        array.add(innerContent);
        content.add("content" , array);
        content.addProperty("type" , commentDetails.get("contentType"));

        array = new JsonArray();
        array.add(content);
        body.add("content" , array);
        body.addProperty("type" , commentDetails.get("type"));
        body.addProperty("version" , Integer.parseInt(commentDetails.get("version")));

        commentPayload.add("body" ,body);
        return commentPayload;
    }

    public static String getIssueKey(){
        String issueRes = TestDataReader.readData2("issue.json");
        JSONObject issue = new JSONObject(issueRes);
        return issue.getString("key");
    }
    public static String getCommentId(){
        String commentRes = TestDataReader.readData2("comment.json");
        JSONObject commentObject = new JSONObject(commentRes);
        return commentObject.getString("id");
    }


    public static RequestSpecification makeAuthentication(RequestSpecification request){
        String username = System.getenv("jirausername");
        String token = System.getenv("accesstoken");
        if (username == null || username.isEmpty() || token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Username or token system properties are not set.");
        }
        return RestAssured.given().auth().preemptive().basic(username, token);
    }
    public static Response createIssueRequest(RequestSpecification request){
        String requestBody = TestDataReader.readData2("CreatIssueRequestBody.json");
        return request.contentType("application/json").body(requestBody).when().post(ConfigManager.getProperty("create_issue_url"));
    }
}
