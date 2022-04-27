package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends PageObject {

    protected WebDriver driver;
    private final By usernameInputLocator = By.cssSelector("input[id='username']");
    private final By passwordInputLocator = By.cssSelector("input[id='password']");

    @FindBy(css = "input[id='username']")
    private WebElement usernameInput;

    @FindBy(css = "input[id='password']")
    private WebElement passwordInput;

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        this.usernameInput.clear();
        this.usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        this.passwordInput.clear();
        this.passwordInput.sendKeys(password);
    }

}
