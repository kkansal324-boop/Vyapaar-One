package com.example.vyapaarone;

public class OrderItem {

    private int id;
    private int orderId;
    private int productId;
    private String productName;
    private double quantity;
    private double price;

    // Empty constructor
    public OrderItem() {
    }

    // Constructor without ID
    public OrderItem(int orderId, int productId, String productName, double quantity, double price) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Full constructor
    public OrderItem(int id, int orderId, int productId, String productName, double quantity, double price) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // ======================
    // Getters & Setters
    // ======================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}