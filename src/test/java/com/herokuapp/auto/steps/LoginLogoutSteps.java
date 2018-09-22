package com.herokuapp.auto.steps;

import com.herokuapp.auto.ScenarioContext;
import com.herokuapp.auto.pageObjects.LoginPage;
import com.herokuapp.auto.pageObjects.PageHelper;
import com.herokuapp.auto.utils.BrowserHelper;
import com.herokuapp.auto.utils.WaitHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LoginLogoutSteps {
    @Autowired
    ScenarioContext scenarioContext;

    @Autowired
    private BrowserHelper browserHelper;

    @Autowired
    private PageHelper pageHelper;

    @Autowired
    private WaitHelper waitHelper;

    @Value("${herokuapp.url}")
    private String herokuappurl;

    @Given("^user is navigated to (Login) page$")
    public void userIsNavigatedToLoginPage(String pageName) throws Throwable {
        browserHelper.navigateTo(herokuappurl);
        pageHelper.getPage(LoginPage.class);
    }

    @When("^user logs in with wrong '(.*)' and '(.*)'$")
    public void userLogsInWithWrongLoginAndPassword(String username, String password) throws Throwable {
        LoginPage loginPage = pageHelper.getPage(LoginPage.class);
        loginPage.enterUsername(username);
        loginPage.enterPasswod(password);
        loginPage.clickLoginBtn();
    }

    @Then("^following error message is shown on the page$")
    public void followingErrorMessageIsShownOnThePage(List<String> errorMessage) throws Throwable {
        LoginPage loginPage = (LoginPage) pageHelper.getCurrentPage();
        waitHelper.waitForElementToAppear(loginPage.getErrorLabel());
        assertThat("Error message is not shown on login...",
                loginPage.isErrorMessageDisplayed(), is(true));
        assertThat("Error message is not correct...",
                loginPage.getErrorMessageText(), is(errorMessage.get(0)));
    }
}
