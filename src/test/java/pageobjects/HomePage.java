package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageObject {

    protected WebDriver driver;

    private final By loginButtonLocator = By.xpath("//a[contains(text(),'LOG IN')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(text(),'LOG IN')]")
    private WebElement loginButton;

    public boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed();
    }

    public LogInPage clickLoginButton() {
        loginButton.click();
        return new LogInPage(driver);
    }

//
//
//
//    public boolean isInitialized() {
//        return firstName.isDisplayed();
//    }
//
//    public void enterName(String firstName, String lastName){
//        this.firstName.clear();
//        this.firstName.sendKeys(firstName);
//
//        this.lastName.clear();
//        this.lastName.sendKeys(lastName);
//    }
//
//    public void enterAddress(String address, String zipCode){
//        this.address.clear();
//        this.address.sendKeys(address);
//
//        this.zipCode.clear();
//        this.zipCode.sendKeys(zipCode);
//    }
//
//    public ReceiptPage submit(){
//        submitButton.click();
//        return new ReceiptPage(driver);
//    }
}
