package pageobjects.yahoo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.PageObject;

public class HomePageYahoo extends PageObject {

    private final WebDriver webDriver;

    @FindBy(xpath = "(//a[contains(text(), 'Sign in')]) [1]")
    private WebElement signInButton;

    public HomePageYahoo(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public boolean isSignInButtonDisplayed() {
        return waitWebElement.waitVisibilityOf(signInButton);
    }

    public LogInPageYahoo clickSignInButtonAndReturnNewLoginPage(WebDriver driver) {
        signInButton.click();
        return new LogInPageYahoo(webDriver);
    }

}
