package com.herokuapp.auto.steps;

import com.herokuapp.auto.model.Employee;
import com.herokuapp.auto.pageObjects.AbstractEmployeePage;
import com.herokuapp.auto.pageObjects.CreateEmployerPage;
import com.herokuapp.auto.pageObjects.EditEmployerPage;
import com.herokuapp.auto.pageObjects.FieldNameEnum;
import com.herokuapp.auto.testDataUtils.DataKey;
import com.herokuapp.auto.testDataUtils.ScenarioContext;
import com.herokuapp.auto.utils.BrowserHelper;
import com.herokuapp.auto.utils.PageHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;

public class CreateEditEmployeeSteps {
    @Autowired
    PageHelper pageHelper;

    @Autowired
    BrowserHelper browserHelper;

    @Autowired
    ScenarioContext scenarioContext;

    @When("^user fulfill form with following data on (CreateEmployer|EditEmployer) page")
    public void userFulfillFormWithFollowingData(String pageType, Employee employee) {
        AbstractEmployeePage employeePage = (AbstractEmployeePage) pageHelper.getCurrentPage();
        employeePage.enterEmployeeDetails(employee);
        scenarioContext.saveTestData(DataKey.EMPLOYEE, employee);
    }

    @And("^user clicks on (Add|Cancel) button on CreateEmployeePage$")
    public void userClicksOnAddButton(String action) {
        CreateEmployerPage createEmployerPage = (CreateEmployerPage) pageHelper.getCurrentPage();
        if (action.equalsIgnoreCase("add")) {
            createEmployerPage.clickAddBtn();
            scenarioContext.saveTestData(DataKey.CREATED_EMPLOYEE, scenarioContext.getTestData(DataKey.EMPLOYEE));
        } else {
            createEmployerPage.clickCancelBtn();
        }
    }

    @And("^field '(.*)' is marked with an error$")
    public void fieldFieldNameIsMarkedWithAnError(String fieldName) {
        FieldNameEnum formFieldName = FieldNameEnum.valueOf(fieldName);
        AbstractEmployeePage createEmployerPage = (AbstractEmployeePage) pageHelper.getCurrentPage();
        assertThat(String.format("Error message is not shown for [%s] field", formFieldName),
                createEmployerPage.isFieldHasValidationError(formFieldName), is(true));
    }

    @And("^user clicks on (Update|Delete|Back) button on EditEmployeePage$")
    public void userClicksOnUpdateButton(String action) {
        EditEmployerPage editEmployerPage = (EditEmployerPage) pageHelper.getCurrentPage();
        if (action.equalsIgnoreCase("update")) {
            editEmployerPage.clickUpdateBtn();
            scenarioContext.saveTestData(DataKey.UPDATED_EMPLOYEE, scenarioContext.getTestData(DataKey.EMPLOYEE));
        }
        if (action.equalsIgnoreCase("delete")) {
            if(browserHelper.isPhantomJs())
                throw new UnsupportedOperationException("Javascript alerts are not supported in headless browser Phantom Js");
            editEmployerPage.clickDeleteBtn();
        }
        if (action.equalsIgnoreCase("back")) {
            editEmployerPage.clickBackBtn();
        }
    }

    @Then("^employee details (were|were not) updated$")
    public void employeeDetailsWereNotUpdated(String checkType) {
        EditEmployerPage editEmployerPage = (EditEmployerPage) pageHelper.getCurrentPage();
        Employee orignalEmployee = (checkType.equalsIgnoreCase("were not"))
                ? (Employee) scenarioContext.getTestData(DataKey.CREATED_EMPLOYEE)
                : (Employee) scenarioContext.getTestData(DataKey.UPDATED_EMPLOYEE);
        Employee actualEmployee = editEmployerPage.getEmployeeDetails();
        assertThat("Employee details were updated.", orignalEmployee, samePropertyValuesAs(actualEmployee));
    }
}
