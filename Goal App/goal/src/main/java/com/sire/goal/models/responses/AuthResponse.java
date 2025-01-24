package com.sire.goal.models.responses;


import com.sire.goal.models.entities.User;

public class AuthResponse {
    private String token;
    private User user;
    private String message;
    public AuthResponse(String message, String token, User user) {
        this.token = token;
        this.user = user;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AuthResponse(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
