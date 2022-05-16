package pageobjects.proton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.PageObject;

public class InboxPageProton extends PageObject {

    private final WebDriver webDriver;

    @FindBy(xpath = "//button[contains(text(),'New message')]")
    private WebElement newMessageButton;

    @FindBy(xpath = "//input[@data-testid='composer:to']")
    private WebElement emailToAddress;

    @FindBy(xpath = "//input[@data-testid='composer:subject']")
    private WebElement emailSubject;

    @FindBy(css = "iframe[data-testid='rooster-iframe']")
    private WebElement emailBodyIFrame;

    @FindBy(css = "div[id='rooster-editor'] div")
    private WebElement editorForEmailBody;

    @FindBy(xpath = "//button[@data-testid='composer:send-button']")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//div[contains(@class, 'notifications-container')] //span[contains(text(), 'Message sent')]")
    private WebElement emailSentMessage;

    @FindBy(css = "button[data-testid='heading:userdropdown'] span[class='block text-ellipsis user-dropdown-displayName']")
    private WebElement headingDropDownButton;

    @FindBy(css = "button[data-testid='userdropdown:button:logout']")
    private WebElement signOutButton;

    public InboxPageProton(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public boolean isNewMessageButtonDisplayed() {
        return waitWebElement.waitVisibilityOf(newMessageButton);
    }

    public InboxPageProton sendEmailTo(String emailTo, String emailSubjectText, String emailBodyText) {
        newMessageButton.click();
        waitWebElement.waitVisibilityOf(emailToAddress);
        emailToAddress.sendKeys(emailTo);
        waitWebElement.waitVisibilityOf(emailSubject);
        emailSubject.sendKeys(emailSubjectText);
        webDriver.switchTo().frame(emailBodyIFrame);
        editorForEmailBody.sendKeys(emailBodyText);
        webDriver.switchTo().defaultContent();
        sendEmailButton.click();
        return this;
    }

    public boolean isSentEmailMessageDisplayed() {
        return waitWebElement.waitVisibilityOf(emailSentMessage);
    }

    public LoginPageProton signOut() {
        headingDropDownButton.click();
        signOutButton.click();
        return new LoginPageProton(webDriver);
    }

}
