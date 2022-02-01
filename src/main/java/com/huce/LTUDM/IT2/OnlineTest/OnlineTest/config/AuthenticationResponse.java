package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.config;

public class AuthenticationResponse {
    private int code;
    private final String jwt;

    public AuthenticationResponse(int code, String jwt) {
        this.code = code;
        this.jwt = jwt;
    }

    public int getCode() {
        return code;
    }

    public String getJwt() {
        return jwt;
    }
}
