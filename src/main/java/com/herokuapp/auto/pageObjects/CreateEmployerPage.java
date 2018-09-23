package com.herokuapp.auto.pageObjects;

import com.herokuapp.auto.model.Employee;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateEmployerPage extends AbstractPage {
    private Logger logger = LoggerFactory.getLogger(CreateEmployerPage.class);

    @FindBy(how = How.XPATH, using = "//a[contains(@class,'bCancel')]")
    WebElement cancelButton;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'firstName')]")
    WebElement firstNameField;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'lastName')]")
    WebElement lastNameField;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'startDate')]")
    WebElement startDateField;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'email')]")
    WebElement emailField;

    @FindBy(how = How.XPATH, using = "//button[@ng-show='isCreateForm']")
    WebElement addButton;

    public void enterFirstName(String firstName) {
        logger.info(String.format("Entering [%s] into First Name field...", firstName));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        logger.info(String.format("Entering [%s] into Last Name field...", lastName));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void enterStartDate(String startDate) {
        logger.info(String.format("Entering [%s] into Start Date field...", startDate));
        startDateField.clear();
        startDateField.sendKeys(startDate);
    }

    public void enterEmail(String email) {
        logger.info(String.format("Entering [%s] into Email field...", email));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterEmployeeDetails(Employee employee) {
        enterFirstName(employee.getFirstName());
        enterLastName(employee.getLastName());
        enterStartDate(employee.getStartDate());
        enterEmail(employee.getEmail());
    }

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

    public boolean isFieldHasValidationError(EmployeeFormFieldName fieldName) {
        logger.info("Checking if [{}] has validation error...", fieldName);
        String classAttrContent = "";
        switch (fieldName) {
            case FIRST_NAME:
                classAttrContent = getClassAttributeValue(firstNameField);
                break;
            case LAST_NAME:
                classAttrContent = getClassAttributeValue(lastNameField);
                break;
            case START_DATE:
                classAttrContent = getClassAttributeValue(startDateField);
                break;
            case EMAIL:
                classAttrContent = getClassAttributeValue(emailField);
                break;
        }
        return classAttrContent.contains("ng-invalid");
    }

    private String getClassAttributeValue(WebElement element) {
        String value = element.getAttribute("class");
        return (value == null) ? "" : value;
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
