package com.example.vyapaarone;

public class Invoice {

    private int invoiceId;
    private String invoiceNumber;
    private int orderId;
    private int customerId;
    private String customerName;
    private String invoiceDate;
    private double subTotal;
    private double gstPercent;
    private double gstAmount;
    private double grandTotal;
    private String status;


    public Invoice() {
    }

    public Invoice(String invoiceNumber,
                   int orderId,
                   int customerId,
                   String customerName,
                   String invoiceDate,
                   double subTotal,
                   double gstPercent,
                   double gstAmount,
                   double grandTotal,
                   String status) {

        this.invoiceNumber = invoiceNumber;
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.invoiceDate = invoiceDate;
        this.subTotal = subTotal;
        this.gstPercent = gstPercent;
        this.gstAmount = gstAmount;
        this.grandTotal = grandTotal;
        this.status = status;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getGstPercent() {
        return gstPercent;
    }

    public void setGstPercent(double gstPercent) {
        this.gstPercent = gstPercent;
    }

    public double getGstAmount() {
        return gstAmount;
    }

    public void setGstAmount(double gstAmount) {
        this.gstAmount = gstAmount;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}