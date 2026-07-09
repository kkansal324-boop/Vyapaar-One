package com.example.vyapaarone;

public class Product {

    private int id;
    private String productName;
    private double quantity;
    private String unit;

    // Existing fields
    private double pricePerUnit;
    private double sellingPrice;
    private double totalPrice;

    // NEW FIELDS (Stock Control)
    private double minStock;
    private double maxStock;

    // Empty Constructor
    public Product() {
    }

    // Constructor (without ID)
    public Product(String productName,
                   double quantity,
                   String unit,
                   double pricePerUnit,
                   double sellingPrice,
                   double totalPrice,
                   double minStock,
                   double maxStock) {

        this.productName = productName;
        this.quantity = quantity;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.sellingPrice = sellingPrice;
        this.totalPrice = totalPrice;
        this.minStock = minStock;
        this.maxStock = maxStock;
    }

    // Constructor (with ID)
    public Product(int id,
                   String productName,
                   double quantity,
                   String unit,
                   double pricePerUnit,
                   double sellingPrice,
                   double totalPrice,
                   double minStock,
                   double maxStock) {

        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.sellingPrice = sellingPrice;
        this.totalPrice = totalPrice;
        this.minStock = minStock;
        this.maxStock = maxStock;
    }

    // ---------------- ID ----------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // ---------------- Product Name ----------------
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    // ---------------- Quantity ----------------
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    // ---------------- Unit ----------------
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    // ---------------- Price Per Unit ----------------
    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
    // ---------------- Selling Price ----------------

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    // ---------------- Total Price ----------------
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // ---------------- MIN STOCK ----------------
    public double getMinStock() {
        return minStock;
    }

    public void setMinStock(double minStock) {
        this.minStock = minStock;
    }

    // ---------------- MAX STOCK ----------------
    public double getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(double maxStock) {
        this.maxStock = maxStock;
    }
}