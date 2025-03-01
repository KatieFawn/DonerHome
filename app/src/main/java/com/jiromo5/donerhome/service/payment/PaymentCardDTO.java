package com.jiromo5.donerhome.service.payment;

public class PaymentCardDTO {

    private String cardNumber;   // The card number (e.g., 1234 5678 9012 3456)
    private String expiryDate;   // The card's expiry date in MM/YY format
    private String cvv;          // The CVV code (Card Verification Value)

    // Default constructor
    public PaymentCardDTO() {}

    // Parameterized constructor
    public PaymentCardDTO(String cardNumber, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    // Getter and setter for card number
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // Getter and setter for expiry date
    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Getter and setter for CVV
    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}


