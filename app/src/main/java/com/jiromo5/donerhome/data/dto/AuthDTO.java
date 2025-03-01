package com.jiromo5.donerhome.data.dto;

import com.jiromo5.donerhome.service.addresses.AddressDTO;

/**
 * Data Transfer Object (DTO) representing authentication-related data.
 * This class contains information about tokens, user data, address, and products.
 */
public class AuthDTO {

    private TokensDTO tokens;  // The authentication tokens
    private UserDataDTO userData;  // The user's data
    private AddressDTO address;  // The user's address
    private ProductsDTO products;  // The products associated with the authentication

    /**
     * Constructs an AuthDTO object with the specified tokens, user data, address, and products.
     *
     * @param tokens The authentication tokens
     * @param userData The user's data
     * @param address The user's address
     * @param products The products associated with the authentication
     */
    public AuthDTO(TokensDTO tokens, UserDataDTO userData, AddressDTO address, ProductsDTO products) {
        this.tokens = tokens;
        this.userData = userData;
        this.address = address;
        this.products = products;
    }

    /**
     * Gets the authentication tokens.
     *
     * @return The authentication tokens
     */
    public TokensDTO getTokens() {
        return tokens;
    }

    /**
     * Sets the authentication tokens.
     *
     * @param tokens The authentication tokens to set
     */
    public void setTokens(TokensDTO tokens) {
        this.tokens = tokens;
    }

    /**
     * Gets the user's data.
     *
     * @return The user's data
     */
    public UserDataDTO getUserData() {
        return userData;
    }

    /**
     * Sets the user's data.
     *
     * @param userData The user's data to set
     */
    public void setUserData(UserDataDTO userData) {
        this.userData = userData;
    }

    /**
     * Gets the user's address.
     *
     * @return The user's address
     */
    public AddressDTO getAddress() {
        return address;
    }

    /**
     * Sets the user's address.
     *
     * @param address The user's address to set
     */
    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    /**
     * Gets the products associated with the authentication.
     *
     * @return The products associated with the authentication
     */
    public ProductsDTO getProducts() {
        return products;
    }

    /**
     * Sets the products associated with the authentication.
     *
     * @param products The products to set
     */
    public void setProducts(ProductsDTO products) {
        this.products = products;
    }
}
