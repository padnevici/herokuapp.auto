package com.herokuapp.auto.utils;

import com.herokuapp.auto.pageObjects.AbstractPage;
import com.herokuapp.auto.pageObjects.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PageHelper {
    private Logger logger = LoggerFactory.getLogger(PageHelper.class);

    @Autowired
    BrowserHelper browserHelper;

    @Autowired
    WaitHelper waitHelper;

    private Map<Class, AbstractPage> pages = new HashMap();
    private AbstractPage currentPage;

    public <T extends AbstractPage> T getPage(Class<T> page) {
        if (!pages.containsKey(page)) {
            logger.debug(String.format("+++> Initialising [%s] as new one", page.getCanonicalName()));
            pages.put(page, PageFactory.initElements(browserHelper.getDriver(), page));
        } else
            logger.debug(String.format("---> Getting [%s] as stored one", page.getCanonicalName()));
        currentPage = pages.get(page);
        currentPage.setWaitHelper(waitHelper);
        currentPage.setPageHelper(this);
        currentPage.setBrowserHelper(browserHelper);
        if (currentPage instanceof LoginPage && currentPage.isAlertPresent())
            currentPage.dismissAlert();
        currentPage.waitForLoad();
        return page.cast(currentPage);
    }

    public AbstractPage getCurrentPage() {
        return currentPage;
    }

    public void clearStoredPages() {
        logger.debug("Clearing out stored page objects...");
        pages.clear();
    }
}
