package com.herokuapp.auto.pageObjects;

import com.herokuapp.auto.model.Employee;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateEmployerPage extends AbstractEmployeePage {
    private Logger logger = LoggerFactory.getLogger(CreateEmployerPage.class);

    @FindBy(how = How.XPATH, using = "//a[contains(@class,'bCancel')]")
    WebElement cancelButton;

    @FindBy(how = How.XPATH, using = "//button[@ng-show='isCreateForm']")
    WebElement addButton;

    public DashboardPage addAnEmployee(Employee employee) {
        enterEmployeeDetails(employee);
        clickAddBtn();
        return pageHelper.getPage(DashboardPage.class);
    }

    public DashboardPage clickCancelBtn() {
        logger.info(String.format("Clicking Cancel button..."));
        cancelButton.click();
        return pageHelper.getPage(DashboardPage.class);
    }

    public void clickAddBtn() {
        logger.info(String.format("Clicking Add button..."));
        addButton.click();
    }

    @Override
    public void waitForLoad() {
        waitHelper.waitForElementToAppear(addButton);
    }

    @Override
    public boolean isOnThePage() {
        return addButton.isDisplayed()
                && cancelButton.isDisplayed()
                && firstNameField.isDisplayed()
                && lastNameField.isDisplayed()
                && startDateField.isDisplayed()
                && emailField.isDisplayed();
    }
}
