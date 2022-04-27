package com.epam.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyLoader {

    private final static String ROOT_PATH = "/home/titan/Desktop/Epam/Solutions/WebDriverSolutions/webdriver-task/src/resources";

    public static void loadProperties(Properties appProps, String pathToProps) {
        String appConfigPath;
        appConfigPath = ROOT_PATH + pathToProps;
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
