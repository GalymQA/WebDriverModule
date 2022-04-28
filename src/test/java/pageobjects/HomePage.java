package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageObject {

    protected WebDriver driver;

    private final By loginButtonLocator = By.xpath("//a[contains(text(),'LOG IN')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(text(),'LOG IN')]")
    private WebElement loginButton;

    public boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed();
    }

    public LogInPage clickLoginButton(WebDriver driver) {
        loginButton.click();
        return new LogInPage(driver);
    }

}
