package pageobjects.proton;

import com.epam.utilities.PropertyLoader;
import com.epam.utilities.WaitWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.PageObject;

public class HomePageProton extends PageObject {

    private final WebDriver webDriver;
    private final WaitWebElement waitWebElement;

    @FindBy(xpath = "//a[contains(text(),'LOG IN')]")
    private WebElement loginButton;

    public HomePageProton(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        int durationForExpectedConditions =
                Integer.parseInt(PropertyLoader.getProperty("DURATION_FOR_EXPECTED_CONDITIONS"));
        int durationForThreadSleep =
                Integer.parseInt(PropertyLoader.getProperty("DURATION_FOR_THREAD_SLEEP"));
        this.waitWebElement = new WaitWebElement(this.webDriver, durationForExpectedConditions, durationForThreadSleep);
    }

    public boolean isLoginButtonDisplayed() {
        return waitWebElement.waitVisibilityOf(loginButton);
    }

    public LogInPageProton clickLoginButtonAndReturnNewLoginPage(WebDriver webDriver) {
        loginButton.click();
        return new LogInPageProton(webDriver);
    }

}
