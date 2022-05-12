package pageobjects.yahoo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.PageObject;

public class LogInPageYahoo extends PageObject {

    private final WebDriver webDriver;

    @FindBy(id = "login-username")
    private WebElement loginInput;

    @FindBy(css = "input[id='login-signin']")
    private WebElement submitLoginButton;

    @FindBy(id = "login-passwd")
    private WebElement passwordInput;

    @FindBy(css = "button[id='login-signin']")
    private WebElement submitPasswordButton;

    @FindBy(css = "a[id='ybarMailLink']")
    private WebElement mailLink;

    public LogInPageYahoo(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public boolean isLoginInputDisplayed() {
        return waitWebElement.waitVisibilityOf(loginInput);
    }

    public void enterUsername(String username) {
        loginInput.clear();
        loginInput.sendKeys(username);
    }

    public void clickSubmitLoginButton() {
        submitLoginButton.click();
    }

    public boolean isPasswordInputDisplayed() {
        return waitWebElement.waitVisibilityOf(passwordInput);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickSubmitPasswordButton() {
        submitPasswordButton.click();
    }

    public boolean isMailLinkDisplayed() {
        return waitWebElement.waitVisibilityOf(mailLink);
    }

    public InboxPageYahoo clickMailLinkAndReturnNewInboxPage(WebDriver webDriver) {
        mailLink.click();
        return new InboxPageYahoo(webDriver);
    }

}
