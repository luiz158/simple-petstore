package test.unit.org.testinfected.petstore.helpers;

import com.vtence.molecule.support.MockRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testinfected.petstore.billing.CreditCardDetails;
import org.testinfected.petstore.billing.CreditCardType;
import org.testinfected.petstore.helpers.PaymentForm;

/**
 * Created by formation on 17/09/14.
 */
public class PaymentFormTest {
    private CreditCardType cardType = CreditCardType.visa;

    private  String cardNumber = "4111 1111 1111 1111";

    private  String expiracyDate = "12/12";

    private String firstName = "John";

    private String lastName = "Doe";

    private String email = "john.doe@gmail.com";

    private String zipCode = "77000";

    private String country = "Brasil";



    private MockRequest request;
    @Before
    public void populateRequest(){
        request = new MockRequest();
        request.addParameter("card-type", cardType.name());
        request.addParameter("card-number", cardNumber);
        request.addParameter("expiry-date", expiracyDate);
        request.addParameter("first-name", firstName);
        request.addParameter("last-name", lastName);
        request.addParameter("email", email);
        request.addParameter("zip-code", zipCode);
        request.addParameter("country", country);


    }

    @Test
    public void readPaymentFormFromRequest(){
        PaymentForm form = PaymentForm.parse(request);
        Assert.assertNotNull(form);
        CreditCardDetails details = form.paymentDetails();
        Assert.assertNotNull(details);
        Assert.assertEquals("Credit card type differs", cardType, details.getCardType());
        Assert.assertEquals("Credit card number differs", cardNumber, details.getCardNumber());
        Assert.assertEquals("Credit card expiracy differs", expiracyDate, details.getCardExpiryDate());
        Assert.assertEquals("First name differs", firstName, details.getFirstName());
        Assert.assertEquals("Last name differs", lastName, details.getLastName());
        Assert.assertEquals("Email differs", email, details.getEmail());
        Assert.assertEquals("Zip Code differs", zipCode, details.getZipCode());
        Assert.assertEquals("Country differs", country, details.getCountry());
    }
}
