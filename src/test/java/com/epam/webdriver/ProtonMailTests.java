package com.epam.webdriver;

import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LogInPage;

import java.time.Duration;
import java.util.Properties;

public class ProtonMailTests {

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

    @Test(enabled = false, description = "Smoke test for proton mail")
    public void titleOfProtonMailTest() {
        driver.get(urlProtonMail);
        Assert.assertEquals(driver.getTitle(), "Secure email: ProtonMail is free encrypted email.");
    }

    @Test(enabled = true, description = "Log in to Proton email service")
    public void logInToProtonMail() throws InterruptedException {
        driver.get(urlProtonMail);
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isLoginButtonDisplayed());
        LogInPage loginPage = homePage.clickLoginButton();
        Thread.sleep(10000);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
