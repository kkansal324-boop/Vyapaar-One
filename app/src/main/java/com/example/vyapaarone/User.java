package com.example.vyapaarone;

public class User {

    private int id;
    private String username;
    private String userId;
    private String shopName;
    private String mobile;
    private String gstin;
    private String password;

    public User() {
    }

    public User(String username,
                String userId,
                String shopName,
                String mobile,
                String gstin,
                String password) {

        this.username = username;
        this.userId = userId;
        this.shopName = shopName;
        this.mobile = mobile;
        this.gstin = gstin;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}