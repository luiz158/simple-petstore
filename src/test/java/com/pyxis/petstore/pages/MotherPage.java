package com.pyxis.petstore.pages;

import com.pyxis.petstore.utils.PageNavigator;
import com.pyxis.petstore.utils.WebDriver;
import cuke4duke.Table;

import java.util.List;

import static junit.framework.Assert.assertTrue;

public class MotherPage {
    private PageNavigator pageNavigator;
    protected WebDriver webDriver;

    public MotherPage(PageNavigator pageNavigator, WebDriver webDriver) {
        this.pageNavigator = pageNavigator;
        this.webDriver = webDriver;
    }

    public void searchPetsByName(String keyword) {
        webDriver.type("keyword", keyword);
        webDriver.click("search");
        webDriver.waitForPageToLoad("30000");
        pageNavigator.setCurrentPage(new ProductsPage(pageNavigator, webDriver));
    }

    public boolean isTextPresent(String text) {
        return webDriver.isTextPresent(text);
    }

    public void assertTableContentsPresent(Table results) {
        for (List<String> row : results.rows()) {
            for (String cell : row) {
                assertTrue(webDriver.isTextPresent(cell));
            }
        }
    }
}
