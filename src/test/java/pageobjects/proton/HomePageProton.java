package pageobjects.proton;

import com.epam.utilities.PropertyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;

import java.time.Duration;
import java.util.Properties;

public class HomePageProton extends PageObject {

    protected WebDriver webDriver;
    int durationForExpectedConditions;

    private final By loginButtonLocator = By.xpath("//a[contains(text(),'LOG IN')]");

    @FindBy(xpath = "//a[contains(text(),'LOG IN')]")
    private WebElement loginButton;

    public HomePageProton(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        Properties appProperties = new Properties();
        PropertyLoader.loadProperties(appProperties);
        durationForExpectedConditions = Integer.parseInt(appProperties.getProperty("DURATION_FOR_EXPECTED_CONDITIONS"));
    }

    public boolean isLoginButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(durationForExpectedConditions));
        return wait.until(ExpectedConditions.visibilityOf(loginButton)).isDisplayed();
    }

    public LogInPageProton clickLoginButton(WebDriver driver) {
        loginButton.click();
        return new LogInPageProton(driver);
    }

}
