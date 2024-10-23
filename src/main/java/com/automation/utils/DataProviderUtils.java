package com.automation.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataProviderUtils {
	public static Map<String, Object> readJsonFromFile(String filePath) {
        try {
            return new ObjectMapper().readValue(new File(filePath), Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }
}
