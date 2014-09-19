package test.system.org.testinfected.petstore.features;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.support.org.testinfected.petstore.web.ApplicationDriver;
import test.support.org.testinfected.petstore.web.TestEnvironment;

public class AboutUsFeature {

    ApplicationDriver application = new ApplicationDriver(TestEnvironment.load());

    @Before public void
    startApplication() throws Exception {
        application.start();
    }

    @After public void
    stopApplication() throws Exception {
        application.stop();
    }

    @Test public void
    dicoveringTeamMembers() throws IOException {
    	String member = "Vincent Tence";
    	application.injectMembers(member);
		application.showsTeamMembers(member);
    }
}
