package com.herokuapp.auto;

import com.herokuapp.auto.utils.BrowserHelper;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class StepHooks {

    @Autowired
    private ScenarioContext scenarioContext;

    @Autowired
    private BrowserHelper browserHelper;


    @Before
    public void beforeScenario() {
        browserHelper.initDriver();
    }

    @After
    public void afterScenario() {
        browserHelper.quitDriver();
    }
}
