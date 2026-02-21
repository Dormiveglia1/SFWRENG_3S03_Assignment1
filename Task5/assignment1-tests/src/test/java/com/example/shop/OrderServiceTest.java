package com.example.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    @Test
    void shouldCancelOrderIfPaymentIsCrypto() {
        Order order = new Order();
        order.addItem(new OrderItem("item1", 2, 10.0));

        OrderService service = new OrderService();

        double result = service.processOrder(order, "NONE", "crypto");

        assertEquals(0.0, result, 1e-9);
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    void shouldCancelOrderIfPaymentIsNull() {
        Order order = new Order();
        order.addItem(new OrderItem("item1", 1, 5.0));

        OrderService service = new OrderService();

        double result = service.processOrder(order, "NONE", null);

        assertEquals(0.0, result, 1e-9);
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    void shouldProcessOrderWithValidPaymentCardNoDiscount() {
        Order order = new Order();
        order.addItem(new OrderItem("item1", 2, 10.0));

        OrderService service = new OrderService();

        double result = service.processOrder(order, "NONE", "card");

        assertTrue(result > 0.0);
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    void unknownPaymentMethodShouldThrow() {
        Order order = new Order();
        order.addItem(new OrderItem("item1", 1, 10.0));

        OrderService service = new OrderService();

        assertThrows(UnsupportedOperationException.class,
                () -> service.processOrder(order, "NONE", "weird_method"));
    }
}