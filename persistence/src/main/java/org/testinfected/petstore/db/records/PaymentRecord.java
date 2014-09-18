package org.testinfected.petstore.db.records;

import org.testinfected.petstore.billing.Address;
import org.testinfected.petstore.billing.CreditCardDetails;
import org.testinfected.petstore.billing.CreditCardType;
import org.testinfected.petstore.billing.PaymentMethod;
import org.testinfected.petstore.db.support.Column;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.testinfected.petstore.db.Access.idOf;

public class PaymentRecord extends AbstractRecord<PaymentMethod> {

    private static final String CREDIT_CARD = "credit_card";

    private final Column<Long> id;
    private final Column<String> paymentType;
    private final Column<String> cardType;
    private final Column<String> cardNumber;
    private final Column<String> cardExpiryDate;
    private final Column<String> billingFirstName;
    private final Column<String> billingLastName;
    private final Column<String> billingEmail;
    private final Column<String> billingCountry;

    public PaymentRecord(Column<Long> id, Column<String> paymentType, Column<String> cardType, Column<String> cardNumber, Column<String> cardExpiryDate, Column<String> billingFirstName, Column<String> billingLastName, Column<String> billingEmail, Column<String> billingCountry) {
        this.id = id;
        this.paymentType = paymentType;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.billingFirstName = billingFirstName;
        this.billingLastName = billingLastName;
        this.billingEmail = billingEmail;
        this.billingCountry = billingCountry;
    }

    @Override
    public CreditCardDetails hydrate(ResultSet rs) throws SQLException {
        if (!paymentType.get(rs).equals(CREDIT_CARD)) throw new IllegalArgumentException("payment of type " + paymentType.get(rs));

        CreditCardDetails creditCard = new CreditCardDetails(
                CreditCardType.valueOf(cardType.get(rs)), cardNumber.get(rs), cardExpiryDate.get(rs),
                //TODO : fill with zip code
                new Address(billingFirstName.get(rs), billingLastName.get(rs), billingEmail.get(rs),"",billingCountry.get(rs))
        );
        idOf(creditCard).set(id.get(rs));
        return creditCard;
    }

    @Override
    public void dehydrate(PreparedStatement st, PaymentMethod payment) throws SQLException {
        CreditCardDetails creditCard = (CreditCardDetails) payment;
        id.set(st, idOf(creditCard).get());
        billingFirstName.set(st, creditCard.getFirstName());
        billingLastName.set(st, creditCard.getLastName());
        billingEmail.set(st, creditCard.getEmail());
        billingCountry.set(st, creditCard.getCountry());
        cardType.set(st, creditCard.getCardType().name());
        cardNumber.set(st, creditCard.getCardNumber());
        cardExpiryDate.set(st, creditCard.getCardExpiryDate());
        paymentType.set(st, CREDIT_CARD);
    }
}
