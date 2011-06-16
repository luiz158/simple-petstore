package com.pyxis.petstore.utils;

import cuke4duke.annotation.I18n;

public class PageNavigator {
    private WebDriver webDriver;

    public PageNavigator(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @I18n.ZH_CN.假如("^我在宠物(.+)的存货页面$")
    public void openProductItemsPage(String number) {
        webDriver.open("/petstore/products/" + number + "/items");
    }

    @I18n.ZH_CN.假如("^我在首页$")
    public void openHomePage() {
        webDriver.open("/petstore/");
    }


}
