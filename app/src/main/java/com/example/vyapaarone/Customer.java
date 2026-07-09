package com.example.vyapaarone;

public class Customer {

    private int customerId;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private String customerAddress;

    // Empty Constructor
    public Customer() {
    }

    // Constructor without ID
    public Customer(String customerName,
                    String customerPhone,
                    String customerEmail,
                    String customerAddress) {

        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
    }

    // Constructor with ID
    public Customer(int customerId,
                    String customerName,
                    String customerPhone,
                    String customerEmail,
                    String customerAddress) {

        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
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

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
}