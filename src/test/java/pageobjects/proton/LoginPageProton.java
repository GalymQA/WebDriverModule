package pageobjects.proton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.PageObject;
import pageobjects.yahoo.LoginPageYahoo;

public class LoginPageProton extends PageObject {

    private final WebDriver webDriver;

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "form[name='loginForm']")
    private WebElement loginForm;

    @FindBy(css = "form[name='loginForm'] button[type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[contains(text(),'Incorrect login credentials')]")
    private WebElement invalidCredentialsMessage;

    @FindBy(xpath = "//label[@for='username'] //span[contains(text(), 'This field is required')]")
    private WebElement emptyUsernameMessage;

    @FindBy(xpath = "//label[@for='password'] //span[contains(text(), 'This field is required')]")
    private WebElement emptyPasswordMessage;

    public LoginPageProton(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public InboxPageProton submitValidLoginForm(String username, String password) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        submitButton.click();
        return new InboxPageProton(webDriver);
    }

    public LoginPageProton submitInvalidLoginForm(String username, String password) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        submitButton.click();
        return this;
    }

    public boolean isInvalidCredentialsMessageDisplayed() {
        return waitWebElement.waitVisibilityOf(invalidCredentialsMessage);
    }

    public boolean isBothEmptyUsernameAndPasswordMessageDisplayed() {
        return (isEmptyUsernameMessageDisplayed() && isEmptyPasswordMessageDisplayed());
    }

    public LoginPageProton waitLoginFormDisplayed() {
        waitWebElement.waitVisibilityOf(loginForm);
        return this;
    }

    public LoginPageProton waitFixedAmountOfTimeAfterEmailHasBeenSent() throws InterruptedException {
        waitWebElement.waitFixedAmountOfTime();
        return this;
    }

    private boolean isEmptyUsernameMessageDisplayed() {
        return waitWebElement.waitVisibilityOf(emptyUsernameMessage);
    }

    private boolean isEmptyPasswordMessageDisplayed() {
        return waitWebElement.waitVisibilityOf(emptyPasswordMessage);
    }

}