package com.herokuapp.auto.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends AbstractPage {
    private Logger logger = LoggerFactory.getLogger(LoginPage.class);

    @FindBy(how = How.XPATH, using = "//input[@ng-model='user.name']")
    private WebElement usernameField;

    @FindBy(how = How.XPATH, using = "//input[@ng-model='user.password']")
    private WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    private WebElement loginButton;

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

    public void clickLoginBtn() {
        logger.info(String.format("Clicking Login button..."));
        loginButton.click();
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
    protected void waitForLoad() {
        super.waitForLoad(loginButton);
    }
}
