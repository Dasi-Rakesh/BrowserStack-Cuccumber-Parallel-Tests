package managers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private final Properties properties;
    private final String propertyFilePath = "configs//Configuration.properties";

    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getAccessKey() {
        String testDataResourcePath = properties.getProperty("access_key");
        if (testDataResourcePath != null)
            return testDataResourcePath;
        else
            throw new RuntimeException(
                    "Test Data Resource Path not specified in the Configuration.properties file for the Key:testDataResourcePath");
    }

    public String getUsername() {
        String url = properties.getProperty("username");
        if (url != null)
            return url;
        else
            throw new RuntimeException("Site Admin Url not specified in the Configuration.properties file.");
    }

    public String getEnvironment() {
        String url = properties.getProperty("environment");
        if (url != null)
            return url;
        else
            throw new RuntimeException("Site Admin Url not specified in the Configuration.properties file.");
    }


}
