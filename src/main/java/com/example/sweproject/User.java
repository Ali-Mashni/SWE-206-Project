package com.example.sweproject;

public class    User {
    private String username;
    private String role;
    private String password;
    private String email;

    public User(String username,String password, String role) {
        this.username = username;
        this.role = role;
        this.password=password;
    }

        public String getUsername() {
            return username;
        }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

