package com.herokuapp.auto.pageObjects;

import com.herokuapp.auto.utils.PageHelper;
import com.herokuapp.auto.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractPage {
    protected WaitHelper waitHelper;
    protected PageHelper pageHelper;

    public abstract void waitForLoad();

    public abstract boolean isOnThePage();

    protected void waitForLoad(WebElement element) {
        waitHelper.waitForElementToAppear(element);
    }

    public void setWaitHelper(WaitHelper waitHelper) {
        this.waitHelper = waitHelper;
    }

    public void setPageHelper(PageHelper pageHelper) {
        this.pageHelper = pageHelper;
    }
}
