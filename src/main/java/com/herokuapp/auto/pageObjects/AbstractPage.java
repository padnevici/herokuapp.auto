package com.herokuapp.auto.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
public class AbstractPage {
    protected WebDriver driver;
    private int dynamicWaitTimeout;

    protected void waitForLoad() {
    }

    protected void waitForLoad(WebElement element) {
        WebDriverWait waiter = new WebDriverWait(driver, dynamicWaitTimeout);
        waiter.until(ExpectedConditions.visibilityOf(element));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public int getDynamicWaitTimeout() {
        return dynamicWaitTimeout;
    }

    public void setDynamicWaitTimeout(int dynamicWaitTimeout) {
        this.dynamicWaitTimeout = dynamicWaitTimeout;
    }
}
