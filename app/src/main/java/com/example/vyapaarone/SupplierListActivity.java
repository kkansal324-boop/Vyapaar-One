package com.example.vyapaarone;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SupplierListActivity extends AppCompatActivity {

    private RecyclerView recyclerSuppliers;
    private TextView txtEmpty;
    private EditText etSearchSupplier;
    private Button btnAddSupplier;
    private SessionManager sessionManager;
    private DatabaseHelper databaseHelper;
    private SupplierAdapter supplierAdapter;
    private ArrayList<Supplier> supplierList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_list);

        recyclerSuppliers = findViewById(R.id.recyclerSuppliers);
        txtEmpty = findViewById(R.id.txtEmpty);
        etSearchSupplier = findViewById(R.id.etSearchSupplier);
        btnAddSupplier = findViewById(R.id.btnAddSupplier);

        databaseHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);
        recyclerSuppliers.setLayoutManager(new LinearLayoutManager(this));

        loadSuppliers();

        etSearchSupplier.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchSuppliers(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnAddSupplier.setOnClickListener(v -> {

            Intent intent = new Intent(
                    SupplierListActivity.this,
                    AddSupplierActivity.class);

            startActivity(intent);

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSuppliers();
    }

    private void loadSuppliers() {

        supplierList = databaseHelper.getAllSuppliers(sessionManager.getUserId());

        if (supplierList == null) {
            supplierList = new ArrayList<>();
        }

        supplierAdapter = new SupplierAdapter(
                supplierList,
                supplier -> {

                    Intent intent = new Intent(
                            SupplierListActivity.this,
                            AddSupplierActivity.class);

                    intent.putExtra("supplier_id", supplier.getSupplierId());

                    startActivity(intent);

                });

        recyclerSuppliers.setAdapter(supplierAdapter);

        if (supplierList.isEmpty()) {

            txtEmpty.setVisibility(View.VISIBLE);
            recyclerSuppliers.setVisibility(View.GONE);

        } else {

            txtEmpty.setVisibility(View.GONE);
            recyclerSuppliers.setVisibility(View.VISIBLE);

        }

    }

    private void searchSuppliers(String keyword) {

        supplierList = databaseHelper.searchSuppliers(
                keyword,
                sessionManager.getUserId()
        );

        supplierAdapter = new SupplierAdapter(
                supplierList,
                supplier -> {

                    Intent intent = new Intent(
                            SupplierListActivity.this,
                            AddSupplierActivity.class);

                    intent.putExtra("supplier_id", supplier.getSupplierId());

                    startActivity(intent);

                });

        recyclerSuppliers.setAdapter(supplierAdapter);

        if (supplierList.isEmpty()) {

            txtEmpty.setVisibility(View.VISIBLE);
            recyclerSuppliers.setVisibility(View.GONE);

        } else {

            txtEmpty.setVisibility(View.GONE);
            recyclerSuppliers.setVisibility(View.VISIBLE);

        }

    }

}