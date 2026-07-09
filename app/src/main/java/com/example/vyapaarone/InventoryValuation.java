package com.example.vyapaarone;

public class InventoryValuation {

    private String productName;

    private double stock;

    private double purchasePrice;

    private double inventoryValue;

    public InventoryValuation() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getInventoryValue() {
        return inventoryValue;
    }

    public void setInventoryValue(double inventoryValue) {
        this.inventoryValue = inventoryValue;
    }
}