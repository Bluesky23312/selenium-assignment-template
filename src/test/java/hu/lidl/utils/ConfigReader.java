package hu.lidl.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties.load(file);
        } catch (Exception e) {
            System.out.println("Failed to load config.properties file.");
        }
    }

    // Method to get a property value by its key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}