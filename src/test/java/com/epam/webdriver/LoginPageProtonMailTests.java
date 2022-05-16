package com.epam.webdriver;

import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.proton.InboxPageProton;
import pageobjects.proton.LogInPageProton;

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

    @Test(description = "Health test for the login page of Proton Mail",
            priority = 0,
            groups = {"health tests"})
    public void healthCheckOfLoginPage() {
        webDriver.get("https://account.protonmail.com/login");
        Assert.assertEquals(webDriver.getTitle(), "Proton Account");
    }

    /**
     * Test might require user interaction to pass CAPTCHA
     */
    @DataProvider(name = "valid-credentials")
    public static Object[][] provideValidCredentials() {
        return new Object[][]{
                {"qapersonstudent1@protonmail.com", "epam930542!#$%^"}
        };
    }

    @Test(description = "Log in with both valid username/password to Proton Mail",
            priority = 1,
            dependsOnMethods = "healthCheckOfLoginPage",
            dataProvider = "valid-credentials",
            groups = {"task tests"})
    public void validLogIn(String username, String password) {
        webDriver.get("https://account.protonmail.com/login");
        LogInPageProton loginPage = new LogInPageProton(webDriver);
        InboxPageProton inboxPage = loginPage.submitLoginFormWithUsernameAndPasswordAndReturnInboxPage(
                username,
                password);
        Assert.assertTrue(inboxPage.isNewMessageButtonDisplayed());
    }

    /**
     * Test might require user interaction to pass CAPTCHA
     */
    @DataProvider(name = "invalid-credentials")
    public static Object[][] provideInvalidCredentials() {
        return new Object[][]{
                {"apersonstudent1@protonmail.com", "epam930542!#$%^"},
                {"qapersonstudent1@protonmail.com", "pam930542!#$%^"},
                {"apersonstudent1@protonmail.com", "pam930542!#$%^"},
        };
    }

    @Test(description = "Log in with invalid username and/or password to Proton Mail",
            priority = 2,
            dependsOnMethods = "healthCheckOfLoginPage",
            dataProvider = "invalid-credentials",
            groups = {"task tests"})
    public void invalidLogInWithInvalidCredentials(String username, String password) throws InterruptedException {
        webDriver.get("https://account.protonmail.com/login");
        LogInPageProton loginPage = new LogInPageProton(webDriver);
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
            dependsOnMethods = "healthCheckOfLoginPage",
            dataProvider = "empty-credentials",
            groups = {"task tests"})
    public void invalidLoginWithEmptyCredentials(String username, String password) {
        webDriver.manage().deleteAllCookies();
        webDriver.get("https://account.protonmail.com/login");
        LogInPageProton loginPage = new LogInPageProton(webDriver);
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