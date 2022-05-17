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

    @BeforeClass(description = "Get properties and set-up a web driver")
    public void getPropertiesAndSetUpWebDriver() {
        String webDriverName = PropertyLoader.getProperty("WEB_DRIVER_NAME");
        String locationOfWebDriver = PropertyLoader.getProperty("LOCATION_OF_WEB_DRIVER");
        int durationForImplicitWait = Integer.parseInt(PropertyLoader.getProperty("DURATION_FOR_IMPLICIT_WAIT"));
        System.setProperty(webDriverName, locationOfWebDriver);
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(durationForImplicitWait));
    }

    /**
     * Test might require user interaction to pass CAPTCHA
     */
    @Test(description = "Log in with both valid username/password to Proton Mail",
            priority = 1)
    public void validLogIn() {
        webDriver.get("https://account.protonmail.com/login");
        LoginPageProton loginPage = new LoginPageProton(webDriver);
        InboxPageProton inboxPage = loginPage.submitValidLoginForm(
                "qapersonstudent1@protonmail.com",
                "epam930542!#$%^");
        boolean statusNewMessageButtonDisplayed = inboxPage.isNewMessageButtonDisplayed();
        signOutFromProtonMailAndWait(inboxPage);
        Assert.assertTrue(statusNewMessageButtonDisplayed,
                "Log in failed with both valid username and valid password");
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
        loginPage = loginPage.submitInvalidLoginForm(username, password);
        Assert.assertTrue(loginPage.isInvalidCredentialsMessageDisplayed(),
                "Message for log in with an invalid username/password has not been displayed");
    }

    @Test(description = "Log in with both empty username and password to Proton Mail",
            priority = 3)
    public void invalidLoginWithEmptyCredentials() {
        webDriver.get("https://account.protonmail.com/login");
        LoginPageProton loginPage = new LoginPageProton(webDriver);
        loginPage = loginPage.submitInvalidLoginForm("", "");
        Assert.assertTrue(loginPage.isBothEmptyUsernameAndPasswordMessageDisplayed(),
                "Message for log in with both empty username and empty password has not been displayed");
    }

    @AfterClass(description = "Quit the web driver")
    public void quitWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}