package com.example.vyapaarone;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddSupplierActivity extends AppCompatActivity {

    private EditText etSupplierName;
    private EditText etCompanyName;
    private EditText etMobile;
    private EditText etEmail;
    private EditText etGST;
    private EditText etAddress;
    private EditText etCity;
    private EditText etState;
    private EditText etPincode;
    private EditText etOpeningBalance;
    private EditText etNotes;

    private Spinner spBalanceType;

    private Button btnSaveSupplier;
    private Button btnDeleteSupplier;

    private DatabaseHelper databaseHelper;
    private SessionManager sessionManager;
    private int supplierId = -1;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);

        databaseHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);
        etSupplierName = findViewById(R.id.etSupplierName);
        etCompanyName = findViewById(R.id.etCompanyName);
        etMobile = findViewById(R.id.etMobile);
        etEmail = findViewById(R.id.etEmail);
        etGST = findViewById(R.id.etGST);
        etAddress = findViewById(R.id.etAddress);
        etCity = findViewById(R.id.etCity);
        etState = findViewById(R.id.etState);
        etPincode = findViewById(R.id.etPincode);
        etOpeningBalance = findViewById(R.id.etOpeningBalance);
        etNotes = findViewById(R.id.etNotes);

        spBalanceType = findViewById(R.id.spBalanceType);

        btnSaveSupplier = findViewById(R.id.btnSaveSupplier);
        btnDeleteSupplier = findViewById(R.id.btnDeleteSupplier);

        String[] balanceTypes = {
                "Debit",
                "Credit"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        balanceTypes
                );

        spBalanceType.setAdapter(adapter);

        supplierId = getIntent().getIntExtra("supplier_id", -1);

        if (supplierId != -1) {

            isEditMode = true;

            btnSaveSupplier.setText("UPDATE SUPPLIER");

            btnDeleteSupplier.setVisibility(View.VISIBLE);

            loadSupplierData();

        } else {

            btnDeleteSupplier.setVisibility(View.GONE);

        }

        btnSaveSupplier.setOnClickListener(v -> {

            if (isEditMode) {

                updateSupplier();

            } else {

                saveSupplier();

            }

        });

        btnDeleteSupplier.setOnClickListener(v -> showDeleteDialog());

    }
// ============================
// SAVE / UPDATE SUPPLIER
// ============================

    private Supplier getSupplierFromForm() {

        String supplierName = etSupplierName.getText().toString().trim();
        String companyName = etCompanyName.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String gst = etGST.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String city = etCity.getText().toString().trim();
        String state = etState.getText().toString().trim();
        String pincode = etPincode.getText().toString().trim();
        String openingBalanceText = etOpeningBalance.getText().toString().trim();
        String notes = etNotes.getText().toString().trim();

        String balanceType = spBalanceType.getSelectedItem().toString();

        if (TextUtils.isEmpty(supplierName)) {
            etSupplierName.setError("Enter Supplier Name");
            etSupplierName.requestFocus();
            return null;
        }

        if (TextUtils.isEmpty(mobile)) {
            etMobile.setError("Enter Mobile Number");
            etMobile.requestFocus();
            return null;
        }

        if (mobile.length() != 10) {
            etMobile.setError("Enter Valid Mobile Number");
            etMobile.requestFocus();
            return null;
        }

        double openingBalance = 0;

        if (!TextUtils.isEmpty(openingBalanceText)) {
            openingBalance = Double.parseDouble(openingBalanceText);
        }

        Supplier supplier = new Supplier();

        supplier.setSupplierId(supplierId);
        supplier.setSupplierName(supplierName);
        supplier.setCompanyName(companyName);
        supplier.setMobile(mobile);
        supplier.setEmail(email);
        supplier.setGstNumber(gst);
        supplier.setAddress(address);
        supplier.setCity(city);
        supplier.setState(state);
        supplier.setPincode(pincode);
        supplier.setOpeningBalance(openingBalance);
        supplier.setBalanceType(balanceType);
        supplier.setNotes(notes);

        return supplier;
    }

    private void saveSupplier() {

        Supplier supplier = getSupplierFromForm();

        if (supplier == null)
            return;

        boolean result =
                databaseHelper.insertSupplier(
                        supplier,
                        sessionManager.getUserId()
                );

        if (result) {

            Toast.makeText(
                    this,
                    "Supplier Added Successfully",
                    Toast.LENGTH_SHORT
            ).show();

            clearFields();

        } else {

            Toast.makeText(
                    this,
                    "Failed to Add Supplier",
                    Toast.LENGTH_SHORT
            ).show();

        }

    }

    private void updateSupplier() {

        Supplier supplier = getSupplierFromForm();

        if (supplier == null)
            return;

        boolean result = databaseHelper.updateSupplier(supplier);

        if (result) {

            Toast.makeText(
                    this,
                    "Supplier Updated Successfully",
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

    private void loadSupplierData() {

        Supplier supplier = databaseHelper.getSupplierById(
                supplierId,
                sessionManager.getUserId()
        );

        if (supplier == null)
            return;

        etSupplierName.setText(supplier.getSupplierName());
        etCompanyName.setText(supplier.getCompanyName());
        etMobile.setText(supplier.getMobile());
        etEmail.setText(supplier.getEmail());
        etGST.setText(supplier.getGstNumber());
        etAddress.setText(supplier.getAddress());
        etCity.setText(supplier.getCity());
        etState.setText(supplier.getState());
        etPincode.setText(supplier.getPincode());
        etOpeningBalance.setText(String.valueOf(supplier.getOpeningBalance()));
        etNotes.setText(supplier.getNotes());

        if (supplier.getBalanceType().equalsIgnoreCase("Credit")) {
            spBalanceType.setSelection(1);
        } else {
            spBalanceType.setSelection(0);
        }

    }
    // ============================
// DELETE SUPPLIER
// ============================

    private void showDeleteDialog() {

        new AlertDialog.Builder(this)
                .setTitle("Delete Supplier")
                .setMessage("Are you sure you want to delete this supplier?")
                .setPositiveButton("Delete", (dialog, which) -> deleteSupplier())
                .setNegativeButton("Cancel", null)
                .show();

    }

    private void deleteSupplier() {

        boolean result = databaseHelper.deleteSupplier(supplierId);

        if (result) {

            Toast.makeText(
                    this,
                    "Supplier Deleted Successfully",
                    Toast.LENGTH_SHORT
            ).show();

            finish();

        } else {

            Toast.makeText(
                    this,
                    "Delete Failed",
                    Toast.LENGTH_SHORT
            ).show();

        }

    }

// ============================
// CLEAR FORM
// ============================

    private void clearFields() {

        etSupplierName.setText("");
        etCompanyName.setText("");
        etMobile.setText("");
        etEmail.setText("");
        etGST.setText("");
        etAddress.setText("");
        etCity.setText("");
        etState.setText("");
        etPincode.setText("");
        etOpeningBalance.setText("");
        etNotes.setText("");

        spBalanceType.setSelection(0);

        etSupplierName.requestFocus();

    }

}