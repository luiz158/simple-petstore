package com.pyxis.petstore;

import cuke4duke.Table;
import cuke4duke.annotation.I18n.ZH_CN.那么;

import java.util.List;

import static junit.framework.Assert.assertTrue;

public class ListProductItemsSteps {

    private WebDriver web;

    public ListProductItemsSteps(WebDriver web) {
        this.web = web;
    }

    @那么("^我应当看到(.+)个现存货物:$")
    public void shouldSeeItems(String number, Table results) {
        assertTrue(web.isTextPresent(number + " items are available for purchase"));
        for (List<String> row : results.rows()) {
            for (String cell : row) {
                assertTrue(web.isTextPresent(cell));
            }
        }
    }
}
