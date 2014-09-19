package test.support.org.testinfected.petstore.web.page;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;
import org.hamcrest.Matchers;

import com.objogate.wl.web.AsyncWebDriver;

public class AboutPage extends Page {

	public AboutPage(AsyncWebDriver browser) {
		 super(browser);
	}

	public void showsTeamMembers(String... members) {
		browser.element(cssSelector(".member:first-child")).assertText(Matchers.equalTo(members[0]));
	}

    public void showImageAbout() {
        browser.element(id("simplets")).assertExists();
    }
}
