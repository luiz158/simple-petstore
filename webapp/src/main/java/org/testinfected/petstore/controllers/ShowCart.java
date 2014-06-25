package org.testinfected.petstore.controllers;

import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import org.testinfected.petstore.View;
import org.testinfected.petstore.order.Cart;
import org.testinfected.petstore.util.SessionScope;

public class ShowCart implements Application {
    private final View<Cart> view;
    private final View<Void> viewEmptyCart;

    public ShowCart(View<Cart> view, View<Void> viewEmptyCart) {
        this.view = view;
        this.viewEmptyCart = viewEmptyCart;
    }

    public void handle(Request request, Response response) throws Exception {
        if (SessionScope.cart(request).empty()) {
            viewEmptyCart.render(response, null);
        } else {
            view.render(response, SessionScope.cart(request));
        }
    }
}
