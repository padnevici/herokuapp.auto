package com.herokuapp.auto.pageObjects;

import com.herokuapp.auto.model.Employee;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEmployeePage extends AbstractPage {
    private Logger logger = LoggerFactory.getLogger(AbstractEmployeePage.class);

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'firstName')]")
    protected WebElement firstNameField;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'lastName')]")
    protected WebElement lastNameField;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'startDate')]")
    protected WebElement startDateField;

    @FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'email')]")
    protected WebElement emailField;

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

    public boolean isFieldHasValidationError(FieldNameEnum fieldName) {
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
}
