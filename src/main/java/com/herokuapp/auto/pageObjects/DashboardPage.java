package com.herokuapp.auto.pageObjects;

import com.herokuapp.auto.model.Employee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DashboardPage extends AbstractPage {
    private Logger logger = LoggerFactory.getLogger(DashboardPage.class);

    private final String employeeListLocator = "//div[@id='employee-list-container']//li";

    @FindBy(how = How.ID, using = "bAdd")
    WebElement createButton;

    @FindBy(how = How.ID, using = "bEdit")
    WebElement editButton;

    @FindBy(how = How.ID, using = "bDelete")
    WebElement deleteButton;

    @FindBy(how = How.XPATH, using = employeeListLocator)
    List<WebElement> employeeList;

    public CreateEmployerPage clickCreateBtn() {
        logger.info(String.format("Clicking Create button..."));
        createButton.click();
        return pageHelper.getPage(CreateEmployerPage.class);
    }

    public EditEmployerPage clickEditBtn() {
        logger.info(String.format("Clicking Edit button..."));
        editButton.click();
        return pageHelper.getPage(EditEmployerPage.class);
    }

    public void clickDeleteBtn(String user) {
        logger.info(String.format("Clicking Delete button for [%s]...", user));
        deleteButton.click();
    }

    @Override
    public void waitForLoad() {
        waitHelper.waitForElementToAppear(createButton);
        waitHelper.waitForElementsToBeMoreThan(By.xpath(employeeListLocator), 1);
    }

    @Override
    public boolean isOnThePage() {
        return createButton.isDisplayed()
                && editButton.isDisplayed()
                && deleteButton.isDisplayed()
                && employeeList.size() > 1;
    }

    public boolean checkPresenceOfEmployee(Employee employee) {
        String fullname = employee.getFirstName() + " " + employee.getLastName();
        logger.info("Searching for {} employee...", fullname);
        String xpath = String.format("//li[contains(text(),'%s')]", fullname);
        return browserHelper.getDriver().findElements(By.xpath(xpath)).size() > 0;
    }
}
