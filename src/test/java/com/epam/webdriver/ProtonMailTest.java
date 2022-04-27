package com.epam.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProtonMailTest {

    public WebDriver driver;
    public static final String PROPERTY_WEBDRIVER = "webdriver.chrome.driver";
    public static final String PROPERTY_LOCATION = "/home/titan/Desktop/Epam/Drivers/chromedriver/chromedriver_linux64/chromedriver";
    public static final String URL = "https://protonmail.com/";

    @Test
    public void verifyTitleOfHomePage() throws InterruptedException {
        System.setProperty(PROPERTY_WEBDRIVER, PROPERTY_LOCATION);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(URL);
        System.out.println(driver.getTitle());
        Thread.sleep(1000);
    }

}
