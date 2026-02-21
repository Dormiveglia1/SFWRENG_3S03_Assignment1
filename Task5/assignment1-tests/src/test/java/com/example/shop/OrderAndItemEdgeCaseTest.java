package com.example.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderAndItemEdgeCaseTest {

    @Test
    void cannotAddItemsAfterOrderProcessed() {
        Order order = new Order();
        order.addItem(new OrderItem("item", 1, 10.0));

        // 让订单被处理，状态不再是 CREATED
        OrderService service = new OrderService();
        service.processOrder(order, "NONE", "card");

        // 现在再加 item 应该抛异常
        assertThrows(IllegalStateException.class,
                () -> order.addItem(new OrderItem("late", 1, 1.0)));
    }

    @Test
    void orderItem_invalidQuantity_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new OrderItem("x", 0, 10.0));
        assertThrows(IllegalArgumentException.class,
                () -> new OrderItem("x", -1, 10.0));
    }

    @Test
    void orderItem_negativePrice_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new OrderItem("x", 1, -0.01));
    }
}