package pageobjects;

import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class InboxPageProton extends PageObject {

    protected WebDriver webDriver;
    int durationForExpectedConditions;
    private final By newMessageButtonLocator = By.xpath("//button[contains(text(),'New message')]");
    private final By emailToAddressLocator = By.xpath("//input[@data-testid='composer:to']");
    private final By emailSubjectLocator = By.xpath("//input[@data-testid='composer:subject']");
    private final By sendEmailButtonLocator = By.xpath("//button[@data-testid='composer:send-button']");
    private final By emailSentMessageLocator = By.xpath("//div[contains(@class, 'notifications-container')] //span[contains(text(), 'Message sent')]");

    @FindBy(xpath = "//button[contains(text(),'New message')]")
    private WebElement newMessageButton;

    @FindBy(xpath = "//input[@data-testid='composer:to']")
    private WebElement emailToAddress;

    @FindBy(xpath = "//input[@data-testid='composer:subject']")
    private WebElement emailSubject;

    @FindBy(xpath = "//button[@data-testid='composer:send-button']")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//div[contains(@class, 'notifications-container')] //span[contains(text(), 'Message sent')]")
    private WebElement emailSentMessage;

    public InboxPageProton(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        Properties appProperties = new Properties();
        PropertyLoader.loadProperties(appProperties);
        durationForExpectedConditions = Integer.parseInt(appProperties.getProperty("DURATION_FOR_EXPECTED_CONDITIONS"));
    }

    public boolean isNewMessageButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        return wait.until(ExpectedConditions.visibilityOf(newMessageButton)).isDisplayed();
    }

    public void sendEmailTo(String emailTo, String emailSubjectText, String emailText) {
        newMessageButton.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        wait.until(ExpectedConditions.visibilityOf(emailToAddress)).isDisplayed();
        wait.until(ExpectedConditions.visibilityOf(emailSubject)).isDisplayed();
        emailToAddress.sendKeys(emailTo);
        emailSubject.sendKeys(emailSubjectText);
        sendEmailButton.click();
    }

    public boolean isSentEmailMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        return wait.until(ExpectedConditions.visibilityOf(emailSentMessage)).isDisplayed();
    }

}
