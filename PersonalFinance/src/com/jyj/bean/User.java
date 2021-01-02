package com.jyj.bean;

public class User {
    private String email;
    private String password;
    private String username;
    private String headshot;

    public User() {
    }

    public User(String email, String password, String username, String headshot) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.headshot = headshot;
    }

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadshot() {
        return headshot;
    }

    public void setHeadshot(String headshot) {
        this.headshot = headshot;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", headshot='" + headshot + '\'' +
                '}';
    }
}
