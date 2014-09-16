package test.unit.org.testinfected.petstore.billing;

import org.junit.Assert;
import org.junit.Test;
import org.testinfected.petstore.billing.Address;
import org.testinfected.petstore.billing.CreditCardDetails;
import org.testinfected.petstore.billing.CreditCardType;

import static org.hamcrest.CoreMatchers.describedAs;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static test.support.org.testinfected.petstore.builders.AddressBuilder.anAddress;
import static test.support.org.testinfected.petstore.builders.CreditCardBuilder.aVisa;
import static test.support.org.testinfected.petstore.builders.CreditCardBuilder.validCreditCardDetails;
import static test.support.org.testinfected.petstore.matchers.SerializedForm.serializedForm;
import static test.support.org.testinfected.petstore.matchers.ValidationMatchers.on;
import static test.support.org.testinfected.petstore.matchers.ValidationMatchers.succeeds;
import static test.support.org.testinfected.petstore.matchers.ValidationMatchers.validationOf;
import static test.support.org.testinfected.petstore.matchers.ValidationMatchers.violates;
import static test.support.org.testinfected.petstore.matchers.ValidationMatchers.withMessage;

public class CreditCardDetailsTest {

    String MISSING = null;
    String EMPTY = "";
    String INCORRECT = "1234567890123456";

    @Test public void
    areSerializable() {
        assertThat("card details", validCreditCardDetails().build(), describedAs("are serializable", serializedForm(notNullValue())));
    }

    @Test public void
    areInvalidWithAnEmptyOrIncorrectCardNumber() {
        assertThat("empty number", validationOf(aVisa().withNumber(EMPTY)), violates(on("cardNumber"), withMessage("empty")));
        assertThat("incorrect number", validationOf(aVisa().withNumber(INCORRECT)), violates(on("cardNumber"), withMessage("incorrect")));
    }

    @Test public void
    areInvalidWithoutACardExpiryDate() {
        assertThat("missing expiry date", validationOf(aVisa().withExpiryDate(MISSING)), violates(on("cardExpiryDate"), withMessage("missing")));
    }

    @Test public void
    areInvalidWithAnInvalidAddress() {
        assertThat("missing first name", validationOf(aVisa().billedTo(anAddress().withFirstName(MISSING))), violates(on("billingAddress.firstName"), withMessage("missing")));
        assertThat("missing last name", validationOf(aVisa().billedTo(anAddress().withLastName(MISSING))), violates(on("billingAddress.lastName"), withMessage("missing")));
        assertThat("invalid address", validationOf(aVisa().billedTo(anAddress().withFirstName(MISSING))), violates(on("billingAddress"), withMessage("invalid")));
    }

    @Test public void
    areValidWithAllRequiredDetails() {
        assertThat("valid card", validationOf(validCreditCardDetails()), succeeds());
    }
    
    @Test public void
    displayHiddenCardNumber()
    {
    	// Given 
		CreditCardDetails creditCardDetails = aVisa().withNumber("4111 1111 1111 " + "1111").build();
    	
    	// When
		String hiddenCardNumber = creditCardDetails.getCardNumberMasked();
    	
    	// Then
		Assert.assertEquals("XXXX XXXX XXXX " + "1111", hiddenCardNumber);
    }
}
