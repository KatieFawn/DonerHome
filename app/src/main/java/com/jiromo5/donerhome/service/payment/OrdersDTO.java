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
    private Long shippingAddressId;    // Ссылка на адрес доставки
    private String paymentMethod;      // Способ оплаты

    // Конструкторы
    public OrdersDTO() {}

    public OrdersDTO(Long userId, String orderDate, String status,
                    BigDecimal totalPrice, Long shippingAddressId, String paymentMethod) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalPrice = totalPrice;
        this.shippingAddressId = shippingAddressId;
        this.paymentMethod = paymentMethod;
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

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
