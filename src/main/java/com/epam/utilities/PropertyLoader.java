package com.epam.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    private final static String APP_CONFIG_PATH =
            "/home/titan/Desktop/Epam/Solutions/WebDriverSolutions/webdriver-task/src/test/resources/config.properties";

    public static void loadProperties(Properties appProps) {
        try {
            appProps.load(new FileInputStream(APP_CONFIG_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
