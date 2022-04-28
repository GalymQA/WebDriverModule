package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends PageObject {

    protected WebDriver driver;
    private final By usernameInputLocator = By.cssSelector("input[id='username']");
    private final By passwordInputLocator = By.cssSelector("input[id='password']");
    private final By staySignedInLocator = By.cssSelector("input[id='staySignedIn']");
    private final By submitButtonLocator = By.cssSelector("form[name='loginForm'] button[type='submit']");

    @FindBy(css = "input[id='username']")
    private WebElement usernameInput;

    @FindBy(css = "input[id='password']")
    private WebElement passwordInput;

    @FindBy(css = "input[id='staySignedIn']")
    private WebElement staySignedInInput;

    @FindBy(css = "form[name='loginForm'] button[type='submit']")
    private WebElement submitButton;

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public boolean isStayCheckedIn() {
        return staySignedInInput.isSelected();
    }

    public void submitLoginForm() {
        submitButton.click();
    }

}
