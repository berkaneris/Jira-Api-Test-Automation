package com.inar.jira_api.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestDataDeleter {

    public static void deleteFileContent(String fileName) {
        try {
            File file = new File("src\\test\\resources\\features\\testdata\\" + fileName);
            Files.write(file.toPath(), new byte[0]); // Write empty bytes to clear content
            System.out.println("File content deleted successfully.");
        } catch (IOException e) {
            System.err.println("Error deleting file content: " + e.getMessage());
        }
    }
}
