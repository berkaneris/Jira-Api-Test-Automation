package com.inar.jira_api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class TestDataWriter {

    public static void writeData(Object object, String path) throws FileNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        FileOutputStream fileOutputStream = new FileOutputStream("src\\test\\resources\\features\\testdata" + path);

        try {
            objectMapper.writeValue(fileOutputStream, object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeData2(String jsonData, String fileName) {
        try (FileWriter fileWriter = new FileWriter("src\\test\\resources\\features\\testdata\\" + fileName)) {
            fileWriter.write(jsonData);
            System.out.println("Test data written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing test data to file: " + e.getMessage());
        }
    }
}

