package com.pyxis.petstore;

import com.thoughtworks.selenium.DefaultSelenium;
import cuke4duke.Table;
import cuke4duke.annotation.After;
import cuke4duke.annotation.Before;
import cuke4duke.annotation.I18n.ZH_CN.假如;
import cuke4duke.annotation.I18n.ZH_CN.当;
import cuke4duke.annotation.I18n.ZH_CN.而且;
import cuke4duke.annotation.I18n.ZH_CN.那么;

import java.util.List;

import static junit.framework.Assert.assertTrue;

public class SearchProductsSteps {

    private DefaultSelenium selenium;

    public SearchProductsSteps() {
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

    @假如("^我在页面\"(.+)\"$")
    public void onPage(String pageUrl) {
        selenium.open("/petstore" + pageUrl);
    }

    @当("^我在搜索框中输入\"(.*)\"$")
    public void inputInSearchBox(String keyword) {
        selenium.type("keyword", keyword);
    }

    @而且("^点击(.+)按钮$")
    public void pressButton(String buttonId) {
        selenium.click(buttonId);
        selenium.waitForPageToLoad("30000");
    }

    @那么("^我应该看到(.+)个宠物结果:$")
    public void shouldSeeProducts(String number, Table results) {
        assertTrue(selenium.isTextPresent("Found " + number + " result"));
        for (List<String> row : results.rows()) {
            assertTrue(selenium.isTextPresent(row.get(0)));
            assertTrue(selenium.isTextPresent(row.get(1)));
        }
    }


}
