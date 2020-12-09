package com.asi.sda.sample.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static com.asi.sda.sample.constant.CommonMessages.GENERIC_ISSUE;

public class JdbcConfig {
    private static final Logger LOGGER = LogManager.getLogger(JdbcConfig.class);

    private static String url;
    private static String username;
    private static String password;

    public JdbcConfig() {
        try {
            Path path = Paths.get(ClassLoader.getSystemResource("jdbc.yml").toURI());
            Properties properties = new Properties();
            properties.load(new FileInputStream(String.valueOf(path)));

            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (Exception exception) {
            LOGGER.error(GENERIC_ISSUE, exception.getMessage());
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
