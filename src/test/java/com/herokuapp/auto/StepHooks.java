package com.herokuapp.auto;

import com.herokuapp.auto.utils.BrowserHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class StepHooks {
    private Logger logger = LoggerFactory.getLogger(StepHooks.class);

    @Autowired
    private ScenarioContext scenarioContext;

    @Autowired
    private BrowserHelper browserHelper;


    @Before
    public void beforeScenario() {
        browserHelper.initDriver();
    }

    @After(order = 1)
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) browserHelper.getDriver()).getScreenshotAs(OutputType.BYTES);
                String testName = scenario.getName();
                scenario.embed(screenshot, "image/png");
                scenario.write(testName);
            } catch (WebDriverException | ClassCastException wde) {
                logger.error("Failed to capture screenshot.");
                logger.error(wde.getMessage());
            }
        }
    }

    @After(order = 0)
    public void afterScenario() {
        browserHelper.quitDriver();
    }
}
