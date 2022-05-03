package pageobjects.proton;

import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;

import java.time.Duration;
import java.util.Properties;

public class InboxPageProton extends PageObject {

    private final WebDriver webDriver;
    private final int durationForExpectedConditions;

    private final By newMessageButtonLocator = By.xpath("//button[contains(text(),'New message')]");
    private final By emailToAddressLocator = By.xpath("//input[@data-testid='composer:to']");
    private final By emailSubjectLocator = By.xpath("//input[@data-testid='composer:subject']");
    private final By emailBodyIFrameLocator = By.cssSelector("iframe[data-testid='rooster-iframe']");
    private final By editorForEmailBodyLocator = By.cssSelector("div[id='rooster-editor'] div");
    private final By sendEmailButtonLocator = By.xpath("//button[@data-testid='composer:send-button']");
    private final By emailSentMessageLocator = By.xpath(
            "//div[contains(@class, 'notifications-container')] //span[contains(text(), 'Message sent')]");
    private final By headingDropDownButtonLocator = By.cssSelector(
            "button[data-testid='heading:userdropdown'] span[class='block text-ellipsis user-dropdown-displayName']");
    private final By signOutButtonLocator = By.cssSelector("button[data-testid='userdropdown:button:logout']");


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
        webDriver.switchTo().frame(emailBodyIFrame);
        editorForEmailBody.sendKeys(emailText);
        webDriver.switchTo().defaultContent();
        sendEmailButton.click();
    }

    public boolean isSentEmailMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        return wait.until(ExpectedConditions.visibilityOf(emailSentMessage)).isDisplayed();
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

}
