package com.jiromo5.donerhome.service.payment;

import java.util.List;

/**
 * Data Transfer Object (DTO) that represents a request to create an order.
 * It includes order details, a list of order items, and payment card information.
 */
public class OrderRequestDTO {

    private OrdersDTO order;              // The order details.
    private List<OrderItemsDTO> items;    // A list of items in the order.
    private PaymentCardDTO card;          // Payment card details for the order.

    /**
     * Constructor to initialize the OrderRequestDTO with order, items, and card information.
     *
     * @param order The details of the order.
     * @param items The list of items included in the order.
     * @param card  The payment card used for the order.
     */
    public OrderRequestDTO(OrdersDTO order, List<OrderItemsDTO> items, PaymentCardDTO card) {
        this.order = order;
        this.items = items;
        this.card = card;
    }

    /**
     * Gets the order details.
     *
     * @return The order details.
     */
    public OrdersDTO getOrder() {
        return order;
    }

    /**
     * Sets the order details.
     *
     * @param order The order details to set.
     */
    public void setOrder(OrdersDTO order) {
        this.order = order;
    }

    /**
     * Gets the list of items in the order.
     *
     * @return The list of order items.
     */
    public List<OrderItemsDTO> getItems() {
        return items;
    }

    /**
     * Sets the list of items in the order.
     *
     * @param items The list of order items to set.
     */
    public void setItems(List<OrderItemsDTO> items) {
        this.items = items;
    }

    /**
     * Gets the payment card details for the order.
     *
     * @return The payment card details.
     */
    public PaymentCardDTO getCard() {
        return card;
    }

    /**
     * Sets the payment card details for the order.
     *
     * @param card The payment card details to set.
     */
    public void setCard(PaymentCardDTO card) {
        this.card = card;
    }
}

