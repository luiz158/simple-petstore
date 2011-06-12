package com.pyxis.petstore.pages;

import com.pyxis.petstore.utils.PageNavigator;
import com.pyxis.petstore.utils.WebDriver;

import static junit.framework.Assert.assertEquals;

public class HomePage extends MotherPage {
    public HomePage(PageNavigator pageNavigator, WebDriver webDriver) {
        super(pageNavigator, webDriver);
        assertEquals("PetStore", webDriver.getTitle());
    }
}
