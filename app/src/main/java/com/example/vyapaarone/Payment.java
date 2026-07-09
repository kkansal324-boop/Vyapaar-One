package com.example.vyapaarone;

public class Payment {

    private int paymentId;
    private int orderId;
    private int customerId;

    private double paidAmount;
    private double pendingAmount;

    private String paymentDate;
    private String paymentMethod;

    public Payment() {
    }

    public Payment(int paymentId,
                   int orderId,
                   int customerId,
                   double paidAmount,
                   double pendingAmount,
                   String paymentDate,
                   String paymentMethod) {

        this.paymentId = paymentId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.paidAmount = paidAmount;
        this.pendingAmount = pendingAmount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

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

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(double pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}