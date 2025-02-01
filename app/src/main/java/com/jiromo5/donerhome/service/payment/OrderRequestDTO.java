package com.jiromo5.donerhome.service.payment;

import java.util.List;

public class OrderRequestDTO {
    private OrdersDTO order;  // Данные о заказе
    private List<OrderItemsDTO> items;  // Список товаров в заказе
    private PaymentCardDTO card;

    public OrderRequestDTO(OrdersDTO order, List<OrderItemsDTO> items, PaymentCardDTO card) {
        this.order = order;
        this.items = items;
        this.card = card;
    }

    // Геттеры и сеттеры
    public OrdersDTO getOrder() {
        return order;
    }

    public void setOrder(OrdersDTO order) {
        this.order = order;
    }

    public List<OrderItemsDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemsDTO> items) {
        this.items = items;
    }

    public PaymentCardDTO getCard(){
        return card;
    }

    public void setCard(PaymentCardDTO card){
        this.card = card;
    }
}
