package test.unit.org.testinfected.petstore.controllers;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.testinfected.petstore.controllers.ListProducts;
import org.testinfected.petstore.controllers.ShowCart;
import org.testinfected.petstore.order.Cart;
import org.testinfected.petstore.product.Product;
import org.testinfected.petstore.views.Products;

import test.support.org.testinfected.petstore.web.MockView;

import com.vtence.molecule.http.HttpStatus;
import com.vtence.molecule.support.MockRequest;
import com.vtence.molecule.support.MockResponse;

public class ListProductsEmptyKeywordTest {

	private final String keyword ="";
	MockRequest request = new MockRequest();
	MockResponse response = new MockResponse();
	
	@Before public void
    addSearchKeywordToRequest() {
        request.addParameter("keyword", keyword);
    }
	
	@Test
	public void searchWithEmptyKeyword_RenderHomePage() throws Exception {
		//Given
		MockView<Products> productsView = new MockView<Products>();
		ListProducts listProducts = new ListProducts(null,null,productsView);
		
		//When
		listProducts.handle(request, response);
		
		
		//Then
		response.assertRedirectedTo("/");
		response.assertStatus(HttpStatus.SEE_OTHER);	
	}
	
	private Matcher<Object> sameCartAs(Cart cart) {
	        return Matchers.<Object>sameInstance(cart);
	}
}
