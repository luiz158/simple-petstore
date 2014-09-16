package test.unit.org.testinfected.petstore.controllers;

import com.vtence.molecule.support.MockRequest;
import com.vtence.molecule.support.MockResponse;
import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testinfected.petstore.controllers.ListProducts;
import org.testinfected.petstore.product.AttachmentStorage;
import org.testinfected.petstore.product.Product;
import org.testinfected.petstore.product.ProductCatalog;
import org.testinfected.petstore.views.Products;
import test.support.org.testinfected.petstore.builders.Builder;
import test.support.org.testinfected.petstore.web.MockView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static test.support.org.testinfected.petstore.builders.Builders.build;
import static test.support.org.testinfected.petstore.builders.ProductBuilder.aProduct;

public class ListProductsForEmptyRequestTest {
    @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

    ProductCatalog productCatalog = context.mock(ProductCatalog.class);
    AttachmentStorage photoLibrary = context.mock(AttachmentStorage.class);
    MockView<Products> view = new MockView<Products>();
    ListProducts listProducts = new ListProducts(productCatalog, photoLibrary, view);

    MockRequest request = new MockRequest();
    MockResponse response = new MockResponse();

    @SuppressWarnings("unchecked")
    @Test public void
    rendersNothingWithEmptyKeyword() throws Exception {
        MockRequest emptyRequest = new MockRequest();
        emptyRequest.addParameter("keyword", "");

        listProducts.handle(emptyRequest, response);

        response.assertRedirectedTo("/");
    }


    private Matcher<Object> photosIn(AttachmentStorage photos) {
        return hasProperty("photos", equalTo(photos));
    }

    private Matcher<Object> searchKeyword(String keyword) {
        return hasProperty("keyword", equalTo(keyword));
    }

    private Matcher<Object> productsFound(List<Product> results) {
        return hasProperty("each", equalTo(results));
    }


}