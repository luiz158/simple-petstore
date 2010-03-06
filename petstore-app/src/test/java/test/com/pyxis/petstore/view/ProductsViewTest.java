package test.com.pyxis.petstore.view;

import static com.pyxis.matchers.dom.DomMatchers.withAttribute;
import static com.pyxis.matchers.dom.DomMatchers.hasChild;
import static com.pyxis.matchers.dom.DomMatchers.hasNoSelector;
import static com.pyxis.matchers.dom.DomMatchers.hasSelector;
import static com.pyxis.matchers.dom.DomMatchers.hasUniqueSelector;
import static com.pyxis.matchers.dom.DomMatchers.withText;
import static com.threelevers.css.DocumentBuilder.dom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.nullValue;
import static test.support.com.pyxis.petstore.builders.Entities.entities;
import static test.support.com.pyxis.petstore.builders.ProductBuilder.aProduct;
import static test.support.com.pyxis.petstore.velocity.VelocityRendering.render;

import java.util.Map;

import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ui.ModelMap;
import org.w3c.dom.Element;

import test.support.com.pyxis.petstore.builders.EntityBuilder;

import com.pyxis.petstore.domain.AttachmentStorage;
import com.pyxis.petstore.domain.Product;

@RunWith(JMock.class)
public class ProductsViewTest {
    private static final String PRODUCTS_VIEW = "products";
    private static final Object DEFAULT_PHOTO_URL = "/url/of/missing.png";

    Mockery context = new JUnit4Mockery();
    AttachmentStorage attachmentStorage = context.mock(AttachmentStorage.class);
    String productsPage;

    @Before public void
    setUpDefaultPhoto() {
        context.checking(new Expectations() {{
            allowing(attachmentStorage).getAttachmentUrl(with(aProductWithoutPhoto())); will(returnValue(DEFAULT_PHOTO_URL));
        }});
    }

    @Test public void
    displaysNumberOfProductsFound() {
        productsPage = renderProductsPageUsing(aModelWith(aProduct(), aProduct()));
        assertThat(dom(productsPage), hasUniqueSelector("#match-count", withText("2")));
    }

    @Test public void
    displaysColumnHeadingsOfProductTable() {
        productsPage = renderProductsPageUsing(aModelWith(aProduct()));
        assertThat(dom(productsPage),
                hasSelector("#products th",
                        inOrder(withEmptyText(),
                                withText("Name"),
                                withText("Description"))));
    }

    @Test public void
    displaysProductDetailsInColumns() throws Exception {
        Map<String, ?> model = aModelWith(aProduct().withName("Labrador").describedAs("Friendly").withPhoto("labrador.png"));
        final String photoUrl = "/path/to/attachment/labrador.png";
        context.checking(new Expectations() {{
            allowing(attachmentStorage).getAttachmentUrl(with(aProductWithPhoto("labrador.png"))); will(returnValue(photoUrl));
        }});

        productsPage = renderProductsPageUsing(model);
        assertThat(dom(productsPage),
                hasSelector("#products td",
                        inOrder(image(photoUrl),
                                productName("Labrador"),
                                description("Friendly"))));
    }

    @Test public void
    handlesProductWithNoDescriptionCorrectly() {
        productsPage = renderProductsPageUsing(aModelWith(aProduct().withoutADescription()));
        assertThat(dom(productsPage),
                hasSelector("#products td:nth-child(3)",
                        contains(anEmptyDescription())));
    }

    @Test public void
    displaysNoProductsTableIfNoProductIsFound() {
        productsPage = renderProductsPageUsing(anEmptyModel());
        assertThat(dom(productsPage), hasUniqueSelector("#no-match"));
        assertThat(dom(productsPage), hasNoSelector("#products"));
    }
    
    @Test
    public void displaysLinksOnProductNamesToBrowseItemsOfThatProduct() {
    	productsPage = renderProductsPageUsing(aModelWith(aProduct().withName("Labrador").withNumber("123")));
    	assertThat(dom(productsPage),
    			hasUniqueSelector("td a", 
    					withAttribute("href", containsString("items?product_number=123"))));
    }

    private Matcher<Product> aProductWithoutPhoto() {
        return aProductWithPhoto(nullValue());
    }

    private Matcher<Product> aProductWithPhoto(String photoName) {
        return aProductWithPhoto(equalTo(photoName));
    }

    private Matcher<Product> aProductWithPhoto(Matcher<? super String> photoMatcher) {
        return hasProperty("photoFileName", photoMatcher);
    }

    private Map<String, ?> anEmptyModel() {
        return aModelWith();
    }

    private Map<String, ?> aModelWith(EntityBuilder<?>... entityBuilders) {
        ModelMap model = new ModelMap();
        model.addAttribute("attachments", attachmentStorage);
        model.addAttribute(entities(entityBuilders));
        return model;
    }

    private String renderProductsPageUsing(Map<String, ?> model) {
        return render(PRODUCTS_VIEW).using(model);
    }

    private Matcher<Element> withEmptyText() {
        return withText("");
    }

    private Matcher<Element> anEmptyDescription() {
        return description("");
    }

    private Matcher<Element> description(String description) {
        return withText(description);
    }

    private Matcher<Element> image(String imageUrl) {
        return hasChild(withAttribute("src", imageUrl));
    }

    private Matcher<Element> productName(String name) {
        return withText(name);
    }

    private Matcher<Iterable<Element>> inOrder(Matcher<Element>... elementMatchers) {
        return contains(elementMatchers);
    }
}