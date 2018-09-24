package com.herokuapp.auto.pageObjects;

import com.herokuapp.auto.model.Employee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

public class DashboardPage extends AbstractPage {
    private Logger logger = LoggerFactory.getLogger(DashboardPage.class);

    private final String employeeListLocator = "//div[@id=\"employee-list-container\"]//li";
    private final String employeeLocatorPattern = "//li[contains(text(),\"%s\")]";

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

    public void clickDeleteBtn() {
        logger.info(String.format("Clicking Delete button for selected employee..."));
        deleteButton.click();
    }

    public void selectAnyRandomEmployee() {
        logger.info("Selecting any random Employee...");
        employeeList.get(new Random().nextInt(employeeList.size())).click();
        waitHelper.waitForElementToBeEnabled(editButton);
        waitHelper.waitForElementToBeEnabled(deleteButton);
    }

    public EditEmployerPage selectEmployeeAndOpenDetails(Employee employee) {
        selectEmployee(employee);
        clickEditBtn();
        return pageHelper.getPage(EditEmployerPage.class);
    }

    public Employee getDetailsOfSelectedEmployee() {
        logger.info("Getting First name and Last name of selected employee in the list...");

        String xpath = employeeListLocator + "[contains(@class,'active')]";
        WebElement element = browserHelper.getDriver().findElement(By.xpath(xpath));

        String names[] = element.getText().split(" ");
        Employee employee = new Employee();
        employee.setFirstName(names[0].trim());
        employee.setLastName(names[1].trim());

        return employee;
    }

    public EditEmployerPage doubleClickOnSelectedEmployee(Employee employee) {
        String fullName = getFullNameOfEmployee(employee);
        logger.info("Double clicking on {} employee...", fullName);
        String xpath = String.format(employeeLocatorPattern, fullName);
        WebElement element = browserHelper.getDriver().findElement(By.xpath(xpath));
        Actions actions = new Actions(browserHelper.getDriver());
        actions.doubleClick(element).build().perform();
        return pageHelper.getPage(EditEmployerPage.class);
    }

    public void selectEmployee(Employee employee) {
        String fullName = getFullNameOfEmployee(employee);
        logger.info("Selecting the {} employee...", fullName);
        String xpath = String.format(employeeLocatorPattern, fullName);
        browserHelper.getDriver().findElement(By.xpath(xpath)).click();
    }

    public DashboardPage confirmDeleteAction() {
        logger.info("Confirming the deletion of employee...");
        super.confirmAlert();
        return pageHelper.getPage(DashboardPage.class);
    }

    public AbstractPage dismissDeleteAction() {
        logger.info("Dismissing the deletion of employee...");
        super.dismissAlert();
        return pageHelper.getCurrentPage();
    }

    public boolean isEditBtnEnabled() {
        logger.info("Checking if Edit button is enabled...");
        return !editButton.getAttribute("class").contains("disabled");
    }

    public boolean isDeleteBtnEnabled() {
        logger.info("Checking if Delete button is enabled...");
        return !deleteButton.getAttribute("class").contains("disabled");
    }

    public boolean checkPresenceOfEmployee(Employee employee) {
        String fullName = getFullNameOfEmployee(employee);
        logger.info("Searching for {} employee...", fullName);
        String xpath = String.format(employeeLocatorPattern, fullName);
        return browserHelper.getDriver().findElements(By.xpath(xpath)).size() > 0;
    }

    @Override
    public void waitForLoad() {
        if (isAlertPresent() == false) {
            waitHelper.waitForElementToAppear(createButton);
            waitHelper.waitForElementsToBeMoreThan(By.xpath(employeeListLocator), 1);
        }
    }

    @Override
    public boolean isOnThePage() {
        return createButton.isDisplayed()
                && editButton.isDisplayed()
                && deleteButton.isDisplayed()
                && employeeList.size() > 1;
    }

    protected String getFullNameOfEmployee(Employee employee) {
        return employee.getFirstName() + " " + employee.getLastName();
    }
}
