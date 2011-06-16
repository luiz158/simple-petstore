package com.pyxis.petstore.pages;

import com.pyxis.petstore.utils.PageNavigator;
import com.pyxis.petstore.utils.WebDriver;
import cuke4duke.Table;
import cuke4duke.annotation.I18n;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class ProductItemsPage extends SuperPage {
    public ProductItemsPage(PageNavigator pageNavigator, WebDriver webDriver) {
        super(pageNavigator, webDriver);
        assertEquals("PetStore - Items", webDriver.getTitle());
    }

    @I18n.ZH_CN.那么("^我应当看到(.+)个现存货物:$")
    public void shouldSeeItems(String number, Table results) {
        assertTrue(webDriver.isTextPresent(number + " items are available for purchase"));
        assertTableContentsPresent(results);
    }

}
