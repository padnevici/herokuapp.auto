package com.herokuapp.auto.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BrowserHelper {
    String phantomJsPath;

    {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
            System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
            phantomJsPath = "./drivers/phantomjs.exe";
        } else {
            if (osName.contains("mac")) {
                System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
                System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver");
                phantomJsPath = "./drivers/phantomjs";
            }
        }
    }

    @Value("${browser.type}")
    private String browserType;

    @Autowired
    PageHelper pageHelper;


    private WebDriver driver;
    private Logger logger = LoggerFactory.getLogger(BrowserHelper.class);

    public WebDriver initDriver() {
        logger.info(String.format("Starting the [%s] browser...", browserType));
        switch (browserType.toUpperCase()) {
            case "CHROME":
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            case "PHANTOMJS":
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setJavascriptEnabled(true);
                caps.setCapability("takesScreenshot", true);
                caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomJsPath);
                driver = new PhantomJSDriver(caps);
                break;
            default:
                driver = new ChromeDriver();
        }

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
            pageHelper.clearStoredPages();
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
