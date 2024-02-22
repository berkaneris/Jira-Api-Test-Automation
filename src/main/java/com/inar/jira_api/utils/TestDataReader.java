package com.inar.jira_api.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestDataReader {


    public static <T> T readData(String path, Class<T> valueType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FileInputStream fileInputStream;

        try {
            fileInputStream = new FileInputStream("src\\test\\resources\\features\\testdata\\" + path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        return mapper.readValue(fileInputStream, valueType);
    }
    public static String readData2(String fileName) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("src\\test\\resources\\features\\testdata\\" + fileName)));
            return content;
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
            return null;
        }
    }

}
