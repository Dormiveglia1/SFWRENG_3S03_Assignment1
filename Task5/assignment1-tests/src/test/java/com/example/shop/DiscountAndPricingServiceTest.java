package com.example.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountAndPricingServiceTest {

    @Test
    void discount_nullOrBlank_returnsSubtotal() {
        DiscountService ds = new DiscountService();

        assertEquals(100.0, ds.applyDiscount(100.0, null), 1e-9);
        assertEquals(100.0, ds.applyDiscount(100.0, "   "), 1e-9); // blank
    }

    @Test
    void discount_student10_and_blackfriday() {
        DiscountService ds = new DiscountService();

        assertEquals(90.0, ds.applyDiscount(100.0, "student10"), 1e-9);     // case-insensitive
        assertEquals(70.0, ds.applyDiscount(100.0, "BLACKFRIDAY"), 1e-9);
    }

    @Test
    void discount_invalid_throws() {
        DiscountService ds = new DiscountService();

        assertThrows(IllegalArgumentException.class,
                () -> ds.applyDiscount(100.0, "INVALID"));
    }

    @Test
    void discount_unknownCode_returnsSubtotal() {
        DiscountService ds = new DiscountService();

        assertEquals(100.0, ds.applyDiscount(100.0, "SOMETHING"), 1e-9);
    }

    @Test
    void tax_negative_throws() {
        PricingService ps = new PricingService();

        assertThrows(IllegalArgumentException.class,
                () -> ps.calculateTax(-0.01));
    }

    @Test
    void tax_zero_isZero() {
        PricingService ps = new PricingService();

        assertEquals(0.0, ps.calculateTax(0.0), 1e-9);
    }

    @Test
    void tax_positive_isTwentyPercent() {
        PricingService ps = new PricingService();

        assertEquals(20.0, ps.calculateTax(100.0), 1e-9);
    }

    @Test
    void subtotal_emptyOrder_isZero_and_multipleItems_sumCorrect() {
        PricingService ps = new PricingService();

        Order empty = new Order();
        assertEquals(0.0, ps.calculateSubtotal(empty), 1e-9);

        Order order = new Order();
        order.addItem(new OrderItem("a", 2, 10.0)); // 20
        order.addItem(new OrderItem("b", 1, 5.5));  // 5.5
        assertEquals(25.5, ps.calculateSubtotal(order), 1e-9);
    }
}