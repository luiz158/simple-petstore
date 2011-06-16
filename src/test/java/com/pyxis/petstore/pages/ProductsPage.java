package com.pyxis.petstore.pages;

import com.pyxis.petstore.utils.PageNavigator;
import com.pyxis.petstore.utils.WebDriver;
import cuke4duke.Table;
import cuke4duke.annotation.I18n;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class ProductsPage extends SuperPage {
    public ProductsPage(PageNavigator pageNavigator, WebDriver webDriver) {
        super(pageNavigator, webDriver);
        assertEquals("PetStore - Products", webDriver.getTitle());
    }

    @I18n.ZH_CN.那么("^我应该看到(.+)个宠物结果:$")
    public void shouldSeeProducts(String number, Table results) {
        assertTrue(webDriver.isTextPresent("Found " + number + " result"));
        assertTableContentsPresent(results);
    }

}
