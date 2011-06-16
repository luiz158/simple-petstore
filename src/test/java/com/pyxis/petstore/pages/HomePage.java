package com.pyxis.petstore.pages;

import com.pyxis.petstore.utils.PageNavigator;
import com.pyxis.petstore.utils.WebDriver;
import cuke4duke.annotation.I18n;

import static junit.framework.Assert.assertEquals;

public class HomePage extends SuperPage {
    public HomePage(PageNavigator pageNavigator, WebDriver webDriver) {
        super(pageNavigator, webDriver);
        assertEquals("PetStore", webDriver.getTitle());
    }

    @I18n.ZH_CN.当("^我用名称\"(.*)\"搜索宠物时$")
    public void searchPets(String keyword) {
        webDriver.type("keyword", keyword);
        webDriver.click("search");
        webDriver.waitForPageToLoad("30000");
    }
}
