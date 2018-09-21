package com.herokuapp.auto;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
public class ScenarioContext {
    WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
