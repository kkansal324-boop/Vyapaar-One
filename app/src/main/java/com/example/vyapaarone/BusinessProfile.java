package com.example.vyapaarone;

public class BusinessProfile {

    private int id;
    private String businessName;
    private String ownerName;
    private String gstNumber;
    private String phone;
    private String email;
    private String profileImage;
    private String address;

    public BusinessProfile() {
    }

    public BusinessProfile(String businessName,
                           String ownerName,
                           String gstNumber,
                           String phone,
                           String email,
                           String address) {

        this.businessName = businessName;
        this.ownerName = ownerName;
        this.gstNumber = gstNumber;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getProfileImage() {return profileImage;}
    public void setProfileImage(String profileImage) {this.profileImage = profileImage;}
}