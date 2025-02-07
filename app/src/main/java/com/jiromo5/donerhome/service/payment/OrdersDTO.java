package com.jiromo5.donerhome.service.payment;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class OrdersDTO {

    private Long userId;               // Ссылка на пользователя
    private String orderDate;   // Дата и время заказа
    private String status;             // Статус заказа
    private BigDecimal totalPrice;     // Общая стоимость заказа
    private String paymentMethod;      // Способ оплаты
    private String street;
    private String build;
    private String apartment;

    // Конструкторы
    public OrdersDTO() {}

    public OrdersDTO(Long userId, String orderDate, String status,
                    BigDecimal totalPrice, String paymentMethod, String street,
                     String build, String apartment) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.street = street;
        this.build = build;
        this.apartment = apartment;
    }

    // Геттеры и сеттеры

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm:ss a");
        this.orderDate = sdf.format(orderDate);  // Преобразуем Date в строку
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
