package com.example.projektarbetebackend.Models;

import org.springframework.http.HttpHeaders;

public class AuthenticationResponse {

    private final String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }

}


