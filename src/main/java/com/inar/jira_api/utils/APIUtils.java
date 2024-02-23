package com.inar.jira_api.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
}
