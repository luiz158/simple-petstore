package test.unit.org.testinfected.petstore.controllers;

import com.vtence.molecule.Session;
import com.vtence.molecule.support.MockRequest;
import com.vtence.molecule.support.MockResponse;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.testinfected.petstore.controllers.ShowCart;
import org.testinfected.petstore.order.Cart;
import test.support.org.testinfected.petstore.web.MockView;

import static test.support.org.testinfected.petstore.builders.CartBuilder.aCart;
import static test.support.org.testinfected.petstore.builders.ItemBuilder.anItem;

public class ShowCartTest {

    MockView<Cart> view = new MockView<Cart>();

    MockView<Void> viewVoid = new MockView<Void>();
    ShowCart showCart = new ShowCart(view, viewVoid);

    MockRequest request = new MockRequest();
    MockResponse response;

    @Before
    public void
    createSession() {
        Session.set(request, new Session());
        response = new MockResponse();
    }

    @Test public void
    rendersCartContent() throws Exception {

        final Cart cart = aCart().containing(anItem()).build();
        storeInSession(cart);

        showCart.handle(request, response);
        view.assertRenderedTo(response);
        view.assertRenderedWith(sameCartAs(cart));
    }

    @Test public void
    whenCartEmptyGotoHomePage() throws Exception {

        final Cart cart = aCart().build();
        storeInSession(cart);

        showCart.handle(request, response);
        viewVoid.assertRenderedTo(response);
    }

    private Matcher<Object> sameCartAs(Cart cart) {
        return Matchers.<Object>sameInstance(cart);
    }


    private void storeInSession(Cart cart) {
        Session.get(request).put(Cart.class, cart);
    }
}