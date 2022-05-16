package pageobjects.proton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.PageObject;

public class LogInPageProton extends PageObject {

    private final WebDriver webDriver;

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

//    @FindBy(id = "staySignedIn")
//    private WebElement staySignedInInput;

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
    }

    public void enterUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void submitLoginForm() {
        submitButton.click();
    }

    public InboxPageProton submitLoginFormWithUsernameAndPasswordAndReturnInboxPage(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        submitLoginForm();
        return new InboxPageProton(webDriver);
    }

    public void submitLoginFormWithUsernameAndPassword(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        submitLoginForm();
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

}