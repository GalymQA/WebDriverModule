package com.epam.webdriver;

import com.epam.dataproviders.DataProviderForProtonMail;
import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.proton.HomePageProton;
import pageobjects.proton.InboxPageProton;
import pageobjects.proton.LogInPageProton;

import java.time.Duration;

public class ProtonMailTests {

    private WebDriver webDriver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        String webDriverName = PropertyLoader.getProperty("WEB_DRIVER_NAME");
        String locationOfWebDriver = PropertyLoader.getProperty("LOCATION_OF_WEB_DRIVER");
        int durationForImplicitWait = Integer.parseInt(PropertyLoader.getProperty("DURATION_FOR_IMPLICIT_WAIT"));
        System.setProperty(webDriverName, locationOfWebDriver);
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(durationForImplicitWait));
    }

    @Test(enabled = true,
            description = "Smoke test for proton mail",
            groups = {"smoke tests"})
    public void verifyTitleOfProtonMail() {
        webDriver.get("https://protonmail.com/");
        Assert.assertEquals(webDriver.getTitle(), "Secure email: ProtonMail is free encrypted email.");
    }

    /**
     * Test might require user interaction to pass CAPTCHA
     */
    @Test(enabled = true,
            description = "Log in with valid username and password to Proton email service",
            dataProvider = "valid-credentials",
            dataProviderClass = DataProviderForProtonMail.class,
            groups = {"task tests"})
    public void validLogInToProtonMail(String username, String password) {
        webDriver.get("https://protonmail.com/");
        HomePageProton homePage = new HomePageProton(webDriver);
        Assert.assertTrue(homePage.isLoginButtonDisplayed());
        LogInPageProton loginPage = homePage.clickLoginButtonAndReturnNewLoginPage(webDriver);
        Assert.assertTrue(loginPage.isStayCheckedInSelected());
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.submitLoginForm();
        InboxPageProton inboxPage = new InboxPageProton(webDriver);
        Assert.assertTrue(inboxPage.isNewMessageButtonDisplayed());
    }

    /**
     * Test might require user interaction to pass CAPTCHA
     */
    @Test(enabled = true,
            description = "Log in with invalid username and password to Proton email service",
            dataProvider = "invalid-credentials",
            dataProviderClass = DataProviderForProtonMail.class,
            groups = {"task tests"})
    public void invalidLogInToProtonMail(String username, String password) {
        webDriver.get("https://protonmail.com/");
        HomePageProton homePage = new HomePageProton(webDriver);
        Assert.assertTrue(homePage.isLoginButtonDisplayed());
        LogInPageProton loginPage = homePage.clickLoginButtonAndReturnNewLoginPage(webDriver);
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
            dataProviderClass = DataProviderForProtonMail.class,
            groups = {"task tests"})
    public void invalidLoginWithEmptyCredentialsToProtonMail(String username, String password) {
        webDriver.get("https://protonmail.com/");
        HomePageProton homePage = new HomePageProton(webDriver);
        Assert.assertTrue(homePage.isLoginButtonDisplayed());
        LogInPageProton loginPage = homePage.clickLoginButtonAndReturnNewLoginPage(webDriver);
        Assert.assertTrue(loginPage.isStayCheckedInSelected());
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.submitLoginForm();
        Assert.assertTrue(loginPage.isEmptyUsernameMessageDisplayed());
        Assert.assertTrue(loginPage.isEmptyPasswordMessageDisplayed());
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://account.protonmail.com/login");
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}