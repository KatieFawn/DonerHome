package com.jiromo5.donerhome.service.payment;

import java.math.BigDecimal;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) that represents the items in an order.
 * Used to hold information about a specific product in an order, such as product ID,
 * quantity, and price at the time of order.
 */
public class OrderItemsDTO {

    private Long orderId;         // The ID of the order.
    private Long productId;       // The ID of the product being ordered.
    private int quantity;         // The quantity of the product in the order.
    private BigDecimal price;     // The price of the product at the time the order was placed.

    /**
     * Default constructor.
     */
    public OrderItemsDTO() {}

    /**
     * Constructor to initialize the OrderItemsDTO with specific values.
     *
     * @param orderId   The ID of the order.
     * @param productId The ID of the product.
     * @param quantity  The quantity of the product.
     * @param price     The price of the product at the time of order.
     */
    public OrderItemsDTO(Long orderId, Long productId, int quantity, BigDecimal price) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Gets the ID of the order.
     *
     * @return The order ID.
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * Sets the ID of the order.
     *
     * @param orderId The order ID to set.
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets the ID of the product.
     *
     * @return The product ID.
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * Sets the ID of the product.
     *
     * @param productId The product ID to set.
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * Gets the quantity of the product.
     *
     * @return The quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product.
     *
     * @param quantity The quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the price of the product at the time of order.
     *
     * @return The price.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the product at the time of order.
     *
     * @param price The price to set.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}


