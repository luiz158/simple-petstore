package com.pyxis.petstore.pages;

import com.pyxis.petstore.utils.PageNavigator;
import com.pyxis.petstore.utils.WebDriver;

import static junit.framework.Assert.assertEquals;

public class ProductItemsPage extends MotherPage {
    public ProductItemsPage(PageNavigator pageNavigator, WebDriver webDriver) {
        super(pageNavigator, webDriver);
        assertEquals("PetStore - Items", webDriver.getTitle());
    }
}
