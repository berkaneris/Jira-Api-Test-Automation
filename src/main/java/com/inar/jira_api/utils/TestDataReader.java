package com.inar.jira_api.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestDataReader {


    public static <T> T readData(String path, Class<T> valueType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FileInputStream fileInputStream;

        try {
            fileInputStream = new FileInputStream("src\\test\\resources\\test_data\\" + path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        return mapper.readValue(fileInputStream, valueType);
    }

}
