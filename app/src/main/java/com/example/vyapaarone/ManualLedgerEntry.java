package com.example.vyapaarone;

public class ManualLedgerEntry {

    private int id;
    private int customerId;

    private String type;      // DEBIT or CREDIT
    private String title;
    private double amount;
    private String date;

    public ManualLedgerEntry() {
    }

    public ManualLedgerEntry(int id,
                             int customerId,
                             String type,
                             String title,
                             double amount,
                             String date) {

        this.id = id;
        this.customerId = customerId;
        this.type = type;
        this.title = title;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}