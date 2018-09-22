package com.herokuapp.auto.steps;

import com.herokuapp.auto.pageObjects.AbstractPage;
import com.herokuapp.auto.pageObjects.HeaderChunk;
import com.herokuapp.auto.pageObjects.PageNameEnum;
import com.herokuapp.auto.utils.PageHelper;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CommonSteps {
    @Autowired
    private PageHelper pageHelper;

    @Then("^(Login|Dashboard) page is (loaded|still shown)$")
    public void loginPageIsLoaded(String pageName, String type) {
        PageNameEnum pageNameEnum = PageNameEnum.valueOf(pageName + "Page");
        AbstractPage page = pageHelper.getPage(pageNameEnum.getClazz());
        assertThat(String.format("%s page is not loaded...", pageName),
                page.isOnThePage(), is(true));
    }

    @When("^user logs out$")
    public void userLogsOut() throws Throwable {
        pageHelper.getPage(HeaderChunk.class).logout();
    }
}
