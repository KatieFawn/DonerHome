package com.jiromo5.donerhome.data;

public class LoginRequest {

    private String email;
    private String password;

    // Конструктор
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginRequest(){

    }

    // Геттеры
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

