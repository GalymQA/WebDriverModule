package com.epam.webdriver;

import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Properties;

public class ProtonMailTest {

    private WebDriver driver;
    private String urlProtonMail;
    private String webDriverName;
    private String locationOfWebDriver;

    @BeforeMethod
    public void setUp() {
        Properties appProperties = new Properties();
        PropertyLoader.loadProperties(appProperties);
        webDriverName = appProperties.getProperty("WEB_DRIVER_NAME");
        locationOfWebDriver = appProperties.getProperty("LOCATION_OF_WEB_DRIVER");
        urlProtonMail = appProperties.getProperty("URL_PROTON_MAIL");
        System.setProperty(webDriverName, locationOfWebDriver);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test(enabled = true, description = "Smoke test for proton mail")
    public void titleOfProtonMail() {
        driver.get(urlProtonMail);
        Assert.assertEquals(driver.getTitle(), "Secure email: ProtonMail is free encrypted email.");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
