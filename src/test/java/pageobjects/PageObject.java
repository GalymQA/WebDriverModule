package pageobjects;

import com.epam.utilities.PropertyLoader;
import com.epam.utilities.WaitWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class PageObject {

    protected WebDriver webDriver;
    protected final WaitWebElement waitWebElement;

    public PageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        int durationForExpectedConditions =
                Integer.parseInt(PropertyLoader.getProperty("DURATION_FOR_EXPECTED_CONDITIONS"));
        int durationForThreadSleep =
                Integer.parseInt(PropertyLoader.getProperty("DURATION_FOR_THREAD_SLEEP"));
        this.waitWebElement = new WaitWebElement(this.webDriver, durationForExpectedConditions, durationForThreadSleep);
    }

}
