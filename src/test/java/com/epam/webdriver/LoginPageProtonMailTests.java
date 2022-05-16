package com.epam.webdriver;

import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.proton.InboxPageProton;
import pageobjects.proton.LoginPageProton;

import java.time.Duration;

public class LoginPageProtonMailTests {

    private WebDriver webDriver;

    @BeforeClass()
    public void setUp() {
        String webDriverName = PropertyLoader.getProperty("WEB_DRIVER_NAME");
        String locationOfWebDriver = PropertyLoader.getProperty("LOCATION_OF_WEB_DRIVER");
        int durationForImplicitWait = Integer.parseInt(PropertyLoader.getProperty("DURATION_FOR_IMPLICIT_WAIT"));
        System.setProperty(webDriverName, locationOfWebDriver);
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(durationForImplicitWait));
    }

    @DataProvider(name = "valid-credentials")
    public static Object[][] provideValidCredentials() {
        return new Object[][]{
                {"qapersonstudent1@protonmail.com", "epam930542!#$%^"}
        };
    }

    /**
     * Test might require user interaction to pass CAPTCHA
     */
    @Test(description = "Log in with both valid username/password to Proton Mail",
            priority = 1,
            dataProvider = "valid-credentials")
    public void validLogIn(String username, String password) {
        webDriver.get("https://account.protonmail.com/login");
        LoginPageProton loginPage = new LoginPageProton(webDriver);
        InboxPageProton inboxPage = loginPage.submitLoginFormWithUsernameAndPasswordAndReturnInboxPage(
                username,
                password);
        boolean statusNewMessageButtonDisplayed = inboxPage.isNewMessageButtonDisplayed();
        signOutFromProtonMailAndWait(inboxPage);
        Assert.assertTrue(statusNewMessageButtonDisplayed);
    }

    private void signOutFromProtonMailAndWait(InboxPageProton inboxPage) {
        LoginPageProton loginPageProton = inboxPage.signOut();
        loginPageProton.waitLoginFormDisplayed();
    }

    @DataProvider(name = "invalid-credentials")
    public static Object[][] provideInvalidCredentials() {
        return new Object[][]{
                {"apersonstudent1@protonmail.com", "epam930542!#$%^"},
                {"qapersonstudent1@protonmail.com", "pam930542!#$%^"},
                {"apersonstudent1@protonmail.com", "pam930542!#$%^"},
        };
    }

    /**
     * Test might require user interaction to pass CAPTCHA
     */
    @Test(description = "Log in with invalid username and/or password to Proton Mail",
            priority = 2,
            dataProvider = "invalid-credentials")
    public void invalidLogInWithInvalidCredentials(String username, String password) {
        webDriver.get("https://account.protonmail.com/login");
        LoginPageProton loginPage = new LoginPageProton(webDriver);
        loginPage.submitLoginFormWithUsernameAndPassword(username, password);
        Assert.assertTrue(loginPage.isInvalidCredentialsMessageDisplayed());
    }

    @DataProvider(name = "empty-credentials")
    public static Object[][] provideEmptyCredentials() {
        return new Object[][]{
                {"", ""}
        };
    }

    @Test(description = "Log in with both empty username and password to Proton Mail",
            priority = 3,
            dataProvider = "empty-credentials")
    public void invalidLoginWithEmptyCredentials(String username, String password) {
        webDriver.get("https://account.protonmail.com/login");
        LoginPageProton loginPage = new LoginPageProton(webDriver);
        loginPage.submitLoginFormWithUsernameAndPassword(username, password);
        Assert.assertTrue(loginPage.isBothEmptyUsernameAndPasswordMessageDisplayed());
    }

    @AfterClass()
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}