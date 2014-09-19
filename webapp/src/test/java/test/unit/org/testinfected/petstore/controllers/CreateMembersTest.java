package test.unit.org.testinfected.petstore.controllers;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testinfected.petstore.Members;
import org.testinfected.petstore.controllers.CreateMembers;

import com.vtence.molecule.http.HttpStatus;
import com.vtence.molecule.support.MockRequest;
import com.vtence.molecule.support.MockResponse;

public class CreateMembersTest {
    @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

    Members members = new Members();
    CreateMembers createMembers = new CreateMembers(members);

    MockRequest request = new MockRequest();
    MockResponse response = new MockResponse();

    @Before public void
    addMembersToRequest() {
        request.addParameter("members", "toto");
    }

    @Test public void
    addMembersRequestAndRespondsWithCreated() throws Exception {
    	createMembers.handle(request, response);
    	MatcherAssert.assertThat("members", members.getMembers(), Matchers.equalTo("toto"));
        response.assertStatus(HttpStatus.CREATED);
    }
}