package pageobjects.proton;

import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.PageObject;

import java.time.Duration;
import java.util.Properties;

public class LogInPageProton extends PageObject {

    protected WebDriver webDriver;
    int durationForExpectedConditions;
    private final By usernameInputLocator = By.cssSelector("input[id='username']");
    private final By passwordInputLocator = By.cssSelector("input[id='password']");
    private final By staySignedInLocator = By.cssSelector("input[id='staySignedIn']");
    private final By submitButtonLocator = By.cssSelector("form[name='loginForm'] button[type='submit']");
    private final By invalidCredentialsMessageLocator =
            By.xpath("//div[contains(text(),'Incorrect login credentials')]");
    private final By emptyUsernameMessageLocator =
            By.xpath("//label[@for='username'] //span[contains(text(), 'This field is required')]");
    private final By emptyPasswordMessageLocator =
            By.xpath("//label[@for='password'] //span[contains(text(), 'This field is required')]");

    @FindBy(css = "input[id='username']")
    private WebElement usernameInput;

    @FindBy(css = "input[id='password']")
    private WebElement passwordInput;

    @FindBy(css = "input[id='staySignedIn']")
    private WebElement staySignedInInput;

    @FindBy(css = "form[name='loginForm'] button[type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[contains(text(),'Incorrect login credentials')]")
    private WebElement invalidCredentialsMessage;

    @FindBy(xpath = "//label[@for='username'] //span[contains(text(), 'This field is required')]")
    private WebElement emptyUsernameMessage;

    @FindBy(xpath = "//label[@for='password'] //span[contains(text(), 'This field is required')]")
    private WebElement emptyPasswordMessage;

    public LogInPageProton(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        Properties appProperties = new Properties();
        PropertyLoader.loadProperties(appProperties);
        durationForExpectedConditions = Integer.parseInt(appProperties.getProperty("DURATION_FOR_EXPECTED_CONDITIONS"));
    }

    public void enterUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public boolean isStayCheckedInSelected() {
        return staySignedInInput.isSelected();
    }

    public void submitLoginForm() {
        submitButton.click();
    }

    public boolean isInvalidCredentialsMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        return wait.until(ExpectedConditions.visibilityOf(invalidCredentialsMessage)).isDisplayed();
    }

    public boolean isEmptyUsernameMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        return wait.until(ExpectedConditions.visibilityOf(emptyUsernameMessage)).isDisplayed();
    }

    public boolean isEmptyPasswordMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        return wait.until(ExpectedConditions.visibilityOf(emptyPasswordMessage)).isDisplayed();
    }

}