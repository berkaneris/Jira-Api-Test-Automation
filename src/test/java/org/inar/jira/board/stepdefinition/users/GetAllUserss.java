package org.inar.jira.board.stepdefinition.users;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.inar.jira.board.pojo.response.getAllUsers.GetAllUsers;
import org.inar.jira.board.stepdefinition.hook.BaseSteps;
import org.inar.jira.board.utils.ConfigManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;


import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.BDDAssertions.then;

public class GetAllUserss extends BaseSteps {
    ObjectMapper objectMapper = new ObjectMapper();
    String getAllUsersEndpoint = ConfigManager.getProperty("get_all_users_url");
    private static final Logger logger = LogManager.getLogger(GetAllUserss.class);
    HashMap<String, GetAllUsers> hashMap = new HashMap<>();
    List<GetAllUsers> getAllUsersList = new ArrayList<>();

    @When("I set the start point to {string} and maximum account number to {string}")
    public void ıSetTheStartPointToStartPointAndMaximumAccountNumberToMaxAccountNumber(String startAt, String maxValue) throws IOException, InterruptedException {
        if (maxValue.isBlank() && startAt.isBlank()) {
            startAt = "0";
            maxValue = "60";
        } else if (startAt.isBlank()) {
            startAt = "0";
        } else if (maxValue.isBlank()) {
            maxValue = "60";
        }
        response = request.queryParam("startAt", Integer.parseInt(startAt))
                .queryParam("maxResults", maxValue)
                .get(getAllUsersEndpoint);


        logger.debug("Result should show less than " + maxValue + " account");
        logger.debug("Accounts of showed should be " + startAt + "th of all users");
        getAllUsersList = response.as(new TypeRef<>() {
        });
        logger.info("According to given query params getAllUsersList setted." + "(size :" + getAllUsersList.size() + ")");
    }


    @And("Number of account should be {string}")
    public void numberOfAccountShouldBeMaxAccountNumber(String numberOfUser) {
        then(getAllUsersList.size()).isLessThanOrEqualTo(Integer.parseInt(numberOfUser));
    }

    @And("Content of accounts should match with given data")
    public void contentOfAccountsShouldMatchWithGivenData() throws IOException {
        try {
            List<GetAllUsers> bilal = objectMapper.readValue(new File("src\\test\\resources\\testdata\\GetAllUsers.json"), new TypeReference<>() {
            });
            for (GetAllUsers user : bilal) {
                if (user != null && user.getAccountId() != null) {
                    hashMap.put(user.getAccountId(), user);
                } else {
                    logger.warn("Skipping entry due to null value for user or accountId.");
                }
            }
            SoftAssertions softAssertions = new SoftAssertions();
            for (GetAllUsers apiUser : getAllUsersList) {
                String accountId = apiUser.getAccountId();
                GetAllUsers fileUser = hashMap.get(accountId);

                if (fileUser == null) {
                    logger.error("No matching user in the JSON file for accountId: {}", accountId);
                    continue;
                }
                softAssertions.assertThat(apiUser.getAccountId()).isEqualTo(fileUser.getAccountId());
                softAssertions.assertThat(apiUser.getDisplayName()).isEqualTo(fileUser.getDisplayName());
                softAssertions.assertThat(apiUser.getAvatarUrls().getJsonMember16x16()).isEqualTo(fileUser.getAvatarUrls().getJsonMember16x16());
                softAssertions.assertThat(apiUser.getAvatarUrls().getJsonMember24x24()).isEqualTo(fileUser.getAvatarUrls().getJsonMember24x24());
                softAssertions.assertThat(apiUser.getAvatarUrls().getJsonMember32x32()).isEqualTo(fileUser.getAvatarUrls().getJsonMember32x32());
                softAssertions.assertThat(apiUser.getAvatarUrls().getJsonMember48x48()).isEqualTo(fileUser.getAvatarUrls().getJsonMember48x48());
                softAssertions.assertThat(apiUser.getAccountType()).isEqualTo(fileUser.getAccountType());
                softAssertions.assertThat(apiUser.isActive()).isEqualTo(fileUser.isActive());
            }
            softAssertions.assertAll();
        } catch (IOException e) {
            logger.error("Error reading JSON file: {}", e.getMessage());
            throw e; // Rethrow the exception after logging
        }
    }


}
