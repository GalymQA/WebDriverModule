package pageobjects.proton;

import com.epam.utilities.PropertyLoader;
import com.epam.utilities.WaitWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.PageObject;

public class LogInPageProton extends PageObject {

    private final WebDriver webDriver;
    private final WaitWebElement waitWebElement;

    @FindBy(css = "input[id='username']")
    private WebElement usernameInput;

    @FindBy(css = "input[id='password']")
    private WebElement passwordInput;

    @FindBy(css = "input[id='staySignedIn']")
    private WebElement staySignedInInput;

    @FindBy(css = "form[name='loginForm'] button[type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[contains(text(),'Incorrect login credentials')]")
    private WebElement invalidCredentialsMessage;

    @FindBy(xpath = "//label[@for='username'] //span[contains(text(), 'This field is required')]")
    private WebElement emptyUsernameMessage;

    @FindBy(xpath = "//label[@for='password'] //span[contains(text(), 'This field is required')]")
    private WebElement emptyPasswordMessage;

    public LogInPageProton(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        int durationForExpectedConditions =
                Integer.parseInt(PropertyLoader.getProperty("DURATION_FOR_EXPECTED_CONDITIONS"));
        int durationForThreadSleep =
                Integer.parseInt(PropertyLoader.getProperty("DURATION_FOR_THREAD_SLEEP"));
        this.waitWebElement = new WaitWebElement(this.webDriver, durationForExpectedConditions, durationForThreadSleep);
    }

    public void enterUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public boolean isStayCheckedInSelected() {
        return staySignedInInput.isSelected();
    }

    public void submitLoginForm() {
        submitButton.click();
    }

    public boolean isInvalidCredentialsMessageDisplayed() {
        return waitWebElement.waitVisibilityOf(invalidCredentialsMessage);
    }

    public boolean isEmptyUsernameMessageDisplayed() {
        return waitWebElement.waitVisibilityOf(emptyUsernameMessage);
    }

    public boolean isEmptyPasswordMessageDisplayed() {
        return waitWebElement.waitVisibilityOf(emptyPasswordMessage);
    }

}