package com.herokuapp.auto.steps;

import com.herokuapp.auto.PageName;
import com.herokuapp.auto.ScenarioContext;
import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class LoginLogoutSteps {
    @Autowired
    ScenarioContext scenarioContext;

    @Value("${herokuapp.url}")
    private String herokuappurl;

    @Given("^user is navigated to (Login) page$")
    public void userIsNavigatedToLoginPage(String pageName) throws Throwable {
        scenarioContext.getDriver().navigate().to(herokuappurl);
    }
}
