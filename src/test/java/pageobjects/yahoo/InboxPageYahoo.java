package pageobjects.yahoo;

import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;

import java.time.Duration;

public class InboxPageYahoo extends PageObject {

    private final WebDriver webDriver;
    private final int durationForExpectedConditions;

    @FindBy(css = "a[data-test-id='compose-button']")
    private WebElement composeEmailButton;

    @FindBy(css = "div[data-test-folder-container='Inbox']")
    private WebElement inboxField;

    @FindBy(css = "ul[aria-label='Message list'] > li:nth-child(3) > a > div > div > div[data-test-id='senders'] > span")
    private WebElement latestEmailInInbox;

    @FindBy(css = "ul[aria-label='Message list'] > li:nth-child(3) > a > div > div span[data-test-id='message-subject']")
    private WebElement messageSubjectOfLatestEmailInInbox;

    @FindBy(css = "ul[aria-label='Message list'] > li:nth-child(3) > a")
    private WebElement linkToLatestEmailInInbox;

    @FindBy(css = "div[data-test-id='message-view-body-content'] > div > div > div > div:nth-child(1)")
    private WebElement bodyOfLatestEmail;

    public InboxPageYahoo(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        durationForExpectedConditions = Integer.parseInt(PropertyLoader.getProperty("DURATION_FOR_EXPECTED_CONDITIONS"));
    }

    public boolean isComposeEmailButtonDisplayed() {
        return waitVisibilityOf(composeEmailButton);
    }

    public boolean isInboxFieldDisplayed() {
        return waitVisibilityOf(inboxField);
    }

    public void clickInboxField() {
        inboxField.click();
    }

    public boolean isLatestEmailInInboxDisplayed() {
        return waitVisibilityOf(latestEmailInInbox);
    }

    public boolean verifyUnreadStatusOfLatestEmail() {
        return "false".equals(linkToLatestEmailInInbox.getAttribute("data-test-read"));
    }

    public boolean verifySenderOfLatestEmailInInbox(String senderOfEmail) {
        return senderOfEmail.equals(latestEmailInInbox.getAttribute("title"));
    }

    public boolean verifyMessageSubjectOfLatestEmailInInbox(String messageSubject) {
        return messageSubject.equals(messageSubjectOfLatestEmailInInbox.getText());
    }

    public void clickOnLinkToLatestEmailInInbox() {
        linkToLatestEmailInInbox.click();
    }

    public boolean isBodyOfLatestEmailDisplayed() {
        return waitVisibilityOf(bodyOfLatestEmail);
    }

    public boolean verifyBodyOfLatestEmail(String messageBody) {
        return messageBody.equals(bodyOfLatestEmail.getText());
    }

    private boolean waitVisibilityOf(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        return wait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
    }

}
