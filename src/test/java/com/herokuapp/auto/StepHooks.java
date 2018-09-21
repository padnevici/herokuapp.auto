package com.herokuapp.auto;

import com.herokuapp.auto.utils.BrowserHelper;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StepHooks {
//    private  ApplicationContext context;
//    {
//        context = new AnnotationConfigApplicationContext(AppConfig.class);
//    }

    @Autowired
    private ScenarioContext scenarioContext;

    @Autowired
    private BrowserHelper browserHelper;


    @Before
    public void beforeScenario() {

//        BrowserHelper browserHelper = context.getBean(BrowserHelper.class);
//        context.getBean(ScenarioContext.class);
        scenarioContext.setDriver(browserHelper.initDriver());
    }

    @After
    public void afterScenario() {
//        BrowserHelper browserHelper = context.getBean(BrowserHelper.class);
        browserHelper.quitDriver();
        scenarioContext.setDriver(null);
    }
}
