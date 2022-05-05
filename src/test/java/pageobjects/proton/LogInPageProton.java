package pageobjects.proton;

import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.PageObject;

import java.time.Duration;

public class LogInPageProton extends PageObject {

    private final WebDriver webDriver;
    private final int durationForExpectedConditions;

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
        durationForExpectedConditions = Integer.parseInt(PropertyLoader.getProperty("DURATION_FOR_EXPECTED_CONDITIONS"));
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
        return waitVisibilityOf(invalidCredentialsMessage);
    }

    public boolean isEmptyUsernameMessageDisplayed() {
        return waitVisibilityOf(emptyUsernameMessage);
    }

    public boolean isEmptyPasswordMessageDisplayed() {
        return waitVisibilityOf(emptyPasswordMessage);
    }

    private boolean waitVisibilityOf(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        return wait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
    }

}