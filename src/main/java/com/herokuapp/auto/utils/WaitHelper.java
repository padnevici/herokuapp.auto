package com.herokuapp.auto.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn({"browserHelper"})
public class WaitHelper {
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

    public void waitForElementToAppear(WebElement element) {
        waiter = initWaiter();
        waiter.until(ExpectedConditions.visibilityOf(element));
    }
}
