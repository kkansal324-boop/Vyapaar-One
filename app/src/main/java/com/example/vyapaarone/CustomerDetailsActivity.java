package com.example.vyapaarone;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerDetailsActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private Button btnEditCustomer, btnDeleteCustomer;

    private TextView txtName;
    private TextView txtPhone;
    private TextView txtEmail;
    private TextView txtAddress;

    private DatabaseHelper databaseHelper;

    private Customer customer;
    private int customerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        btnBack = findViewById(R.id.btnBack);
        btnEditCustomer = findViewById(R.id.btnEditCustomer);
        btnDeleteCustomer = findViewById(R.id.btnDeleteCustomer);

        txtName = findViewById(R.id.txtCustomerName);
        txtPhone = findViewById(R.id.txtCustomerPhone);
        txtEmail = findViewById(R.id.txtCustomerEmail);
        txtAddress = findViewById(R.id.txtCustomerAddress);

        databaseHelper = new DatabaseHelper(this);

        customerId = getIntent().getIntExtra("customerId", -1);

        loadCustomerDetails();

        btnBack.setOnClickListener(v -> finish());

        // Edit Customer
        btnEditCustomer.setOnClickListener(v -> {

            Intent intent = new Intent(
                    CustomerDetailsActivity.this,
                    AddCustomerActivity.class);

            intent.putExtra("customerId", customerId);

            startActivity(intent);

        });

        // Delete Customer
        btnDeleteCustomer.setOnClickListener(v -> {

            new AlertDialog.Builder(CustomerDetailsActivity.this)
                    .setTitle("Delete Customer")
                    .setMessage("Are you sure you want to delete this customer?")
                    .setPositiveButton("Delete", (dialog, which) -> {

                        boolean deleted = databaseHelper.deleteCustomer(customerId);

                        if (deleted) {

                            Toast.makeText(
                                    CustomerDetailsActivity.this,
                                    "Customer Deleted Successfully",
                                    Toast.LENGTH_SHORT
                            ).show();

                            finish();

                        } else {

                            Toast.makeText(
                                    CustomerDetailsActivity.this,
                                    "Unable to Delete Customer",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }

                    })
                    .setNegativeButton("Cancel", null)
                    .show();

        });

    }

    private void loadCustomerDetails() {

        customer = databaseHelper.getCustomerById(customerId);

        if (customer != null) {

            txtName.setText(customer.getCustomerName());
            txtPhone.setText(customer.getCustomerPhone());
            txtEmail.setText(customer.getCustomerEmail());
            txtAddress.setText(customer.getCustomerAddress());

        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        loadCustomerDetails();
    }

}