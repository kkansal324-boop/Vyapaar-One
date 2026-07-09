package com.example.vyapaarone;

public class Order {

    private int orderId;
    private int customerId;
    private String customerName;
    private String orderDate;
    private double totalAmount;
    private String orderStatus;

    // Empty Constructor
    public Order() {
    }

    // Constructor without Order ID
    public Order(int customerId,
                 String customerName,
                 String orderDate,
                 double totalAmount,
                 String orderStatus) {

        this.customerId = customerId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }

    // Constructor with Order ID
    public Order(int orderId,
                 int customerId,
                 String customerName,
                 String orderDate,
                 double totalAmount,
                 String orderStatus) {

        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }

    // ==========================
    // Getters and Setters
    // ==========================

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}