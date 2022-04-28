package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LogInPage extends PageObject {

    protected WebDriver webDriver;
    private final By usernameInputLocator = By.cssSelector("input[id='username']");
    private final By passwordInputLocator = By.cssSelector("input[id='password']");
    private final By staySignedInLocator = By.cssSelector("input[id='staySignedIn']");
    private final By submitButtonLocator = By.cssSelector("form[name='loginForm'] button[type='submit']");
    private final By invalidCredentialsMessageLocator = By.xpath("//div[contains(text(),'Incorrect login credentials')]");

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

    public LogInPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
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
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(invalidCredentialsMessage));
        return invalidCredentialsMessage.isDisplayed();
    }

}