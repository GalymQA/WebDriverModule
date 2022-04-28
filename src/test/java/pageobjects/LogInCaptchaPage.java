package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInCaptchaPage extends PageObject {

    protected WebDriver driver;

    private final By primaryIframeLocator = By.cssSelector("div.modal-two iframe");
    private final By secondaryIframeLocator = By.cssSelector("iframe");
    private final By checkboxOfCaptchaLocator = By.id("checkbox");

    @FindBy(css = "div.modal-two iframe")
    private WebElement primaryIframe;

    @FindBy(css = "iframe")
    private WebElement secondaryIframe;

    @FindBy(id = "checkbox")
    private WebElement checkboxOfCaptcha;

    public LogInCaptchaPage(WebDriver driver) {
        super(driver);
    }

    public void passCaptcha(int windowHandlesSize) {
        if (windowHandlesSize > 1) {
            driver.switchTo().frame(primaryIframe);
            driver.switchTo().frame(secondaryIframe);
            checkboxOfCaptcha.click();
        }
    }

}
