package pageobjects.yahoo;

import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;

import java.time.Duration;

public class HomePageYahoo extends PageObject {

    private final WebDriver webDriver;
    private final int durationForExpectedConditions;

    @FindBy(xpath = "(//a[contains(text(), 'Sign in')]) [1]")
    private WebElement signInButton;

    public HomePageYahoo(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        durationForExpectedConditions = Integer.parseInt(PropertyLoader.getProperty("DURATION_FOR_EXPECTED_CONDITIONS"));
    }

    public boolean isSignInButtonDisplayed() {
        return waitVisibilityOf(signInButton);
    }

    public LogInPageYahoo clickSignInButtonAndReturnNewLoginPage(WebDriver driver) {
        signInButton.click();
        return new LogInPageYahoo(webDriver);
    }

    private boolean waitVisibilityOf(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        return wait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
    }

}
