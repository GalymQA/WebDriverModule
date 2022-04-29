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

public class InboxPageYahoo extends PageObject {

    protected WebDriver webDriver;
    int durationForExpectedConditions;

    private final By composeEmailButtonLocator = By.cssSelector("a[data-test-id='compose-button']");

    @FindBy(css = "a[data-test-id='compose-button']")
    private WebElement composeEmailButton;

    public InboxPageYahoo(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        Properties appProperties = new Properties();
        PropertyLoader.loadProperties(appProperties);
        durationForExpectedConditions = Integer.parseInt(appProperties.getProperty("DURATION_FOR_EXPECTED_CONDITIONS"));
    }

    public boolean isComposeEmailButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        return wait.until(ExpectedConditions.visibilityOf(composeEmailButton)).isDisplayed();
    }

}
