package com.pyxis.petstore.pages;

import com.pyxis.petstore.utils.PageNavigator;
import com.pyxis.petstore.utils.WebDriver;

import static junit.framework.Assert.assertEquals;

public class ProductsPage extends MotherPage {
    public ProductsPage(PageNavigator pageNavigator, WebDriver webDriver) {
        super(pageNavigator, webDriver);
        assertEquals("PetStore - Products", webDriver.getTitle());
    }

}
