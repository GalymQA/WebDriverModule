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

    public void passCaptcha() throws InterruptedException {

        System.out.println("************************************");
        System.out.println("After submission : " + driver.getTitle());
        System.out.println("************************************");

        driver.switchTo().frame(primaryIframe);
        System.out.println("************************************");
        System.out.println("Title 1: " + driver.getTitle());
//        System.out.println(driver.findElement(By.tagName("title")).getAttribute("innerHTML"));
        System.out.println("************************************");
        driver.switchTo().frame(secondaryIframe);
                System.out.println("************************************");
        System.out.println("Title 2: " + driver.getTitle());
//        System.out.println(driver.findElement(By.tagName("title")).getAttribute("innerHTML"));
        System.out.println("************************************");
        checkboxOfCaptcha.click();
        Thread.sleep(2000);

//        System.out.println("************************************");
//        System.out.println("After submission : " + driver.getTitle());
//        System.out.println("************************************");
//
//        WebElement iframe = driver.findElement(By.cssSelector("div.modal-two iframe"));
//        driver.switchTo().frame(iframe);
//        System.out.println("************************************");
//        System.out.println("Title 1: " + driver.getTitle());
//        System.out.println(driver.findElement(By.tagName("title")).getAttribute("innerHTML"));
//        System.out.println("************************************");
//        WebElement iframe2 = driver.findElement(By.cssSelector("iframe"));
//        driver.switchTo().frame(iframe2);
//        System.out.println("************************************");
//        System.out.println("Title 2: " + driver.getTitle());
//        System.out.println(driver.findElement(By.tagName("title")).getAttribute("innerHTML"));
//        System.out.println("************************************");

//        driver.findElement(By.id("checkbox")).click();

    }

}
