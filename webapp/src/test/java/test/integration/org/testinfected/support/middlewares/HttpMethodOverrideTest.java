package test.integration.org.testinfected.support.middlewares;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.testinfected.support.Application;
import org.testinfected.support.Server;
import org.testinfected.support.middlewares.HttpMethodOverride;
import org.testinfected.support.MiddlewareStack;
import test.support.org.testinfected.support.web.HttpRequest;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static test.support.org.testinfected.support.web.HttpRequest.aRequest;

@RunWith(JMock.class)
public class HttpMethodOverrideTest {
    Mockery context = new JUnit4Mockery();
    Application runner = context.mock(Application.class);

    HttpMethodOverride methodOverride = new HttpMethodOverride();

    Server server = new Server(9999);
    HttpRequest request = aRequest().to(server);

    @Before public void
    startServer() throws IOException {
        server.run(new MiddlewareStack() {{
            use(methodOverride);
            run(runner);
        }});
    }

    @After public void
    stopServer() throws Exception {
        server.shutdown();
    }

    @Test public void
    doesNotAffectGetMethods() throws Exception {
        context.checking(new Expectations() {{
            oneOf(runner).handle(with(aRequestWithMethod("GET")), with(any(Response.class)));
        }});
        request.withParameter("_method", "delete").get("/");
    }

    @Test public void
    doesNotAffectPostMethodsWhenOverrideParameterIsNotSet() throws Exception {
        context.checking(new Expectations() {{
            oneOf(runner).handle(with(aRequestWithMethod("POST")), with(any(Response.class)));
        }});
        request.post("/item");
    }

    @Test public void
    changesPostMethodsAccordingToOverrideParameter() throws Exception {
        context.checking(new Expectations() {{
            oneOf(runner).handle(with(aRequestWithMethod(equalToIgnoringCase("DELETE"))), with(any(Response.class)));
        }});

        HttpMethodOverride.METHOD_OVERRIDE_PARAMETER = "override";
        request.withParameter("override", "delete").post("/item");
    }

    @Test public void
    doesNotChangeMethodIfOverriddenMethodIsUnknown() throws Exception {
        context.checking(new Expectations() {{
            oneOf(runner).handle(with(aRequestWithMethod(equalToIgnoringCase("POST"))), with(any(Response.class)));
        }});
        request.withParameter("_method", "foo").post("/item");
    }

    private Matcher<Request> aRequestWithMethod(String method) {
        return aRequestWithMethod(equalTo(method));
    }

    private Matcher<Request> aRequestWithMethod(Matcher<? super String> methodMatcher) {
        return new FeatureMatcher<Request, String>(methodMatcher, "a request with method", "method") {
            protected String featureValueOf(Request request) {
                return request.getMethod();
            }
        };
    }
}