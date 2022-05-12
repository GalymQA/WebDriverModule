package pageobjects.proton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.PageObject;

public class HomePageProton extends PageObject {

    private final WebDriver webDriver;

    @FindBy(xpath = "//a[contains(text(),'LOG IN')]")
    private WebElement loginButton;

    public HomePageProton(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public boolean isLoginButtonDisplayed() {
        return waitWebElement.waitVisibilityOf(loginButton);
    }

    public LogInPageProton clickLoginButtonAndReturnNewLoginPage(WebDriver webDriver) {
        loginButton.click();
        return new LogInPageProton(webDriver);
    }

}
