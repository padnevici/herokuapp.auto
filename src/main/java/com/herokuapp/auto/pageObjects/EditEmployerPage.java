package com.herokuapp.auto.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EditEmployerPage extends CreateEmployerPage {
    private Logger logger = LoggerFactory.getLogger(EditEmployerPage.class);

    @FindBy(how = How.XPATH, using = "//button[@ng-show='isCreateForm']")
    WebElement addButton;

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
        return addButton.isDisplayed();
    }
}
