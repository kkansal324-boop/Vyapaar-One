package com.example.vyapaarone;

public class StockMovement {

    private int movementId;
    private int productId;
    private String productName;
    private String movementType;
    private double quantity;
    private double stockAfter;
    private String movementDate;

    // Empty Constructor
    public StockMovement() {
    }

    // Constructor without ID
    public StockMovement(int productId,
                         String productName,
                         String movementType,
                         double quantity,
                         double stockAfter,
                         String movementDate) {

        this.productId = productId;
        this.productName = productName;
        this.movementType = movementType;
        this.quantity = quantity;
        this.stockAfter = stockAfter;
        this.movementDate = movementDate;
    }

    // Constructor with ID
    public StockMovement(int movementId,
                         int productId,
                         String productName,
                         String movementType,
                         double quantity,
                         double stockAfter,
                         String movementDate) {

        this.movementId = movementId;
        this.productId = productId;
        this.productName = productName;
        this.movementType = movementType;
        this.quantity = quantity;
        this.stockAfter = stockAfter;
        this.movementDate = movementDate;
    }

    // =========================
    // Getters & Setters
    // =========================

    public int getMovementId() {
        return movementId;
    }

    public void setMovementId(int movementId) {
        this.movementId = movementId;
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

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getStockAfter() {
        return stockAfter;
    }

    public void setStockAfter(double stockAfter) {
        this.stockAfter = stockAfter;
    }

    public String getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(String movementDate) {
        this.movementDate = movementDate;
    }
}