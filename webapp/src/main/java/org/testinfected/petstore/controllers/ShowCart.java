package org.testinfected.petstore.controllers;

import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import org.testinfected.petstore.View;
import org.testinfected.petstore.order.Cart;
import org.testinfected.petstore.util.SessionScope;

public class ShowCart implements Application {
    private final View<Cart> view;

    public ShowCart(View<Cart> view) {
        this.view = view;
    }

    public void handle(Request request, Response response) throws Exception {

        Cart cart = SessionScope.cart(request);

        if (!cart.empty()) {
            view.render(response, cart);
        } else {
            response.redirectTo("/");
        }
    }
}