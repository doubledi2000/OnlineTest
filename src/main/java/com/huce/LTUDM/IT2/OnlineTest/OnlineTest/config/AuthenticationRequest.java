package com.huce.LTUDM.IT2.OnlineTest.OnlineTest.config;


public class AuthenticationRequest {
    private String username;
    private String password;
    private String role;

    public AuthenticationRequest(String username, String password, String role) {
        this.username = username;
        this.password = password;
    }

    public AuthenticationRequest() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
