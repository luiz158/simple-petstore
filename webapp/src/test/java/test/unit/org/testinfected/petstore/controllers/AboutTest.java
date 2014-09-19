package test.unit.org.testinfected.petstore.controllers;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.testinfected.petstore.Members;
import org.testinfected.petstore.controllers.About;

import test.support.org.testinfected.petstore.web.MockView;

import com.vtence.molecule.support.MockRequest;
import com.vtence.molecule.support.MockResponse;


public class AboutTest {
    
	MockRequest request = new MockRequest();
    MockResponse response = new MockResponse();
    MockView<Members> aboutView = new MockView<Members>();
    Members members = new Members();
    
	@Test public void
	renderAboutPageWithTeamMembers() throws Exception
	{
		About about = new About(members, aboutView);
		about.handle(request, response);
		
		aboutView.assertRenderedTo(response);
		aboutView.assertRenderedWith(Matchers.sameInstance(members));
	}

}
