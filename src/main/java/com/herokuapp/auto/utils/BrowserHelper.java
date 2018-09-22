package com.herokuapp.auto.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BrowserHelper {
    static {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
            System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
        } else {
            if (osName.contains("mac")) {
                System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
                System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver");
            }
        }
    }

    @Value("${browser.type}")
    private String browserType;


    private WebDriver driver;
    private Logger logger = LoggerFactory.getLogger(BrowserHelper.class);

    public WebDriver initDriver() {
        logger.info(String.format("Starting the [%s] browser...", browserType));
        switch (browserType) {
            case "CHROME":
                driver = new ChromeDriver();
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage()
                .timeouts()
                .implicitlyWait(30, TimeUnit.SECONDS)
                .pageLoadTimeout(60, TimeUnit.SECONDS);
        logger.info(String.format("The [%s] browser is started.", browserType));
        return driver;
    }

    public void quitDriver() {
        logger.info(String.format("Closing the [%s] browser...", browserType));
        if (driver != null) {
            logger.info(String.format("The [%s] browser is closed.", browserType));
            driver.quit();
        } else
            logger.info(String.format("The [%s] browser wre not opened.", browserType));
    }

    public void navigateTo(String url) {
        logger.info(String.format("navigating by following [%s] url", url));
        driver.navigate().to(url);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
