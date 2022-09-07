package by.teachmeskills.ui.utils;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class PropertiesLoader {

    public static final String CONFIG_PROPERTIES = "config.properties";

    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();

        try (InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(input);
            log.info("The property {} was successfully loaded", fileName);

        } catch (IOException ex) {
            ex.printStackTrace();
            log.error("The property {} was not loaded, because of error {}", fileName, ex.getCause());
        }
        return properties;
    }

    public static Properties loadProperties() {
        return loadProperties(CONFIG_PROPERTIES);
    }
}
