package com.herokuapp.auto.pageObjects;

import com.herokuapp.auto.utils.BrowserHelper;
import com.herokuapp.auto.utils.PageHelper;
import com.herokuapp.auto.utils.WaitHelper;
import org.openqa.selenium.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractPage {
    private Logger logger = LoggerFactory.getLogger(AbstractPage.class);
    protected WaitHelper waitHelper;
    protected PageHelper pageHelper;
    protected BrowserHelper browserHelper;

    public abstract void waitForLoad();

    public abstract boolean isOnThePage();

    public void setWaitHelper(WaitHelper waitHelper) {
        this.waitHelper = waitHelper;
    }

    public void setPageHelper(PageHelper pageHelper) {
        this.pageHelper = pageHelper;
    }

    public void setBrowserHelper(BrowserHelper browserHelper) {
        this.browserHelper = browserHelper;
    }

    public void confirmAlert() {
        logger.info("Confirming Alert...");
        Alert alert = browserHelper.getDriver().switchTo().alert();
        alert.accept();
    }

    public void dismissAlert() {
        logger.info("Dismissing Alert...");
        Alert alert = browserHelper.getDriver().switchTo().alert();
        alert.dismiss();
    }

    public boolean isAlertPresent() {
        try {
            if (!browserHelper.isPhantomJs()) {
                browserHelper.getDriver().switchTo().alert();
                return true;
            } else
                return false;
        } catch (Exception Ex) {
            return false;
        }
    }
}
