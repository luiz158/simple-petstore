package test.support.org.testinfected.petstore.web.page;

import com.objogate.wl.web.AsyncWebDriver;

import static org.openqa.selenium.By.id;

/**
 * Created by formation on 19/09/14.
 */
public class AboutPage extends Page {
    public AboutPage(AsyncWebDriver browser) { super(browser);
    }

    public void showImageAbout() {
        browser.element(id("simplets")).assertExists();
    }

    // browser.element(cellShowingNameOf(itemNumber)).assertText(containsString(itemDescription))
}
