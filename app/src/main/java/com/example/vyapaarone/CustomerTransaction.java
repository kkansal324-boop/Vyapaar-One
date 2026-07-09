package com.example.vyapaarone;

public class CustomerTransaction {

    private int id;

    private String transactionType;
    // INVOICE
    // PAYMENT
    // DEBIT
    // CREDIT

    private String title;

    private String date;

    private double amount;

    private double runningBalance;

    public CustomerTransaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRunningBalance() {
        return runningBalance;
    }

    public void setRunningBalance(double runningBalance) {
        this.runningBalance = runningBalance;
    }
}