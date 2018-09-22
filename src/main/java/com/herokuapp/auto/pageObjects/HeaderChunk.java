package com.herokuapp.auto.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeaderChunk extends AbstractPage {
    private Logger logger = LoggerFactory.getLogger(HeaderChunk.class);

    @FindBy(how = How.XPATH, using = "//p[@ng-click='logout()']")
    private WebElement logoutButton;

    public void clickLogoutBtn() {
        logger.info(String.format("Clicking Logout button..."));
        logoutButton.click();
    }

    public LoginPage logout() {
        logger.info(String.format("Login out from the system..."));
        clickLogoutBtn();
        return pageHelper.getPage(LoginPage.class);
    }

    @Override
    public void waitForLoad() {
        super.waitForLoad(logoutButton);
    }

    @Override
    public boolean isOnThePage() {
        return logoutButton.isDisplayed();
    }
}
