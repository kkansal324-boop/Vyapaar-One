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

public class PurchaseHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerPurchases;

    private EditText etSearchPurchase;

    private TextView txtEmpty;

    private Button btnAddPurchase;

    private DatabaseHelper databaseHelper;
    private SessionManager sessionManager;
    private PurchaseAdapter purchaseAdapter;

    private ArrayList<Purchase> purchaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        recyclerPurchases = findViewById(R.id.recyclerPurchases);

        etSearchPurchase = findViewById(R.id.etSearchPurchase);

        txtEmpty = findViewById(R.id.txtEmpty);

        btnAddPurchase = findViewById(R.id.btnAddPurchase);

        databaseHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);
        recyclerPurchases.setLayoutManager(
                new LinearLayoutManager(this));

        loadPurchases();
        // ==========================
        // SEARCH PURCHASE
        // ==========================

        etSearchPurchase.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s,
                                          int start,
                                          int count,
                                          int after) {

            }

            @Override
            public void onTextChanged(CharSequence s,
                                      int start,
                                      int before,
                                      int count) {

                searchPurchases(s.toString().trim());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        // ==========================
        // ADD PURCHASE
        // ==========================

        btnAddPurchase.setOnClickListener(v -> {

            Intent intent = new Intent(
                    PurchaseHistoryActivity.this,
                    AddPurchaseActivity.class);

            startActivity(intent);

        });

    }

    // ==========================
    // REFRESH LIST
    // ==========================

    @Override
    protected void onResume() {
        super.onResume();

        loadPurchases();

    }

    // ==========================
    // LOAD PURCHASES
    // ==========================

    private void loadPurchases() {

        purchaseList = databaseHelper.getAllPurchases(
                sessionManager.getUserId());
        if (purchaseList == null) {

            purchaseList = new ArrayList<>();

        }

        purchaseAdapter =
                new PurchaseAdapter(
                        PurchaseHistoryActivity.this,
                        purchaseList);

        recyclerPurchases.setAdapter(purchaseAdapter);

        if (purchaseList.isEmpty()) {

            txtEmpty.setVisibility(View.VISIBLE);

            recyclerPurchases.setVisibility(View.GONE);

        } else {

            txtEmpty.setVisibility(View.GONE);

            recyclerPurchases.setVisibility(View.VISIBLE);

        }

    }
    // ==========================
    // SEARCH PURCHASES
    // ==========================

    private void searchPurchases(String keyword) {

        purchaseList = databaseHelper.searchPurchases(
                keyword,
                sessionManager.getUserId());
        if (purchaseList == null) {
            purchaseList = new ArrayList<>();
        }

        purchaseAdapter =
                new PurchaseAdapter(
                        PurchaseHistoryActivity.this,
                        purchaseList);

        recyclerPurchases.setAdapter(purchaseAdapter);

        if (purchaseList.isEmpty()) {

            txtEmpty.setVisibility(View.VISIBLE);
            recyclerPurchases.setVisibility(View.GONE);

        } else {

            txtEmpty.setVisibility(View.GONE);
            recyclerPurchases.setVisibility(View.VISIBLE);

        }

    }

}