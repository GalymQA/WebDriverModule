package pageobjects.yahoo;

import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;

import java.time.Duration;
import java.util.Properties;

public class LogInPageYahoo extends PageObject {

    private final WebDriver webDriver;
    private final int durationForExpectedConditions;

    @FindBy(css = "input[id='login-username']")
    private WebElement loginInput;

    @FindBy(css = "input[id='login-signin']")
    private WebElement submitLoginButton;

    @FindBy(css = "input[id='login-passwd']")
    private WebElement passwordInput;

    @FindBy(css = "button[id='login-signin']")
    private WebElement submitPasswordButton;

    @FindBy(css = "a[id='ybarMailLink']")
    private WebElement mailLink;

    public LogInPageYahoo(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        Properties appProperties = new Properties();
        PropertyLoader.loadProperties(appProperties);
        durationForExpectedConditions = Integer.parseInt(appProperties.getProperty("DURATION_FOR_EXPECTED_CONDITIONS"));
    }

    public boolean isLoginInputDisplayed() {
        return waitVisibilityOf(loginInput);
    }

    public void enterUsername(String username) {
        loginInput.clear();
        loginInput.sendKeys(username);
    }

    public void clickSubmitLoginButton() {
        submitLoginButton.click();
    }

    public boolean isPasswordInputDisplayed() {
        return waitVisibilityOf(passwordInput);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickSubmitPasswordButton() {
        submitPasswordButton.click();
    }

    public boolean isMailLinkDisplayed() {
        return waitVisibilityOf(mailLink);
    }

    public InboxPageYahoo clickMailLinkAndReturnNewInboxPage(WebDriver webDriver) {
        mailLink.click();
        return new InboxPageYahoo(webDriver);
    }

    private boolean waitVisibilityOf(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        return wait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
    }

}
