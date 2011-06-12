package com.pyxis.petstore.utils;

import com.thoughtworks.selenium.DefaultSelenium;
import cuke4duke.annotation.After;
import cuke4duke.annotation.Before;

public class WebDriver {
    private DefaultSelenium selenium;

    public WebDriver() {
        selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://localhost:8080/");
    }

    @Before
    public void before() {
        selenium.start();
    }

    @After
    public void after() {
        selenium.stop();
    }

    public void open(String url) {
        selenium.open(url);
    }

    public void type(String locator, String value) {
        selenium.type(locator, value);
    }

    public void click(String locator) {
        selenium.click(locator);
    }

    public void waitForPageToLoad(String timeout) {
        selenium.waitForPageToLoad(timeout);
    }

    public boolean isTextPresent(String pattern) {
        return selenium.isTextPresent(pattern);
    }

    public String getTitle() {
        return selenium.getTitle();
    }

}
