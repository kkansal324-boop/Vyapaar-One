package com.example.vyapaarone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerListActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private Button btnAddCustomer;
    private RecyclerView recyclerCustomers;

    private DatabaseHelper databaseHelper;
    private CustomerAdapter customerAdapter;
    private ArrayList<Customer> customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        btnBack = findViewById(R.id.btnBack);
        btnAddCustomer = findViewById(R.id.btnAddCustomer);
        recyclerCustomers = findViewById(R.id.recyclerCustomers);

        databaseHelper = new DatabaseHelper(this);

        customerList = new ArrayList<>();

        recyclerCustomers.setLayoutManager(new LinearLayoutManager(this));
        recyclerCustomers.setHasFixedSize(true);
        recyclerCustomers.setItemAnimator(null);

        btnBack.setOnClickListener(v -> finish());

        btnAddCustomer.setOnClickListener(v -> {

            Intent intent = new Intent(
                    CustomerListActivity.this,
                    AddCustomerActivity.class);

            startActivity(intent);

        });

        loadCustomers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCustomers();
    }

    private void loadCustomers() {

        customerList = databaseHelper.getAllCustomers();

        customerAdapter = new CustomerAdapter(this, customerList);

        recyclerCustomers.setAdapter(customerAdapter);
    }

}