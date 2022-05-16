package pageobjects.yahoo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.PageObject;

public class LoginPageYahoo extends PageObject {

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

    public LoginPageYahoo(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public InboxPageYahoo submitLoginFormAndReturnInboxPage(String username, String password) {
        loginInput.clear();
        loginInput.sendKeys(username);
        submitLoginButton.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
        submitPasswordButton.click();
        mailLink.click();
        return new InboxPageYahoo(webDriver);
    }

}
