package com.herokuapp.auto.steps;

import com.herokuapp.auto.model.Employee;
import com.herokuapp.auto.pageObjects.DashboardPage;
import com.herokuapp.auto.testDataUtils.DataKey;
import com.herokuapp.auto.testDataUtils.ScenarioContext;
import com.herokuapp.auto.utils.PageHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DashboardSteps {
    @Autowired
    PageHelper pageHelper;

    @Autowired
    ScenarioContext scenarioContext;

    @When("^user clicks on (Create) button$")
    public void userClicksOnCreateButton(String action) {
        DashboardPage dashboardPage = pageHelper.getPage(DashboardPage.class);
        dashboardPage.clickCreateBtn();
    }

    @And("^(canceled|created) employer (is|is not) present in the list$")
    public void canceledEmployerIsNotPresentInTheList(String action, String resultType) {
        DashboardPage dashboardPage = pageHelper.getPage(DashboardPage.class);
        Employee employee = (Employee) scenarioContext.getTestData(DataKey.EMPLOYEE);
        if (action.equalsIgnoreCase("created"))
            assertThat(String.format("Following [%s %s] employee cannot be found in the list.", employee.getFirstName(), employee.getLastName()),
                    dashboardPage.checkPresenceOfEmployee(employee), is(true));
        if (action.equalsIgnoreCase("canceled"))
            assertThat(String.format("Following [%s %s] employee is found in the list.", employee.getFirstName(), employee.getLastName()),
                    dashboardPage.checkPresenceOfEmployee(employee), is(false));
    }
}
