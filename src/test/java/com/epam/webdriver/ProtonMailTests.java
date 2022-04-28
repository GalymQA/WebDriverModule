package com.epam.webdriver;

import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.InboxPage;
import pageobjects.LogInCaptchaPage;
import pageobjects.LogInPage;

import java.time.Duration;
import java.util.Properties;

public class ProtonMailTests {

    private WebDriver webDriver;
    private String urlProtonMail;
    private String webDriverName;
    private String locationOfWebDriver;
    private int waitTime;
    private String usernameForProtonMail;
    private String passwordForProtonMail;

    @BeforeMethod
    public void setUp() {
        Properties appProperties = new Properties();
        PropertyLoader.loadProperties(appProperties);
        webDriverName = appProperties.getProperty("WEB_DRIVER_NAME");
        locationOfWebDriver = appProperties.getProperty("LOCATION_OF_WEB_DRIVER");
        urlProtonMail = appProperties.getProperty("URL_PROTON_MAIL");
        waitTime = Integer.parseInt(appProperties.getProperty("WAIT_TIME"));
        usernameForProtonMail = appProperties.getProperty("USERNAME_FOR_PROTON_MAIL");
        passwordForProtonMail = appProperties.getProperty("PASSWORD_FOR_PROTON_MAIL");
        System.setProperty(webDriverName, locationOfWebDriver);
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(enabled = false, description = "Smoke test for proton mail")
    public void titleOfProtonMailTest() {
        webDriver.get(urlProtonMail);
        Assert.assertEquals(webDriver.getTitle(), "Secure email: ProtonMail is free encrypted email.");
    }

    @Test(enabled = true, description = "Log in with valid username and password to Proton email service")
    public void validLogInToProtonMail() throws InterruptedException {
        webDriver.get(urlProtonMail);
        HomePage homePage = new HomePage(webDriver);
        Assert.assertTrue(homePage.isLoginButtonDisplayed());
        LogInPage loginPage = homePage.clickLoginButton(webDriver);
        Assert.assertTrue(loginPage.isStayCheckedInSelected());
        loginPage.enterUsername(usernameForProtonMail);
        loginPage.enterPassword(passwordForProtonMail);
        loginPage.submitLoginForm();
        LogInCaptchaPage logInCaptchaPage = new LogInCaptchaPage(webDriver);
        logInCaptchaPage.passCaptcha(webDriver);
        InboxPage inboxPage = new InboxPage(webDriver);
        Assert.assertTrue(inboxPage.isNewMessageButtonDisplayed());
    }

    @AfterMethod
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}
