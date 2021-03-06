package com.herokuapp.auto.steps;

import com.herokuapp.auto.pageObjects.LoginPage;
import com.herokuapp.auto.utils.BrowserHelper;
import com.herokuapp.auto.utils.PageHelper;
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
    BrowserHelper browserHelper;

    @Autowired
    PageHelper pageHelper;

    @Value("${herokuapp.url}")
    String herokuappurl;

    @Value("${username}")
    String username;

    @Value("${password}")
    String password;


    @Given("^user is navigated to (Login) page$")
    public void userIsNavigatedToLoginPage(String pageName) {
        browserHelper.navigateTo(herokuappurl);
        pageHelper.getPage(LoginPage.class);
    }

    @When("^user logs in with wrong '(.*)' and '(.*)'$")
    public void userLogsInWithWrongLoginAndPassword(String username, String password) {
        LoginPage loginPage = (LoginPage) pageHelper.getCurrentPage();
        loginPage.enterUsername(username);
        loginPage.enterPasswod(password);
        loginPage.clickLoginBtn();
    }

    @Then("^following error message is shown on the page$")
    public void followingErrorMessageIsShownOnThePage(List<String> errorMessage) {
        LoginPage loginPage = (LoginPage) pageHelper.getCurrentPage();
        assertThat("Error message is not shown on login...",
                loginPage.isErrorMessageDisplayed(), is(true));
        assertThat("Error message is not correct...",
                loginPage.getErrorMessageText(), is(errorMessage.get(0)));
    }

    @When("^user logs in with correct credentials$")
    public void userLogsInWithCorrectCredentials() {
        LoginPage loginPage = (LoginPage) pageHelper.getCurrentPage();
        loginPage.loginAs(username, password);
    }
}
