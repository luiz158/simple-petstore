package com.pyxis.petstore.utils;

import com.pyxis.petstore.pages.HomePage;
import com.pyxis.petstore.pages.MotherPage;
import com.pyxis.petstore.pages.ProductItemsPage;

public class PageNavigator {
    private WebDriver web;
    private MotherPage currentPage;

    public PageNavigator(WebDriver web) {
        this.web = web;
    }

    public void openProductItemsPage(String number) {
        web.open("/petstore/products/" + number + "/items");
        currentPage = new ProductItemsPage(this, web);
    }

    public void openHomePage() {
        web.open("/petstore/");
        currentPage = new HomePage(this, web);
    }

    public MotherPage getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(MotherPage page) {
        this.currentPage = page;
    }


}
