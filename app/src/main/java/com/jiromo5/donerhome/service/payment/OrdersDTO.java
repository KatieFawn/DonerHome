package com.jiromo5.donerhome.service.payment;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Data Transfer Object (DTO) representing the details of an order.
 * Contains information about the user, order date, status, total price,
 * payment method, and delivery address.
 */
public class OrdersDTO {

    private Long userId;               // The user who placed the order.
    private String orderDate;          // The date and time the order was placed.
    private String status;             // The current status of the order (e.g., "pending", "completed").
    private BigDecimal totalPrice;    // The total price of the order.
    private String paymentMethod;     // The payment method used for the order (e.g., "credit card", "cash").
    private String street;             // The street for the delivery address.
    private String build;              // The building or house number for the delivery address.
    private String apartment;          // The apartment or unit number for the delivery address.

    /**
     * Default constructor for the OrdersDTO class.
     */
    public OrdersDTO() {}

    /**
     * Constructor to initialize an order with user ID, order date, status,
     * total price, payment method, and delivery address details.
     *
     * @param userId The user who placed the order.
     * @param orderDate The date and time the order was placed.
     * @param status The current status of the order.
     * @param totalPrice The total price of the order.
     * @param paymentMethod The payment method used for the order.
     * @param street The street of the delivery address.
     * @param build The building number of the delivery address.
     * @param apartment The apartment or unit number of the delivery address.
     */
    public OrdersDTO(Long userId, String orderDate, String status,
                     BigDecimal totalPrice, String paymentMethod,
                     String street, String build, String apartment) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.street = street;
        this.build = build;
        this.apartment = apartment;
    }

    /**
     * Gets the user ID of the person who placed the order.
     *
     * @return The user ID.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the user ID for the person who placed the order.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets the date and time the order was placed.
     *
     * @return The order date as a string.
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the order date using a Date object, formatting it into a string.
     *
     * @param orderDate The Date object representing the order date.
     */
    public void setOrderDate(Date orderDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm:ss a");
        this.orderDate = sdf.format(orderDate);  // Converts Date to string format
    }

    /**
     * Gets the current status of the order.
     *
     * @return The order status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the order.
     *
     * @param status The order status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the total price of the order.
     *
     * @return The total price of the order.
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the total price of the order.
     *
     * @param totalPrice The total price to set.
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets the payment method used for the order.
     *
     * @return The payment method.
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the payment method for the order.
     *
     * @param paymentMethod The payment method to set.
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Gets the street part of the delivery address.
     *
     * @return The street.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street part of the delivery address.
     *
     * @param street The street to set.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets the building part of the delivery address.
     *
     * @return The building number.
     */
    public String getBuild() {
        return build;
    }

    /**
     * Sets the building part of the delivery address.
     *
     * @param build The building number to set.
     */
    public void setBuild(String build) {
        this.build = build;
    }

    /**
     * Gets the apartment or unit number for the delivery address.
     *
     * @return The apartment number.
     */
    public String getApartment() {
        return apartment;
    }

    /**
     * Sets the apartment or unit number for the delivery address.
     *
     * @param apartment The apartment number to set.
     */
    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    /**
     * Sets the order date using a string.
     *
     * @param orderDate The order date as a string.
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}

