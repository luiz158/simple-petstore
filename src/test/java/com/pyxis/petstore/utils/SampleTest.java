package com.pyxis.petstore.utils;

import com.thoughtworks.selenium.DefaultSelenium;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class SampleTest {
    @Test
    public void testOne() {
        DefaultSelenium selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://localhost:8080/");
        selenium.start();
        selenium.open("/petstore/");
        selenium.type("keyword", "");
        selenium.click("search");
        selenium.waitForPageToLoad("30000");
        System.out.println(selenium.getBodyText());
        selenium.close();
        selenium.stop();
    }
}
