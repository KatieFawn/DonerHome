package com.jiromo5.donerhome.service.auth;

import com.jiromo5.donerhome.data.dto.TokensDTO;
import com.jiromo5.donerhome.data.dto.UserDataDTO;
import com.jiromo5.donerhome.service.addresses.AddressDTO;

public class AuthResponse {

    private TokensDTO tokens;
    private UserDataDTO userData;
    private AddressDTO address;

    public AuthResponse(TokensDTO tokens, UserDataDTO userData, AddressDTO address) {
        this.tokens = tokens;
        this.userData = userData;
        this.address = address;
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
}
