package pageobjects.yahoo;

import com.epam.utilities.PropertyLoader;
import com.epam.utilities.WaitWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.PageObject;

public class HomePageYahoo extends PageObject {

    private final WebDriver webDriver;
    private final WaitWebElement waitWebElement;

    @FindBy(xpath = "(//a[contains(text(), 'Sign in')]) [1]")
    private WebElement signInButton;

    public HomePageYahoo(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        int durationForExpectedConditions =
                Integer.parseInt(PropertyLoader.getProperty("DURATION_FOR_EXPECTED_CONDITIONS"));
        this.waitWebElement = new WaitWebElement(this.webDriver, durationForExpectedConditions);
    }

    public boolean isSignInButtonDisplayed() {
        return waitWebElement.waitVisibilityOf(signInButton);
    }

    public LogInPageYahoo clickSignInButtonAndReturnNewLoginPage(WebDriver driver) {
        signInButton.click();
        return new LogInPageYahoo(webDriver);
    }

}
