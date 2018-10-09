package com.herokuapp.auto.utils;

import org.openqa.selenium.By;
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

@Component @DependsOn({ "browserHelper" }) public class WaitHelper {
	@Autowired BrowserHelper browserHelper;
	private Logger logger = LoggerFactory.getLogger(WaitHelper.class);
	@Value("${dynamic.wait.max.timeout}") private String dynamicWaitTimeout;

	private WebDriverWait waiter;

	private WebDriverWait initWaiter() {
		if (waiter == null)
			return new WebDriverWait(browserHelper.getDriver(), Integer.parseInt(dynamicWaitTimeout));
		else
			return waiter;
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

	public void waitForElementsToBeMoreThan(By by, int moreThanThis) {
		logger.debug("Just waiting for list of elements to appear...");
		waiter = initWaiter();
		try {
			waiter.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, moreThanThis));
			logger.debug("Web elements have appeared.");
		} catch (WebDriverException ex) {
			logger.error(ex.getMessage());
		}
	}

	public void waitForElementToBeEnabled(WebElement element) {
		logger.debug("Just waiting for elements to appear...");
		waiter = initWaiter();
		try {
			waiter.until(ExpectedConditions.elementToBeClickable(element));
			logger.debug("Web elements have appeared.");
		} catch (WebDriverException ex) {
			logger.error(ex.getMessage());
		}
	}

	public void clearWaiter() {
		waiter = null;
	}
}
