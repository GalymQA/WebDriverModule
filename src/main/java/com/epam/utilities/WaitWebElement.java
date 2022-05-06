package com.epam.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitWebElement {

    private final WebDriverWait webDriverWait;
    private final Duration durationOfSecondsForExpectedConditions;
    private final Duration durationOfSecondsForThreadSleep;

    public WaitWebElement(WebDriver webDriver, int durationForExpectedConditions, int durationForThreadSleep) {
        durationOfSecondsForThreadSleep = Duration.ofSeconds(durationForThreadSleep);
        durationOfSecondsForExpectedConditions = Duration.ofSeconds(durationForExpectedConditions);
        webDriverWait = new WebDriverWait(webDriver, durationOfSecondsForExpectedConditions);
    }

    public boolean waitVisibilityOf(WebElement webElement) {
        return webDriverWait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
    }

    public void waitFixedAmountOfTime() throws InterruptedException {
        Thread.sleep(durationOfSecondsForThreadSleep.toMillis());
    }

}
