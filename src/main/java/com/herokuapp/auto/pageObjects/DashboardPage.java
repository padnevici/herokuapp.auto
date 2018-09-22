package com.herokuapp.auto.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {
    private Logger logger = LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(how = How.XPATH, using = "//input[@ng-model='user.name']")
    private WebElement usernameField;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='user.password']")
    private WebElement passwordField;

    @FindBy(how = How.ID, using = "bAdd")
    private WebElement createButton;

    @FindBy(how = How.XPATH, using = "//p[contains(@class,'error-message')]")
    private WebElement errorLabel;

    public void enterUsername(String username) {
        logger.info(String.format("Entering [%s] into username field...", username));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPasswod(String passwrod) {
        logger.info(String.format("Entering [%s] into password field...", passwrod));
        passwordField.clear();
        passwordField.sendKeys(passwrod);
    }

    public void clickCreateBtn() {
        logger.info(String.format("Clicking Create button..."));
        createButton.click();
    }

    public boolean isErrorMessageDisplayed() {
        logger.info(String.format("Checking if error message is shown..."));
        return errorLabel.isDisplayed();
    }

    public String getErrorMessageText() {
        logger.info(String.format("Getting error message text..."));
        return errorLabel.getText();
    }

    public WebElement getErrorLabel() {
        return errorLabel;
    }

    @Override
    public void waitForLoad() {
        super.waitForLoad(createButton);
    }

    @Override
    public boolean isOnThePage() {
        return createButton.isDisplayed();
    }
}
