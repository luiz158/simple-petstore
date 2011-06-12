package com.pyxis.petstore;

import com.pyxis.petstore.utils.PageNavigator;
import com.pyxis.petstore.utils.WebDriver;
import cuke4duke.annotation.I18n;

public class PageNavigationSteps {
    private WebDriver web;
    private PageNavigator pageNavigator;

    public PageNavigationSteps(WebDriver web, PageNavigator pageNavigator) {
        this.web = web;
        this.pageNavigator = pageNavigator;
    }

    @I18n.ZH_CN.假如("^我在宠物(.+)的存货页面$")
    public void onTheProductItemsPage(String number) {
        pageNavigator.openProductItemsPage(number);
    }

    @I18n.ZH_CN.假如("^我在首页$")
    public void onTheHomePage() {
        pageNavigator.openHomePage();
    }

}
