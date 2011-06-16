package com.pyxis.petstore.pages;

import com.pyxis.petstore.utils.PageNavigator;
import com.pyxis.petstore.utils.WebDriver;
import cuke4duke.Table;

import java.util.List;

import static junit.framework.Assert.assertTrue;

public class SuperPage {
    protected PageNavigator pageNavigator;
    protected WebDriver webDriver;

    public SuperPage(PageNavigator pageNavigator, WebDriver webDriver) {
        this.pageNavigator = pageNavigator;
        this.webDriver = webDriver;
    }

    public void assertTableContentsPresent(Table results) {
        for (List<String> row : results.rows()) {
            for (String cell : row) {
                assertTrue(webDriver.isTextPresent(cell));
            }
        }
    }
}
