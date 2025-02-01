package com.jiromo5.donerhome.service.payment;

public class PaymentCardDTO {

    private String cardNumber;   // Номер карты
    private String expiryDate;   // Дата окончания (MM/YY)
    private String cvv;          // CVV-код

    // Конструкторы
    public PaymentCardDTO() {}

    public PaymentCardDTO(String cardNumber, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    // Геттеры и сеттеры
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}

