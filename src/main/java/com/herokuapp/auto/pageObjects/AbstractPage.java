package com.herokuapp.auto.pageObjects;

import com.herokuapp.auto.utils.BrowserHelper;
import com.herokuapp.auto.utils.PageHelper;
import com.herokuapp.auto.utils.WaitHelper;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractPage {
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
}
