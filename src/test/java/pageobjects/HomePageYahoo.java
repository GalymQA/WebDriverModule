package pageobjects;

import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class HomePageYahoo extends PageObject {

    protected WebDriver webDriver;
    int durationForExpectedConditions;

    private final By signInButtonLocator = By.xpath("(//a[contains(text(), 'Sign in')]) [1]");

    @FindBy(xpath = "(//a[contains(text(), 'Sign in')]) [1]")
    private WebElement signInButton;

    public HomePageYahoo(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        Properties appProperties = new Properties();
        PropertyLoader.loadProperties(appProperties);
        durationForExpectedConditions = Integer.parseInt(appProperties.getProperty("DURATION_FOR_EXPECTED_CONDITIONS"));
    }

    public boolean isSignInButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        return wait.until(ExpectedConditions.visibilityOf(signInButton)).isDisplayed();
    }

    public LogInPageYahoo clickSignInButton(WebDriver driver) {
        signInButton.click();
        return new LogInPageYahoo(webDriver);
    }

}