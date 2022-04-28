package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InboxPage extends PageObject {

    protected WebDriver webDriver;
    private final By newMessageButtonLocator = By.xpath("//button[contains(text(),'New message')]") ;

    @FindBy(xpath = "//button[contains(text(),'New message')]")
    private WebElement newMessageButton;

    public InboxPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isNewMessageButtonDisplayed() {
        return newMessageButton.isDisplayed();
    }

}
