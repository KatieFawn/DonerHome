package com.jiromo5.donerhome.data.dto;

import com.jiromo5.donerhome.data.state.ProductsData;
import com.jiromo5.donerhome.service.addresses.AddressDTO;

public class AuthDTO {

    private TokensDTO tokens;
    private UserDataDTO userData;
    private AddressDTO address;
    private ProductsDTO products;

    public AuthDTO(TokensDTO tokens, UserDataDTO userData, AddressDTO address, ProductsDTO products) {
        this.tokens = tokens;
        this.userData = userData;
        this.address = address;
        this.products = products;
    }

    public TokensDTO getTokens() {
        return tokens;
    }

    public void setTokens(TokensDTO tokens) {
        this.tokens = tokens;
    }

    public UserDataDTO getUserData() {
        return userData;
    }

    public void setUserData(UserDataDTO userData) {
        this.userData = userData;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public ProductsDTO getProducts() {
        return products;
    }

    public void setProducts(ProductsDTO products) {
        this.products = products;
    }
}
