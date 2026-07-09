package com.example.vyapaarone;

public class Supplier {

    private int supplierId;

    private String supplierName;
    private String companyName;
    private String mobile;
    private String email;
    private String gstNumber;
    private String address;
    private String city;
    private String state;
    private String pincode;

    private double openingBalance;
    private String balanceType;

    private String notes;

    public Supplier() {
    }

    public Supplier(int supplierId,
                    String supplierName,
                    String companyName,
                    String mobile,
                    String email,
                    String gstNumber,
                    String address,
                    String city,
                    String state,
                    String pincode,
                    double openingBalance,
                    String balanceType,
                    String notes) {

        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.companyName = companyName;
        this.mobile = mobile;
        this.email = email;
        this.gstNumber = gstNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.openingBalance = openingBalance;
        this.balanceType = balanceType;
        this.notes = notes;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public String getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}