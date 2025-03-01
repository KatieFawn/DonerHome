package com.jiromo5.donerhome.service.addresses;

import java.util.Arrays;

/**
 * This class represents a Data Transfer Object (DTO) that holds address-related data.
 * It includes fields for user ID, address components such as address name, city, street,
 * building, apartment, and postal code. These fields are stored as arrays of strings
 * to allow multiple address entries for a user.
 */
public class AddressDTO {

    private Long userId; // The user ID associated with the address
    private String[] addressName; // The name of the address (e.g., "Home", "Office")
    private String[] city; // The city of the address
    private String[] street; // The street of the address
    private String[] build; // The building information of the address
    private String[] apartment; // The apartment information of the address
    private String[] postalCode; // The postal code of the address

    /**
     * Default constructor for AddressDTO.
     * Initializes a new instance with no data.
     */
    public AddressDTO() {
    }

    /**
     * Constructor for AddressDTO.
     * Initializes the address data with the provided values.
     *
     * @param userId      The user ID associated with the address.
     * @param addressName The name of the address (e.g., "Home", "Office").
     * @param city        The city of the address.
     * @param street      The street of the address.
     * @param build       The building information of the address.
     * @param apartment   The apartment information of the address.
     * @param postalCode  The postal code of the address.
     */
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

    // Getters and setters for each field

    /**
     * Gets the user ID associated with the address.
     *
     * @return The user ID.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the user ID for the address.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets the address names (e.g., "Home", "Office").
     *
     * @return The address names as an array of strings.
     */
    public String[] getAddressName() {
        return addressName;
    }

    /**
     * Sets the address names.
     *
     * @param addressName The address names to set.
     */
    public void setAddressName(String[] addressName) {
        this.addressName = addressName;
    }

    /**
     * Gets the city of the address.
     *
     * @return The city as an array of strings.
     */
    public String[] getCity() {
        return city;
    }

    /**
     * Sets the city of the address.
     *
     * @param city The city to set.
     */
    public void setCity(String[] city) {
        this.city = city;
    }

    /**
     * Gets the street of the address.
     *
     * @return The street as an array of strings.
     */
    public String[] getStreet() {
        return street;
    }

    /**
     * Sets the street of the address.
     *
     * @param street The street to set.
     */
    public void setStreet(String[] street) {
        this.street = street;
    }

    /**
     * Gets the building information of the address.
     *
     * @return The building information as an array of strings.
     */
    public String[] getBuild() {
        return build;
    }

    /**
     * Sets the building information of the address.
     *
     * @param build The building information to set.
     */
    public void setBuild(String[] build) {
        this.build = build;
    }

    /**
     * Gets the apartment information of the address.
     *
     * @return The apartment information as an array of strings.
     */
    public String[] getApartment() {
        return apartment;
    }

    /**
     * Sets the apartment information of the address.
     *
     * @param apartment The apartment information to set.
     */
    public void setApartment(String[] apartment) {
        this.apartment = apartment;
    }

    /**
     * Gets the postal code of the address.
     *
     * @return The postal code as an array of strings.
     */
    public String[] getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the address.
     *
     * @param postalCode The postal code to set.
     */
    public void setPostalCode(String[] postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Returns a string representation of the AddressDTO object, including the user ID and address details.
     *
     * @return A string representation of the AddressDTO.
     */
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


