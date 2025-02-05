package com.jiromo5.donerhome.service.payment;

import java.math.BigDecimal;

public class OrderItemsDTO {

    private Long orderId;
    private Long productId;    // Ссылка на товар
    private int quantity;      // Количество товара
    private BigDecimal price;  // Цена товара на момент заказа

    // Конструкторы
    public OrderItemsDTO() {}

    public OrderItemsDTO(Long orderId, Long productId, int quantity, BigDecimal price) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getOrderId(){return orderId;}

    public void setOrderId(Long userId){
        this.orderId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

