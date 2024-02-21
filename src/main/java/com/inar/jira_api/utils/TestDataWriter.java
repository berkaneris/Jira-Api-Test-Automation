package com.inar.jira_api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestDataWriter {

    public static void writeData(Object object, String path) throws FileNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        FileOutputStream fileOutputStream = new FileOutputStream("src\\test\\resources\\test_data\\" + path);

        try {
            objectMapper.writeValue(fileOutputStream, object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

