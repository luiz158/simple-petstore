package test.unit.org.testinfected.petstore.views;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testinfected.hamcrest.dom.DomMatchers.hasSelector;
import static org.testinfected.hamcrest.dom.DomMatchers.hasText;
import static org.testinfected.hamcrest.dom.DomMatchers.matches;
import static test.support.org.testinfected.petstore.web.OfflineRenderer.render;

import org.junit.Test;
import org.testinfected.petstore.Members;
import org.w3c.dom.Element;

import test.support.org.testinfected.petstore.web.OfflineRenderer;
import test.support.org.testinfected.petstore.web.WebRoot;

public class AboutPageTest {

	String ABOUT_TEMPLATE = "about";
	Element aboutPage;

	@Test public void
    displaysMembers() {
    	Members members = new Members();
    	members.addMember("Denis Boisset");
    	
		aboutPage = renderAboutPage().with(members).asDom();
        assertThat("about page", aboutPage,
                hasSelector(".member",
                        matches(hasText("Denis Boisset"))));
    }

	private OfflineRenderer renderAboutPage() {
		return render(ABOUT_TEMPLATE).from(WebRoot.pages());
	}

}
