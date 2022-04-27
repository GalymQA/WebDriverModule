package com.epam.webdriver;

import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Properties;

public class ProtonMailTest {

    final static String PATH_TO_CONFIG_PROPERTIES = "/config.properties";

    public WebDriver driver;

    @Test
    public void verifyTitleOfHomePage() throws InterruptedException {
        Properties appProperties = new Properties();
        PropertyLoader.loadProperties(appProperties, PATH_TO_CONFIG_PROPERTIES);
        final String webDriverName = appProperties.getProperty("WEB_DRIVER_NAME");
        final String locationOfWebDriver = appProperties.getProperty("LOCATION_OF_WEB_DRIVER");
        String urlProtonMail = appProperties.getProperty("URL_PROTON_MAIL");
        System.out.println("URL proton:" + urlProtonMail);
        System.setProperty(webDriverName, locationOfWebDriver);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(urlProtonMail);
        System.out.println(driver.getTitle());
        Thread.sleep(1000);
    }

}
