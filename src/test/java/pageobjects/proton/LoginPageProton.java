package pageobjects.proton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.PageObject;

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

    public InboxPageProton submitLoginFormWithUsernameAndPasswordAndReturnInboxPage(String username, String password) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        submitButton.click();
        return new InboxPageProton(webDriver);
    }

    public void submitLoginFormWithUsernameAndPassword(String username, String password) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        submitButton.click();
    }

    public boolean isInvalidCredentialsMessageDisplayed() {
        return waitWebElement.waitVisibilityOf(invalidCredentialsMessage);
    }

    private boolean isEmptyUsernameMessageDisplayed() {
        return waitWebElement.waitVisibilityOf(emptyUsernameMessage);
    }

    private boolean isEmptyPasswordMessageDisplayed() {
        return waitWebElement.waitVisibilityOf(emptyPasswordMessage);
    }

    public boolean isBothEmptyUsernameAndPasswordMessageDisplayed() {
        return (isEmptyUsernameMessageDisplayed() & isEmptyPasswordMessageDisplayed());
    }

    public void waitLoginFormDisplayed() {
        waitWebElement.waitVisibilityOf(loginForm);
    }

    public void waitFixedAmountOfTimeAfterEmailHasBeenSent() throws InterruptedException {
        waitWebElement.waitFixedAmountOfTime();
    }

}