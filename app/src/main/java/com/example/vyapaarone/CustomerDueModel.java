package com.example.vyapaarone;

public class CustomerDueModel {

    private String customerName;
    private double total;
    private double paid;
    private double pending;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getPending() {
        return pending;
    }

    public void setPending(double pending) {
        this.pending = pending;
    }
}