package com.herokuapp.auto.pageObjects;

import com.herokuapp.auto.utils.BrowserHelper;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PageHelper {

    @Autowired
    BrowserHelper browserHelper;

    @Value("${dynamic.wait.max.timeout}")
    private String dynamicWaitTimeout;

    private Map<Class, AbstractPage> pages = new HashMap();
    private AbstractPage currentPage;

    public <T extends AbstractPage> T getPage(Class<T> page) {
        if (!pages.containsKey(page)) {
            pages.put(page, PageFactory.initElements(browserHelper.getDriver(), page));
        }
        currentPage = pages.get(page);
        currentPage.setDriver(browserHelper.getDriver());
        currentPage.setDynamicWaitTimeout(Integer.parseInt(dynamicWaitTimeout));
        currentPage.waitForLoad();
        return page.cast(currentPage);
    }

    public AbstractPage getCurrentPage() {
        return currentPage;
    }
}
