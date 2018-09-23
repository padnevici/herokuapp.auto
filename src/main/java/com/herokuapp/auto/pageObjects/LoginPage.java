package com.herokuapp.auto.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends AbstractPage {
    private Logger logger = LoggerFactory.getLogger(LoginPage.class);

    @FindBy(how = How.XPATH, using = "//input[@ng-model='user.name']")
    WebElement usernameField;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='user.password']")
    WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//p[contains(@class,'error-message')]")
    WebElement errorLabel;

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

    public void clickLoginBtn() {
        logger.info(String.format("Clicking Login button..."));
        loginButton.click();
    }

    public boolean isErrorMessageDisplayed() {
        logger.info(String.format("Checking if error message is shown..."));
        waitHelper.waitForElementToAppear(errorLabel);
        return errorLabel.isDisplayed();
    }

    public String getErrorMessageText() {
        logger.info(String.format("Getting error message text..."));
        return errorLabel.getText();
    }

    public DashboardPage loginAs(String username, String password) {
        logger.info(String.format("Login with valid credentials..."));
        enterUsername(username);
        enterPasswod(password);
        clickLoginBtn();
        return pageHelper.getPage(DashboardPage.class);
    }

    @Override
    public void waitForLoad() {
        waitHelper.waitForElementToAppear(loginButton);
    }

    @Override
    public boolean isOnThePage() {
        return loginButton.isDisplayed()
                && usernameField.isDisplayed()
                && passwordField.isDisplayed();
    }
}
