package com.epam.webdriver;

import com.epam.dataproviders.DataProviderForEmailDelivery;
import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.proton.HomePageProton;
import pageobjects.proton.LogInPageProton;
import pageobjects.proton.InboxPageProton;
import pageobjects.yahoo.HomePageYahoo;
import pageobjects.yahoo.LogInPageYahoo;
import pageobjects.yahoo.InboxPageYahoo;

import java.time.Duration;
import java.util.Properties;

public class EmailDeliveryTests {

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

    @Test(enabled = false, description = "Smoke test for yahoo mail")
    public void titleOfYahooMailTest() {
        webDriver.get("https://www.yahoo.com/");
        Assert.assertEquals(webDriver.getTitle(),
                "Yahoo | Mail, Weather, Search, Politics, News, Finance, Sports & Videos");
    }

    @Test(enabled = true,
            description = "Verify delivery of email from ProtonMail to YahooMail",
            dataProvider = "valid-credentials-for-email-delivery",
            dataProviderClass = DataProviderForEmailDelivery.class)
    public void verifyDeliveryOfEmail(String usernameProton,
                                      String passwordProton,
                                      String usernameYahoo,
                                      String passwordYahoo,
                                      String emailSubjectText,
                                      String emailBodyText) throws InterruptedException {
        webDriver.get("https://protonmail.com/");
        HomePageProton homePageProton = new HomePageProton(webDriver);
        Assert.assertTrue(homePageProton.isLoginButtonDisplayed());
        LogInPageProton logInPageProton = homePageProton.clickLoginButton(webDriver);
        logInPageProton.enterUsername(usernameProton);
        logInPageProton.enterPassword(passwordProton);
        logInPageProton.submitLoginForm();
        InboxPageProton inboxPageProton = new InboxPageProton(webDriver);
        Assert.assertTrue(inboxPageProton.isNewMessageButtonDisplayed());
        inboxPageProton.sendEmailTo(usernameYahoo, emailSubjectText, emailBodyText);
        Assert.assertTrue(inboxPageProton.isSentEmailMessageDisplayed());
        inboxPageProton.clickHeadingDropDownButton();
        inboxPageProton.clickSignOutButton();
        inboxPageProton.waitFixedAmountOfTimeAfterEmailHasBeenSent();
        webDriver.get("https://yahoo.com/");
        HomePageYahoo homePageYahoo = new HomePageYahoo(webDriver);
        Assert.assertTrue(homePageYahoo.isSignInButtonDisplayed());
        LogInPageYahoo logInPageYahoo = homePageYahoo.clickSignInButton(webDriver);
        Assert.assertTrue(logInPageYahoo.isLoginInputDisplayed());
        logInPageYahoo.enterUsername(usernameYahoo);
        logInPageYahoo.clickSubmitLoginButton();
        Assert.assertTrue(logInPageYahoo.isPasswordInputDisplayed());
        logInPageYahoo.enterPassword(passwordYahoo);
        logInPageYahoo.clickSubmitPasswordButton();
        Assert.assertTrue(logInPageYahoo.isMailLinkDisplayed());
        InboxPageYahoo inboxPageYahoo = logInPageYahoo.clickMailLink(webDriver);
        Assert.assertTrue(inboxPageYahoo.isComposeEmailButtonDisplayed());
        Assert.assertTrue(inboxPageYahoo.isInboxFieldDisplayed());
        inboxPageYahoo.clickInboxField();
        Assert.assertTrue(inboxPageYahoo.isLatestEmailInInboxDisplayed());
        Assert.assertTrue(inboxPageYahoo.verifyUnreadStatusOfLatestEmail());
        Assert.assertTrue(inboxPageYahoo.verifySenderOfLatestEmailInInbox(usernameProton));
        Assert.assertTrue(inboxPageYahoo.verifyMessageSubjectOfLatestEmailInInbox(emailSubjectText));
        inboxPageYahoo.clickOnLinkToLatestEmailInInbox();
        Assert.assertTrue(inboxPageYahoo.isBodyOfLatestEmailDisplayed());
        Assert.assertTrue(inboxPageYahoo.verifyBodyOfLatestEmail(emailBodyText));
    }

    @AfterMethod
    public void teardown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}
