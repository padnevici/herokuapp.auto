package com.herokuapp.auto.pageObjects;

import com.herokuapp.auto.model.Employee;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EditEmployerPage extends AbstractEmployeePage {
    private Logger logger = LoggerFactory.getLogger(EditEmployerPage.class);

    @FindBy(how = How.XPATH, using = "//a[@ng-click='browseToOverview()']")
    WebElement backButton;

    @FindBy(how = How.XPATH, using = "//button[@ng-hide='isCreateForm']")
    WebElement updateButton;

    @FindBy(how = How.XPATH, using = "//p[@ng-click='deleteEmployee()']")
    WebElement deleteButton;

    public DashboardPage clickBackBtn() {
        logger.info(String.format("Clicking Back button..."));
        backButton.click();
        return pageHelper.getPage(DashboardPage.class);
    }

    public void clickUpdateBtn() {
        logger.info(String.format("Clicking Update button..."));
        updateButton.click();
    }

    public void clickDeleteBtn() {
        logger.info(String.format("Clicking Delete button..."));
        deleteButton.click();
    }

    public DashboardPage updateAnEmployee(Employee employee) {
        enterEmployeeDetails(employee);
        clickUpdateBtn();
        return pageHelper.getPage(DashboardPage.class);
    }

    public Employee getEmployeeDetails() {
        logger.info("Getting Employee details from Edit page...");
        Employee employee = new Employee();
        employee.setFirstName(firstNameField.getAttribute("value"));
        employee.setLastName(lastNameField.getAttribute("value"));
        employee.setStartDate(startDateField.getAttribute("value"));
        employee.setEmail(emailField.getAttribute("value"));
        return employee;
    }

    @Override
    public void waitForLoad() {
        waitHelper.waitForElementToAppear(updateButton);
    }

    @Override
    public boolean isOnThePage() {
        return backButton.isDisplayed()
                && updateButton.isDisplayed()
                && deleteButton.isDisplayed()
                && firstNameField.isDisplayed()
                && lastNameField.isDisplayed()
                && startDateField.isDisplayed()
                && emailField.isDisplayed();
    }
}
