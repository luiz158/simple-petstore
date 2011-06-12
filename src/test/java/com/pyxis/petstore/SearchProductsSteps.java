package com.pyxis.petstore;

import cuke4duke.Table;
import cuke4duke.annotation.I18n.ZH_CN.假如;
import cuke4duke.annotation.I18n.ZH_CN.当;
import cuke4duke.annotation.I18n.ZH_CN.而且;
import cuke4duke.annotation.I18n.ZH_CN.那么;

import java.util.List;

import static junit.framework.Assert.assertTrue;

public class SearchProductsSteps {

    private WebDriver web;

    public SearchProductsSteps(WebDriver web) {
        this.web = web;
    }

    @假如("^我在页面\"(.+)\"$")
    public void onPage(String pageUrl) {
        web.open("/petstore" + pageUrl);
    }

    @当("^我在搜索框中输入\"(.*)\"$")
    public void inputInSearchBox(String keyword) {
        web.type("keyword", keyword);
    }

    @而且("^点击(.+)按钮$")
    public void pressButton(String buttonId) {
        web.click(buttonId);
        web.waitForPageToLoad("30000");
    }

    @那么("^我应该看到(.+)个宠物结果:$")
    public void shouldSeeProducts(String number, Table results) {
        assertTrue(web.isTextPresent("Found " + number + " result"));
        for (List<String> row : results.rows()) {
            assertTrue(web.isTextPresent(row.get(0)));
            assertTrue(web.isTextPresent(row.get(1)));
        }
    }


}
