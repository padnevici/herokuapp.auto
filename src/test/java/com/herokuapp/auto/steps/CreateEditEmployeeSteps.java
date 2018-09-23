package com.herokuapp.auto.steps;

import com.herokuapp.auto.model.Employee;
import com.herokuapp.auto.pageObjects.CreateEmployerPage;
import com.herokuapp.auto.pageObjects.EmployeeFormFieldName;
import com.herokuapp.auto.testDataUtils.DataKey;
import com.herokuapp.auto.testDataUtils.ScenarioContext;
import com.herokuapp.auto.utils.PageHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CreateEditEmployeeSteps {
    @Autowired
    PageHelper pageHelper;

    @Autowired
    ScenarioContext scenarioContext;

    @When("^user fulfill form with following data$")
    public void userFulfillFormWithFollowingData(Employee employee) {
        CreateEmployerPage createEmployerPage = pageHelper.getPage(CreateEmployerPage.class);
        createEmployerPage.enterEmployeeDetails(employee);
        createEmployerPage.clickAddBtn();
        scenarioContext.saveTestData(DataKey.EMPLOYEE, employee);
    }

    @And("^user clicks on (Add|Cancel) button$")
    public void userClicksOnAddButton(String action) {
        CreateEmployerPage createEmployerPage = pageHelper.getPage(CreateEmployerPage.class);
        if (action.equalsIgnoreCase("add")) {
            createEmployerPage.clickAddBtn();
        } else {
            createEmployerPage.clickCancelBtn();
        }
    }

    @And("^field '(.*)' is marked with an error$")
    public void fieldFieldNameIsMarkedWithAnError(String fieldName) {
        EmployeeFormFieldName formFieldName = EmployeeFormFieldName.valueOf(fieldName);
        CreateEmployerPage createEmployerPage = pageHelper.getPage(CreateEmployerPage.class);
        assertThat(String.format("Error message is not shown for [%s] field", formFieldName),
                createEmployerPage.isFieldHasValidationError(formFieldName), is(true));
    }
}
