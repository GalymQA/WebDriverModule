package pageobjects.yahoo;

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

public class LogInPageYahoo extends PageObject {

    private final WebDriver webDriver;
    private final int durationForExpectedConditions;

    private final By loginInputLocator = By.cssSelector("input[id='login-username']");
    private final By submitLoginButtonLocator = By.cssSelector("input[id='login-signin']");
    private final By passwordInputLocator = By.cssSelector("input[id='login-passwd']");
    private final By submitPasswordButtonLocator = By.cssSelector("button[id='login-signin']");
    private final By mailLinkLocator = By.cssSelector("a[id='ybarMailLink']");

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
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        return wait.until(ExpectedConditions.visibilityOf(loginInput)).isDisplayed();
    }

    public void enterUsername(String username) {
        loginInput.clear();
        loginInput.sendKeys(username);
    }

    public void clickSubmitLoginButton() {
        submitLoginButton.click();
    }

    public boolean isPasswordInputDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        return wait.until(ExpectedConditions.visibilityOf(passwordInput)).isDisplayed();
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickSubmitPasswordButton() {
        submitPasswordButton.click();
    }

    public boolean isMailLinkDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        return wait.until(ExpectedConditions.visibilityOf(mailLink)).isDisplayed();
    }

    public InboxPageYahoo clickMailLink(WebDriver webDriver) {
        mailLink.click();
        return new InboxPageYahoo(webDriver);
    }

}
