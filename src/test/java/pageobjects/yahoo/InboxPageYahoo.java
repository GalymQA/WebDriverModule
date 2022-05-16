package pageobjects.yahoo;

import enums.BooleanStrings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.PageObject;

public class InboxPageYahoo extends PageObject {

    private final WebDriver webDriver;

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
    }

    public boolean verifyUnreadStatusOfLatestEmail() {
        waitWebElement.waitVisibilityOf(linkToLatestEmailInInbox);
        return BooleanStrings.FALSE.toString().equals(linkToLatestEmailInInbox.getAttribute("data-test-read"));
    }

    public boolean verifySenderOfLatestEmailInInbox(String senderOfEmail) {
        waitWebElement.waitVisibilityOf(latestEmailInInbox);
        return senderOfEmail.equals(latestEmailInInbox.getAttribute("title"));
    }

    public boolean verifyMessageSubjectOfLatestEmailInInbox(String messageSubject) {
        waitWebElement.waitVisibilityOf(messageSubjectOfLatestEmailInInbox);
        return messageSubject.equals(messageSubjectOfLatestEmailInInbox.getText());
    }

    public InboxPageYahoo clickOnLinkToLatestEmailInInbox() {
        linkToLatestEmailInInbox.click();
        return this;
    }

    public boolean verifyBodyOfLatestEmail(String messageBody) {
        waitWebElement.waitVisibilityOf(bodyOfLatestEmail);
        return messageBody.equals(bodyOfLatestEmail.getText());
    }

}
