package com.compulandia.sistematickets.config;

public class AuthRequest {
    private String username;
    private String password;

    // Getters y setters (requeridos para que Spring lea el JSON)
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

