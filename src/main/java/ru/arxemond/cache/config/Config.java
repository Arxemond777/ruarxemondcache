package ru.arxemond.cache.config;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public enum Config {
    INSTANCE;

    private final Properties properties;

    Config() {
        properties = new Properties();
        String propFileName = "config.properties";
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {

            if (inputStream == null)
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");

            properties.load(inputStream);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }
}
