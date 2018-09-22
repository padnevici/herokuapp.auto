package com.herokuapp.auto.utils;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn({"browserHelper"})
public class WaitHelper {
    private Logger logger = LoggerFactory.getLogger(WaitHelper.class);

    @Autowired
    BrowserHelper browserHelper;

    @Value("${dynamic.wait.max.timeout}")
    private String dynamicWaitTimeout;

    private WebDriverWait waiter;

    private WebDriverWait initWaiter() {
        if (waiter == null)
            return new WebDriverWait(browserHelper.getDriver(), Integer.parseInt(dynamicWaitTimeout));
        else
            return waiter;
    }

    public void waitForElementToAppearOrThrow(WebElement element) {
        logger.debug("Waiting for element to appear or throw an error...");
        waiter = initWaiter();
        waiter.until(ExpectedConditions.visibilityOf(element));
        logger.debug("Web element has appeared.");
    }

    public void waitForElementToAppear(WebElement element) {
        logger.debug("Just waiting for element to appear...");
        waiter = initWaiter();
        try {
            waiter.until(ExpectedConditions.visibilityOf(element));
            logger.debug("Web element has appeared.");
        } catch (WebDriverException ex) {
            logger.error(ex.getMessage());
        }
    }
}
