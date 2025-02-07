package com.jiromo5.donerhome.service.addresses;

import java.util.Arrays;

public class AddressDTO {

    private Long userId;
    private String[] addressName;
    private String[] city;
    private String[] street;
    private String[] build;
    private String[] apartment;
    private String[] postalCode;

    public AddressDTO() {
    }

    // Конструктор
    public AddressDTO(Long userId, String[] addressName, String[] city, String[] street,
                      String[] build, String[] apartment, String[] postalCode) {
        this.userId = userId;
        this.addressName = addressName;
        this.city = city;
        this.street = street;
        this.build = build;
        this.apartment = apartment;
        this.postalCode = postalCode;
    }

    // Геттеры и сеттеры
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String[] getAddressName() {
        return addressName;
    }

    public void setAddressName(String[] addressName) {
        this.addressName = addressName;
    }

    public String[] getCity() {
        return city;
    }

    public void setCity(String[] city) {
        this.city = city;
    }

    public String[] getStreet() {
        return street;
    }

    public void setStreet(String[] street) {
        this.street = street;
    }

    public String[] getBuild() {
        return build;
    }

    public void setBuild(String[] build) {
        this.build = build;
    }

    public String[] getApartment() {
        return apartment;
    }

    public void setApartment(String[] apartment) {
        this.apartment = apartment;
    }

    public String[] getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String[] postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "userId=" + userId +
                ", addressName=" + Arrays.toString(addressName) +
                ", city=" + Arrays.toString(city) +
                ", street=" + Arrays.toString(street) +
                ", build=" + Arrays.toString(build) +
                ", apartment=" + Arrays.toString(apartment) +
                ", postalCode=" + Arrays.toString(postalCode) +
                '}';
    }
}


