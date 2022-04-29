package com.epam.webdriver;

import com.epam.dataproviders.DataProviderForProtonMail;
import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.InboxPage;
import pageobjects.LogInPage;

import java.time.Duration;
import java.util.Properties;

public class ProtonMailTests {

    private WebDriver webDriver;

    @BeforeMethod
    public void setUp() {
        Properties appProperties = new Properties();
        PropertyLoader.loadProperties(appProperties);
        String webDriverName = appProperties.getProperty("WEB_DRIVER_NAME");
        String locationOfWebDriver = appProperties.getProperty("LOCATION_OF_WEB_DRIVER");
        int durationForImplicitWait = Integer.parseInt(appProperties.getProperty("DURATION_FOR_IMPLICIT_WAIT"));
        System.setProperty(webDriverName, locationOfWebDriver);
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(durationForImplicitWait));
    }

    @Test(enabled = false, description = "Smoke test for proton mail")
    public void titleOfProtonMailTest() {
        webDriver.get("https://protonmail.com/");
        Assert.assertEquals(webDriver.getTitle(), "Secure email: ProtonMail is free encrypted email.");
    }

    @Test(enabled = false,
            description = "Log in with valid username and password to Proton email service",
            dataProvider = "valid-credentials",
            dataProviderClass = DataProviderForProtonMail.class)
    public void validLogInToProtonMail(String username, String password) {
        webDriver.get("https://protonmail.com/");
        HomePage homePage = new HomePage(webDriver);
        Assert.assertTrue(homePage.isLoginButtonDisplayed());
        LogInPage loginPage = homePage.clickLoginButton(webDriver);
        Assert.assertTrue(loginPage.isStayCheckedInSelected());
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.submitLoginForm();
        InboxPage inboxPage = new InboxPage(webDriver);
        Assert.assertTrue(inboxPage.isNewMessageButtonDisplayed());
    }

    @Test(enabled = false,
            description = "Log in with invalid username and password to Proton email service",
            dataProvider = "invalid-credentials",
            dataProviderClass = DataProviderForProtonMail.class)
    public void invalidLogInToProtonMail(String username, String password) {
        webDriver.get("https://protonmail.com/");
        HomePage homePage = new HomePage(webDriver);
        Assert.assertTrue(homePage.isLoginButtonDisplayed());
        LogInPage loginPage = homePage.clickLoginButton(webDriver);
        Assert.assertTrue(loginPage.isStayCheckedInSelected());
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.submitLoginForm();
        Assert.assertTrue(loginPage.isInvalidCredentialsMessageDisplayed());
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://account.protonmail.com/login");
    }

    @Test(enabled = true,
            description = "Log in with empty credentials to Proton email service",
            dataProvider = "empty-credentials",
            dataProviderClass = DataProviderForProtonMail.class)
    public void loginWithEmptyCredentialsToProtonMail(String username, String password) {
        webDriver.get("https://protonmail.com/");
        HomePage homePage = new HomePage(webDriver);
        Assert.assertTrue(homePage.isLoginButtonDisplayed());
        LogInPage loginPage = homePage.clickLoginButton(webDriver);
        Assert.assertTrue(loginPage.isStayCheckedInSelected());
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.submitLoginForm();
        Assert.assertTrue(loginPage.isEmptyUsernameMessageDisplayed());
        Assert.assertTrue(loginPage.isEmptyPasswordMessageDisplayed());
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://account.protonmail.com/login");
    }

    @AfterMethod
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}