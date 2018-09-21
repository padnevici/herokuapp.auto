package com.herokuapp.auto.steps;

import com.herokuapp.auto.ScenarioContext;
import com.herokuapp.auto.pageObjects.LoginPage;
import com.herokuapp.auto.pageObjects.PageHelper;
import com.herokuapp.auto.utils.BrowserHelper;
import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class LoginLogoutSteps {
    @Autowired
    ScenarioContext scenarioContext;

    @Autowired
    private BrowserHelper browserHelper;

    @Autowired
    private PageHelper pageHelper;

    @Value("${herokuapp.url}")
    private String herokuappurl;

    @Given("^user is navigated to (Login) page$")
    public void userIsNavigatedToLoginPage(String pageName) throws Throwable {
        browserHelper.navigateTo(herokuappurl);
        LoginPage loginPage = pageHelper.getPage(LoginPage.class);
    }
}
