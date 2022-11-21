package com.example.collectiveproject732.utils;

import java.io.InputStream;
import java.util.Properties;

public final class AppUtils {

    private static final Properties properties;

    static {
        properties = new Properties();

        try {
            ClassLoader classLoader = AppUtils.class.getClassLoader();
            InputStream applicationPropertiesStream = classLoader.getResourceAsStream("application.properties");
            properties.load(applicationPropertiesStream);
        } catch (Exception e) {
            // process the exception
        }
    }

    public static String getUrl(){
        return properties.getProperty("spring.datasource.url");
    }
    public static String getUsername(){
        return properties.getProperty("spring.datasource.username");
    }
    public static String getPassword(){
        return properties.getProperty("spring.datasource.password");
    }
}
