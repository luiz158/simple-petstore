package com.pyxis.petstore;

import com.pyxis.petstore.pages.ProductsPage;
import com.pyxis.petstore.utils.PageNavigator;
import com.pyxis.petstore.utils.WebDriver;
import cuke4duke.Table;
import cuke4duke.annotation.I18n.ZH_CN.假如;
import cuke4duke.annotation.I18n.ZH_CN.当;
import cuke4duke.annotation.I18n.ZH_CN.那么;

import static junit.framework.Assert.assertTrue;

public class SearchProductsSteps {
    private PageNavigator pageNavigator;
    private WebDriver web;

    public SearchProductsSteps(WebDriver web, PageNavigator pageNavigator) {
        this.web = web;
        this.pageNavigator = pageNavigator;
    }

    @当("^我用名称\"(.*)\"搜索宠物时$")
    public void searchPetsByName(String keyword) {
        pageNavigator.getCurrentPage().searchPetsByName(keyword);
    }

    @那么("^我应该看到(.+)个宠物结果:$")
    public void shouldSeeProducts(String number, Table results) {
        ProductsPage productsPage = (ProductsPage) pageNavigator.getCurrentPage();
        productsPage.assertTableContentsPresent(results);
        assertTrue(productsPage.isTextPresent("Found " + number + " result"));
    }


}
