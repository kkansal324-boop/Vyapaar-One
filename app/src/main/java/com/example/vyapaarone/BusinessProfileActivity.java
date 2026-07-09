package com.example.vyapaarone;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BusinessProfileActivity extends AppCompatActivity {

    private EditText etBusinessName;
    private EditText etOwnerName;
    private EditText etGST;
    private EditText etPhone;
    private EditText etEmail;
    private EditText etAddress;
    private Button btnSave;

    private DatabaseHelper db;

    private BusinessProfile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_profile);

        db = new DatabaseHelper(this);

        etBusinessName = findViewById(R.id.etBusinessName);
        etOwnerName = findViewById(R.id.etOwnerName);
        etGST = findViewById(R.id.etGST);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);

        btnSave = findViewById(R.id.btnSave);

        loadBusinessProfile();

        btnSave.setOnClickListener(v -> saveBusinessProfile());
    }

    private void loadBusinessProfile() {

        profile = db.getBusinessProfile();

        if (profile == null)
            return;

        etBusinessName.setText(profile.getBusinessName());
        etOwnerName.setText(profile.getOwnerName());
        etGST.setText(profile.getGstNumber());
        etPhone.setText(profile.getPhone());
        etEmail.setText(profile.getEmail());
        etAddress.setText(profile.getAddress());

        btnSave.setText("Update Business Profile");
    }

    private void saveBusinessProfile() {

        String business = etBusinessName.getText().toString().trim();
        String owner = etOwnerName.getText().toString().trim();
        String gst = etGST.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String address = etAddress.getText().toString().trim();

        if (business.isEmpty()) {

            Toast.makeText(
                    this,
                    "Enter Business Name",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        if (profile == null) {

            profile = new BusinessProfile();

            profile.setBusinessName(business);
            profile.setOwnerName(owner);
            profile.setGstNumber(gst);
            profile.setPhone(phone);
            profile.setEmail(email);
            profile.setAddress(address);

            if (db.insertBusinessProfile(profile)) {

                Toast.makeText(
                        this,
                        "Business Profile Saved",
                        Toast.LENGTH_SHORT
                ).show();

                finish();

                loadBusinessProfile();

            } else {

                Toast.makeText(
                        this,
                        "Save Failed",
                        Toast.LENGTH_SHORT
                ).show();
            }

        } else {

            profile.setBusinessName(business);
            profile.setOwnerName(owner);
            profile.setGstNumber(gst);
            profile.setPhone(phone);
            profile.setEmail(email);
            profile.setAddress(address);

            if (db.updateBusinessProfile(profile)) {

                Toast.makeText(
                        this,
                        "Business Profile Updated",
                        Toast.LENGTH_SHORT
                ).show();

                finish();
            } else {

                Toast.makeText(
                        this,
                        "Update Failed",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }
}