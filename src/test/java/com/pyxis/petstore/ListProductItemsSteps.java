package com.pyxis.petstore;

import com.thoughtworks.selenium.DefaultSelenium;
import cuke4duke.Table;
import cuke4duke.annotation.After;
import cuke4duke.annotation.Before;
import cuke4duke.annotation.I18n.ZH_CN.*;

import java.util.List;

import static junit.framework.Assert.assertTrue;

public class ListProductItemsSteps {
    DefaultSelenium selenium;

    public ListProductItemsSteps() {
        selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://localhost:8080/");
    }

    @Before
    public void before() {
        selenium.start();
    }

    @After
    public void after() {
        selenium.close();
        selenium.stop();
    }

    @假如("^我在页面\"(.+)\"上$")
    public void onPage(String pageUrl) {
        selenium.open("/petstore" + pageUrl);
    }

    @那么("^我应当看到(.+)个现存货物:$")
    public void shouldSeeItems(String number, Table results) {
        assertTrue(selenium.isTextPresent(number + " items are available for purchase"));
        for (List<String> row : results.rows()) {
            for (String cell : row) {
                assertTrue(selenium.isTextPresent(cell));
            }
        }
    }
}
