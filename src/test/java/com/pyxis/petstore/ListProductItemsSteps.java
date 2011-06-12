package com.pyxis.petstore;

import com.pyxis.petstore.utils.PageNavigator;
import com.pyxis.petstore.utils.WebDriver;
import cuke4duke.Table;
import cuke4duke.annotation.I18n.ZH_CN.假如;
import cuke4duke.annotation.I18n.ZH_CN.那么;

public class ListProductItemsSteps {

    private WebDriver web;
    private PageNavigator pageNavigator;

    public ListProductItemsSteps(WebDriver web, PageNavigator pageNavigator) {
        this.web = web;
        this.pageNavigator = pageNavigator;
    }

    @假如("^我在宠物(.+)的存货页面$")
    public void onTheProductItemsPage(String number) {
        pageNavigator.openProductItemsPage(number);
    }


    @那么("^我应当看到(.+)个现存货物:$")
    public void shouldSeeItems(String number, Table results) {
        pageNavigator.getCurrentPage().isTextPresent(number + " items are available for purchase");
        pageNavigator.getCurrentPage().assertTableContentsPresent(results);
    }
}
