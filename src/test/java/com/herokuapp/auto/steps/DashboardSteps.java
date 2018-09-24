package com.herokuapp.auto.steps;

import com.herokuapp.auto.model.Employee;
import com.herokuapp.auto.pageObjects.DashboardPage;
import com.herokuapp.auto.testDataUtils.DataKey;
import com.herokuapp.auto.testDataUtils.ScenarioContext;
import com.herokuapp.auto.utils.BrowserHelper;
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
    BrowserHelper browserHelper;

    @Autowired
    ScenarioContext scenarioContext;

    @When("^user clicks on (Create|Edit|Delete) button on DashboardPage$")
    public void userClicksOnCreateButton(String action) {
        DashboardPage dashboardPage = (DashboardPage) pageHelper.getCurrentPage();
        if (action.equalsIgnoreCase("Create"))
            dashboardPage.clickCreateBtn();
        if (action.equalsIgnoreCase("Edit"))
            dashboardPage.clickEditBtn();
        if (action.equalsIgnoreCase("Delete")) {
            if (browserHelper.isPhantomJs())
                throw new UnsupportedOperationException("Javascript alerts are not supported in headless browser Phantom Js");
            dashboardPage.clickDeleteBtn();
        }
    }

    @And("^(canceled|created|deleted|updated) employee (is|is not) present in the list$")
    public void canceledEmployerIsNotPresentInTheList(String action, String resultType) {
        DashboardPage dashboardPage = pageHelper.getPage(DashboardPage.class);
        Employee employee = (Employee) scenarioContext.getTestData(DataKey.EMPLOYEE);

        if (action.equalsIgnoreCase("created")) {
            assertThat(String.format("Following [%s %s] created employee cannot be found in the list.",
                    employee.getFirstName(), employee.getLastName()),
                    dashboardPage.checkPresenceOfEmployee(employee), is(true));
        }

        if (action.equalsIgnoreCase("updated")) {
            employee = (Employee) scenarioContext.getTestData(DataKey.UPDATED_EMPLOYEE);
            assertThat(String.format("Following [%s %s] updated employee cannot be found in the list.",
                    employee.getFirstName(), employee.getLastName()),
                    dashboardPage.checkPresenceOfEmployee(employee), is(true));
        }

        if (action.equalsIgnoreCase("canceled") || action.equalsIgnoreCase("deleted")) {
            assertThat(String.format("Following [%s %s] deleted/not updated employee is found in the list.",
                    employee.getFirstName(), employee.getLastName()),
                    dashboardPage.checkPresenceOfEmployee(employee), is(false));
        }
    }

    @And("^(Edit|Delete) button is (disabled|enabled)$")
    public void editButtonIsDisabled(String buttonName, String expectedState) {
        DashboardPage dashboardPage = (DashboardPage) pageHelper.getCurrentPage();
        if (buttonName.equalsIgnoreCase("Edit")) {
            if (expectedState.equalsIgnoreCase("enabled")) {
                assertThat(String.format("Edit button is not enabled."),
                        dashboardPage.isEditBtnEnabled(), is(true));
            } else {
                assertThat(String.format("Edit button is not disabled."),
                        dashboardPage.isEditBtnEnabled(), is(false));
            }
        } else {
            if (expectedState.equalsIgnoreCase("enabled")) {
                assertThat(String.format("Delete button is not enabled."),
                        dashboardPage.isDeleteBtnEnabled(), is(true));
            } else {
                assertThat(String.format("Delete button is not disabled."),
                        dashboardPage.isDeleteBtnEnabled(), is(false));
            }
        }
    }

    @When("^any employer is selected$")
    public void aRandomEmployerIsSelected() {
        DashboardPage dashboardPage = pageHelper.getPage(DashboardPage.class);
        dashboardPage.selectAnyRandomEmployee();
        scenarioContext.saveTestData(DataKey.SELECTED_EMPLOYEE, dashboardPage.getDetailsOfSelectedEmployee());
    }

    @When("^user makes double click on it$")
    public void userMakesDoubleClickOnIt() {
        DashboardPage dashboardPage = pageHelper.getPage(DashboardPage.class);
        dashboardPage.doubleClickOnSelectedEmployee((Employee) scenarioContext.getTestData(DataKey.SELECTED_EMPLOYEE));
    }

    @When("^user opens details for (created|updated) employee$")
    public void userOpensDetailsForCreatedEmployee(String type) {
        DashboardPage dashboardPage = pageHelper.getPage(DashboardPage.class);
        Employee employee = (type.equalsIgnoreCase("created"))
                ? (Employee) scenarioContext.getTestData(DataKey.CREATED_EMPLOYEE)
                : (Employee) scenarioContext.getTestData(DataKey.UPDATED_EMPLOYEE);
        dashboardPage.selectEmployeeAndOpenDetails(employee);
    }

    @When("^created employee is selected$")
    public void createdEmployeeIsSelected() {
        DashboardPage dashboardPage = pageHelper.getPage(DashboardPage.class);
        Employee employee = (Employee) scenarioContext.getTestData(DataKey.CREATED_EMPLOYEE);
        dashboardPage.selectEmployee(employee);
    }

    @And("^user (confirms|cancels) delete action$")
    public void userConfirmsDeleteAction(String action) {
        DashboardPage dashboardPage = pageHelper.getPage(DashboardPage.class);
        if (action.equalsIgnoreCase("confirms"))
            dashboardPage.confirmDeleteAction();
        else
            dashboardPage.dismissDeleteAction();
    }
}
