package pageobjects.proton;

import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;

import java.time.Duration;

public class InboxPageProton extends PageObject {

    private final WebDriver webDriver;
    private final int durationForExpectedConditions;

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
        durationForExpectedConditions = Integer.parseInt(PropertyLoader.getProperty("DURATION_FOR_EXPECTED_CONDITIONS"));
    }

    public boolean isNewMessageButtonDisplayed() {
        return waitVisibilityOf(newMessageButton);
    }

    public void sendEmailTo(String emailTo, String emailSubjectText, String emailBodyText) {
        newMessageButton.click();
        waitVisibilityOf(emailToAddress);
        emailToAddress.sendKeys(emailTo);
        waitVisibilityOf(emailSubject);
        emailSubject.sendKeys(emailSubjectText);
        webDriver.switchTo().frame(emailBodyIFrame);
        editorForEmailBody.sendKeys(emailBodyText);
        webDriver.switchTo().defaultContent();
        sendEmailButton.click();
    }

    public boolean isSentEmailMessageDisplayed() {
        return waitVisibilityOf(emailSentMessage);
    }

    public void clickHeadingDropDownButton() {
        headingDropDownButton.click();
    }

    public void clickSignOutButton() {
        signOutButton.click();
    }

    public void waitFixedAmountOfTimeAfterEmailHasBeenSent() throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(durationForExpectedConditions).toMillis());
    }

    private boolean waitVisibilityOf(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        return wait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
    }

}
