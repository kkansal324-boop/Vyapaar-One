package com.example.vyapaarone;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCustomerActivity extends AppCompatActivity {

    private EditText etCustomerName;
    private EditText etCustomerPhone;
    private EditText etCustomerEmail;
    private EditText etCustomerAddress;

    private Button btnSaveCustomer;
    private Button btnResetCustomer;
    private ImageButton btnBack;

    private DatabaseHelper databaseHelper;

    private int customerId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        databaseHelper = new DatabaseHelper(this);

        etCustomerName = findViewById(R.id.etCustomerName);
        etCustomerPhone = findViewById(R.id.etCustomerPhone);
        etCustomerEmail = findViewById(R.id.etCustomerEmail);
        etCustomerAddress = findViewById(R.id.etCustomerAddress);

        btnSaveCustomer = findViewById(R.id.btnSaveCustomer);
        btnResetCustomer = findViewById(R.id.btnResetCustomer);
        btnBack = findViewById(R.id.btnBack);

        // Get Customer ID
        customerId = getIntent().getIntExtra("customerId", -1);

        // Debug Toast
        Toast.makeText(this,
                "Customer ID = " + customerId,
                Toast.LENGTH_LONG).show();

        // Edit Mode
        if (customerId != -1) {

            Customer customer = databaseHelper.getCustomerById(customerId);

            if (customer != null) {

                etCustomerName.setText(customer.getCustomerName());
                etCustomerPhone.setText(customer.getCustomerPhone());
                etCustomerEmail.setText(customer.getCustomerEmail());
                etCustomerAddress.setText(customer.getCustomerAddress());

                btnSaveCustomer.setText("Update Customer");
            }
        }

        btnBack.setOnClickListener(v -> finish());

        btnResetCustomer.setOnClickListener(v -> clearFields());

        btnSaveCustomer.setOnClickListener(v -> saveCustomer());
    }

    private void saveCustomer() {

        String name = etCustomerName.getText().toString().trim();
        String phone = etCustomerPhone.getText().toString().trim();
        String email = etCustomerEmail.getText().toString().trim();
        String address = etCustomerAddress.getText().toString().trim();

        if (name.isEmpty()) {
            etCustomerName.setError("Enter customer name");
            etCustomerName.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            etCustomerPhone.setError("Enter phone number");
            etCustomerPhone.requestFocus();
            return;
        }

        Customer customer = new Customer();

        customer.setCustomerName(name);
        customer.setCustomerPhone(phone);
        customer.setCustomerEmail(email);
        customer.setCustomerAddress(address);

        boolean success;

        if (customerId != -1) {

            customer.setCustomerId(customerId);

            success = databaseHelper.updateCustomer(customer);

            if (success) {

                Toast.makeText(this,
                        "Customer Updated Successfully",
                        Toast.LENGTH_SHORT).show();

                finish();

            } else {

                Toast.makeText(this,
                        "Update Failed",
                        Toast.LENGTH_SHORT).show();
            }

        } else {

            success = databaseHelper.insertCustomer(customer);

            if (success) {

                Toast.makeText(this,
                        "Customer Added Successfully",
                        Toast.LENGTH_SHORT).show();

                clearFields();

            } else {

                Toast.makeText(this,
                        "Failed to Add Customer",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void clearFields() {

        etCustomerName.setText("");
        etCustomerPhone.setText("");
        etCustomerEmail.setText("");
        etCustomerAddress.setText("");

        etCustomerName.requestFocus();
    }
}