package test.system.org.testinfected.petstore.features;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.support.org.testinfected.petstore.web.ApplicationDriver;
import test.support.org.testinfected.petstore.web.TestEnvironment;

import java.io.IOException;

public class About {

    private String aText;

    ApplicationDriver application = new ApplicationDriver(TestEnvironment.load());

    @Before
    public void
    startApplication() throws Exception {
        application.start();
    }

    @After
    public void
    stopApplication() throws Exception {
        application.stop();
    }

    @Test
    public void verifyAboutPage() throws IOException {

        application.showsImageInAboutPage();

    }
}
