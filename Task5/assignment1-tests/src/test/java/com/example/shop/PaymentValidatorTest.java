package com.example.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentValidatorTest {

    @Test
    void nullShouldBeInvalid() {
        PaymentValidator v = new PaymentValidator();
        assertFalse(v.isPaymentMethodValid(null));
    }

    @Test
    void cardAndPaypalShouldBeValid() {
        PaymentValidator v = new PaymentValidator();
        assertTrue(v.isPaymentMethodValid("card"));
        assertTrue(v.isPaymentMethodValid("paypal"));
    }

    @Test
    void cryptoShouldBeInvalid() {
        PaymentValidator v = new PaymentValidator();
        assertFalse(v.isPaymentMethodValid("crypto"));
    }

    @Test
    void unknownShouldThrow() {
        PaymentValidator v = new PaymentValidator();
        assertThrows(UnsupportedOperationException.class,
                () -> v.isPaymentMethodValid("unknown"));
    }
}