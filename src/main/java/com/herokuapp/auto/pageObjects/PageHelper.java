package com.herokuapp.auto.pageObjects;

import com.herokuapp.auto.utils.BrowserHelper;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PageHelper {

    @Autowired
    BrowserHelper browserHelper;

    private Map<Class, AbstractPage> pages = new HashMap();

    public <T extends AbstractPage> T getPage(Class<T> page) {
        if (!pages.containsKey(page)) {
            pages.put(page, PageFactory.initElements(browserHelper.getDriver(), page));
        }
        AbstractPage pageToReturn = pages.get(page);
        pageToReturn.setDriver(browserHelper.getDriver());
        pageToReturn.waitForLoad();
        return page.cast(pageToReturn);
    }
}
