package com.epam.webdriver;

import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pageobjects.proton.LoginPageProton;
import pageobjects.proton.InboxPageProton;
import pageobjects.yahoo.LoginPageYahoo;
import pageobjects.yahoo.InboxPageYahoo;

import java.time.Duration;

public class EmailDeliveryTests {

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

    @DataProvider(name = "valid-credentials")
    public static Object[][] provideValidCredentials() {
        return new Object[][]{
                {"qapersonstudent1@protonmail.com",
                        "epam930542!#$%^",
                        "Printer in office #842 is out of work",
                        "Hi, Viktor. The printer in office #842 is not working properly. Could you please fix it.",
                        "ViktorPotapov7575@yahoo.com",
                        "epam34534f#%$"}
        };
    }

    /**
     * 1. Test might require user interaction to pass CAPTCHA
     * 2. After an email is sent from ProtonMail, the test waits for some time at ProtonMail
     * so that the email is delivered to Yahoo Mail
     */
    @Test(description = "Verify delivery of email from ProtonMail to YahooMail",
            dataProvider = "valid-credentials")
    public void verifyDeliveryOfEmailFromProtonToYahoo(String usernameProton,
                                                       String passwordProton,
                                                       String emailSubjectText,
                                                       String emailBodyText,
                                                       String usernameYahoo,
                                                       String passwordYahoo) throws InterruptedException {
        webDriver.get("https://account.protonmail.com/login");
        boolean sentEmailStatus = getStatusOfSentEmailFromProtonToYahooAndWait(usernameProton,
                passwordProton,
                emailSubjectText,
                emailBodyText,
                usernameYahoo);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(sentEmailStatus, "Email has not been sent from Proton Mail");
        webDriver.get("https://login.yahoo.com/");
        boolean statusOfDeliveredEmail = getStatusOfDeliveredEmailAtYahoo(usernameYahoo,
                passwordYahoo,
                usernameProton,
                emailSubjectText,
                emailBodyText);
        softAssert.assertTrue(statusOfDeliveredEmail,
                "The delivered email to Yahoo Mail has not passed all checks");
        softAssert.assertAll();
    }

    private boolean getStatusOfSentEmailFromProtonToYahooAndWait(String usernameProton,
                                                                 String passwordProton,
                                                                 String emailSubjectText,
                                                                 String emailBodyText,
                                                                 String usernameYahoo) throws InterruptedException {
        LoginPageProton logInPageProton = new LoginPageProton(webDriver);
        InboxPageProton inboxPageProton = logInPageProton.submitValidLoginForm(usernameProton, passwordProton);
        inboxPageProton = inboxPageProton.sendEmailTo(usernameYahoo, emailSubjectText, emailBodyText);
        boolean sentEmailStatus = inboxPageProton.isSentEmailMessageDisplayed();
        logInPageProton = inboxPageProton.signOut();
        logInPageProton.waitFixedAmountOfTimeAfterEmailHasBeenSent();
        return sentEmailStatus;
    }

    private boolean getStatusOfDeliveredEmailAtYahoo(String usernameYahoo,
                                                     String passwordYahoo,
                                                     String usernameProton,
                                                     String emailSubjectText,
                                                     String emailBodyText) {
        LoginPageYahoo loginPageYahoo = new LoginPageYahoo(webDriver);
        InboxPageYahoo inboxPageYahoo = loginPageYahoo.submitValidLoginForm(usernameYahoo, passwordYahoo);
        boolean unreadStatus = inboxPageYahoo.verifyUnreadStatusOfLatestEmail();
        boolean senderStatus = inboxPageYahoo.verifySenderOfLatestEmailInInbox(usernameProton);
        boolean emailSubjectStatus = inboxPageYahoo.verifyMessageSubjectOfLatestEmailInInbox(emailSubjectText);
        inboxPageYahoo = inboxPageYahoo.clickOnLinkToLatestEmailInInbox();
        boolean emailBodyStatus = inboxPageYahoo.verifyBodyOfLatestEmail(emailBodyText);
        return (unreadStatus && senderStatus && emailSubjectStatus && emailBodyStatus);
    }

    @AfterClass(description = "Quit the web driver")
    public void quitWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}