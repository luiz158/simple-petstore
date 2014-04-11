package test.unit.org.testinfected.petstore.controllers;


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
import test.support.org.testinfected.molecule.unit.MockRequest;
import test.support.org.testinfected.molecule.unit.MockResponse;
import test.support.org.testinfected.petstore.builders.Builder;
import test.support.org.testinfected.petstore.web.MockPage;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static test.support.org.testinfected.petstore.builders.Builders.build;
import static test.support.org.testinfected.petstore.builders.ProductBuilder.aProduct;

public class ListEmptyProductsTest {
    @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

    ProductCatalog productCatalog = context.mock(ProductCatalog.class);
    AttachmentStorage photoLibrary = context.mock(AttachmentStorage.class);
    MockPage productsPage = new MockPage();
    ListProducts listProducts = new ListProducts(productCatalog, photoLibrary, productsPage);

    MockRequest request = new MockRequest();
    MockResponse response = new MockResponse();

    String emptyKeyword = "";

    @Before public void
    addSearchKeywordToRequest() {
        request.addParameter("keyword", emptyKeyword);
    }

    @SuppressWarnings("unchecked")
    @Test public void redirectToHomeOnEmptySearch() throws Exception {
        listProducts.handle(request, response);
        response.assertRedirectedTo("/");
    }

    
}

